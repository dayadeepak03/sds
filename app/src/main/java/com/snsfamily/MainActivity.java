package com.snsfamily;


import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends Activity {

	ImageButton btsbh,btnsbm,btnmsg;
	ConnectionDetector cd;
	Boolean isInternetPresent = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1b75ba")));
        bar.setIcon(android.R.color.transparent);
        //bar.setDisplayHomeAsUpEnabled(true);
        
        btsbh = (ImageButton)findViewById(R.id.btnsbh);
        btnsbm = (ImageButton)findViewById(R.id.btnsbm);
        btnmsg = (ImageButton) findViewById(R.id.imgbtnmsg);
        cd = new ConnectionDetector(getApplicationContext());
        
        btsbh.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				isInternetPresent = cd.isConnectingToInternet();
				
				 if (isInternetPresent) {
					 Intent i = new Intent(MainActivity.this,Head.class);
					 startActivity(i);
				 }
				  else {  
	                
	                    showAlertDialog(MainActivity.this, "No Internet Connection",
	                            "You don't have internet connection.", false);
	               }
			}
		});
        
        btnsbm.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				isInternetPresent = cd.isConnectingToInternet();
				
				 if (isInternetPresent) {
					 Intent i = new Intent(MainActivity.this,Member.class);
					 startActivity(i);
				 }
				  else {  
	                
	                    showAlertDialog(MainActivity.this, "No Internet Connection",
	                            "You don't have internet connection.", false);
	               }
			}
		});
        btnmsg.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this,Feedback.class);
				startActivity(i);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
    	/*switch (item.getItemId()) {
			case android.R.id.home:
				onBackPressed();
			default:
		}*/
		return super.onOptionsItemSelected(item);
    }
}
