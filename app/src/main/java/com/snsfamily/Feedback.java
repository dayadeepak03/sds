package com.snsfamily;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Feedback extends Activity {

	EditText ednm,edcontact,edmsg;
	Button btnsend,btncncl,btnReset;
	String nm,num,msg;
	ConnectionDetector cd;
	Boolean isInternetPresent = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);
		
		ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1b75ba")));
        bar.setIcon(android.R.color.transparent);
        bar.setDisplayHomeAsUpEnabled(true);
		
		ednm = (EditText) findViewById(R.id.edtnm);
		edcontact = (EditText) findViewById(R.id.edtnum);
		edmsg = (EditText) findViewById(R.id.edtmsg);
		btnsend = (Button) findViewById(R.id.btnsend);
		btncncl = (Button) findViewById(R.id.btncancel);
		cd = new ConnectionDetector(getApplicationContext());
		btnReset = findViewById(R.id.btnReset);
		
		btnsend.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				nm = ednm.getText().toString().trim();
				num = edcontact.getText().toString().trim();
				msg = edmsg.getText().toString().trim();

				if(nm.isEmpty())
				{
					ednm.setError("Name is Required");
					ednm.requestFocus();
					return;
				}
				if(num.isEmpty())
				{
					edcontact.setError("contact Number is Required");
					edcontact.requestFocus();
					return;
				}
				if(num.length() < 10){
					edcontact.setError("Enter Valid Mobile No.");
					edcontact.requestFocus();
					return;
				}
				if(msg.isEmpty())
				{
					edmsg.setError("Message is Required");
					edmsg.requestFocus();
					return;
				}
				else{
					isInternetPresent = cd.isConnectingToInternet();
					
					 if (isInternetPresent) {	
						 InsertData task1 = new InsertData();
						 task1.execute(new String[]{"http://keypointservices.net/darji/android/insertfeedback.php"});
					 }
					  else {  
		                
		                    showAlertDialog(Feedback.this, "No Internet Connection",
		                            "You don't have internet connection.", false);
		               }
				}
			}
		});

		btnReset.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ednm.setText("");
				edcontact.setText("");
				edmsg.setText("");
			}
		});
		btncncl.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ednm.setText("");
				edcontact.setText("");
				edmsg.setText("");
				startActivity(new Intent(Feedback.this,MainActivity.class));

			}
		});
	}
	
	public void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
 
        alertDialog.setTitle(title);
 
        alertDialog.setMessage(message);
         
        alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);
 
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.show();
    }
	
	private class InsertData extends AsyncTask<String, Void, Boolean> {

    	//ProgressDialog dialog = new ProgressDialog(Home.this);
		@Override
		protected void onPreExecute() {	
			//dialog.setMessage("Sending Data.....");
			//dialog.show();
		}
		@Override
		protected Boolean doInBackground(String... urls) {
			
			for(String url1 : urls){
					try {
					ArrayList<NameValuePair> pairs= new ArrayList<NameValuePair>();
					pairs.add(new BasicNameValuePair("txtnm", ednm.getText().toString()));
					pairs.add(new BasicNameValuePair("txtnum", edcontact.getText().toString()));
					pairs.add(new BasicNameValuePair("txtmsg", edmsg.getText().toString()));
					
					HttpClient client = new DefaultHttpClient();
					HttpPost post = new HttpPost(url1);
					post.setEntity(new UrlEncodedFormEntity(pairs));
					
					HttpResponse response = client.execute(post);
				} catch (ClientProtocolException e) {
					
				} catch (IOException e) {
					Toast.makeText(Feedback.this, e.toString(), Toast.LENGTH_LONG).show();
					return false;
				}
			}			
			return true;
		}
		@Override
		protected void onPostExecute(Boolean result) {
			if(result==true)
			{
				Toast.makeText(Feedback.this, "Request send....", Toast.LENGTH_LONG).show();
				ednm.setText("");
				edcontact.setText("");
				edmsg.setText("");
				Intent i = new Intent(Feedback.this,MainActivity.class);
				startActivity(i);
			}
			//dialog.dismiss();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.feedback, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case android.R.id.home:
            onBackPressed();
		default:	
		}
		return super.onOptionsItemSelected(item);
	}
}
