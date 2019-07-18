package com.sdsfamily;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class Testing extends Activity {

	JSONObject jsonobject;
	JSONArray jsonarray;
	ListView listview;
	ListViewAdapter adapter;
	ProgressDialog mProgressDialog;
	ArrayList<HashMap<String, String>> arraylist;
	private static final String TAG_SUCCESS = "success";
    int success;
    static String HEAD_NAME = "hname";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_testing);
		
		ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1b75ba")));
        bar.setIcon(android.R.color.transparent);
        bar.setDisplayHomeAsUpEnabled(true);
        
        new DownloadJSON().execute();
	}
	
	private class DownloadJSON extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			//mProgressDialog = new ProgressDialog(Head.this);
			//mProgressDialog.setTitle("Load Head Member");
			//mProgressDialog.setMessage("Loading...");
			//mProgressDialog.setIndeterminate(false);
			//mProgressDialog.show();
		}
		@Override
		protected Void doInBackground(Void... params) {
			
			arraylist = new ArrayList<HashMap<String, String>>();
			jsonobject = JSONfunctions
					.getJSONfromURL("http://darji.keypointservices.net/android/read_guj.php");

			try {				
				jsonarray = jsonobject.getJSONArray("Head");
				success = jsonobject.getInt(TAG_SUCCESS);

				for (int i = 0; i < jsonarray.length(); i++) {
					HashMap<String, String> map = new HashMap<String, String>();
					jsonobject = jsonarray.getJSONObject(i);
					map.put("head_id", jsonobject.getString("head_id"));
					map.put("hname", jsonobject.getString("hname"));
				
					arraylist.add(map);
				}				
			} catch (JSONException e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPostExecute(Void args) {
			listview = (ListView) findViewById(R.id.listtest);
			adapter = new ListViewAdapter(Testing.this, arraylist);
			listview.setAdapter(adapter);
			adapter.notifyDataSetChanged();	
			//listview.setEmptyView(findViewById(R.id.txtempty));
			//mProgressDialog.dismiss();
		}
	}	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.testing, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		switch (item.getItemId()) {
		case android.R.id.home:
            onBackPressed();
			return true;
		default:	
		}
		return super.onOptionsItemSelected(item);
	}
}
