package com.sdsfamily;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

public class HeadDetail extends Activity {

	JSONObject jsonobject;
	JSONArray jsonarray;
	ListView listview;
	Memberadapter adapter;
	ProgressDialog mProgressDialog;
	ArrayList<HashMap<String, String>> arraylist;
	static String MEMBER_NAME = "mname";
	static String MEMBER_ID = "mem_id";
	static String HEAD_ID = "head_id";
	static String HEAD_NAME = "hname";
	static String DOB_AGE = "dob";
	static String QUALIFICATION = "qualification";
	static String RELATION = "relation";
	static String MAR_STATUS = "mar_status";
	static String BUSINESS = "business";
	static String MOBILE_NO= "mobile_no";
	private static final String TAG_SUCCESS = "success";
    int success;
	String HEADID,HEADNAME,hometown,income,kuldeviname,kuldevicity,homeadd,officeadd;
	TextView tvhname,tvhometown,tvincome,tvkuldeviname,tvkuldevicity,tvhome,tvoffice,tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_head_detail);
		
		ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1b75ba")));
        bar.setIcon(android.R.color.transparent);
        bar.setDisplayHomeAsUpEnabled(true);
		
		Intent i = getIntent();
		HEADID = i.getStringExtra("headid");
		HEADNAME = i.getStringExtra("headname");
		hometown = i.getStringExtra("hometown");
		income = i.getStringExtra("income");
		kuldeviname = i.getStringExtra("kuldeviname");
		kuldevicity = i.getStringExtra("kuldevicity");
		homeadd = i.getStringExtra("homeadd");
		officeadd = i.getStringExtra("offadd");
		
		tvhname = (TextView)findViewById(R.id.txthname);
		tvhometown = (TextView)findViewById(R.id.txthometown);
		tvincome = (TextView)findViewById(R.id.txtMonthlyIncome);
		tvkuldeviname  = findViewById(R.id.txtKuldeviName);
		tvkuldevicity = findViewById(R.id.txtKuldeviCity);
		tvhome =(TextView)findViewById(R.id.txthomeadd);
		tvoffice = (TextView)findViewById(R.id.txtoffadd);
		
		tvhname.setText(HEADNAME);
		tvhometown.setText(hometown);
		tvincome.setText(income);
		tvkuldeviname.setText(kuldeviname);
		tvkuldevicity.setText(kuldevicity);
		tvhome.setText(homeadd);
		tvoffice.setText(officeadd); 
		
		new DownloadJSON().execute();
	}

	private class DownloadJSON extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
		}
		@Override
		protected Void doInBackground(Void... params) {
			
			arraylist = new ArrayList<HashMap<String, String>>();
			jsonobject = JSONfunctions
					.getJSONfromURL("http://keypointservices.net/darji/android/readmember.php?id="+HEADID);

			try {				
				jsonarray = jsonobject.getJSONArray("Member");
				success = jsonobject.getInt(TAG_SUCCESS);

				for (int i = 0; i < jsonarray.length(); i++) {
					HashMap<String, String> map = new HashMap<String, String>();
					jsonobject = jsonarray.getJSONObject(i);
					map.put("mem_id", jsonobject.getString("mem_id"));
					map.put("head_id", jsonobject.getString("head_id"));
					map.put("mname", jsonobject.getString("mname"));
					map.put("dob", jsonobject.getString("dob"));
					map.put("qualification", jsonobject.getString("qualification"));
					map.put("relation", jsonobject.getString("relation"));
					map.put("mar_status", jsonobject.getString("mar_status"));
					map.put("business", jsonobject.getString("business"));
					map.put("mobile_no", jsonobject.getString("mobile_no"));
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
			listview = (ListView) findViewById(R.id.list_member);
			adapter = new Memberadapter(HeadDetail.this, arraylist);
			listview.setAdapter(adapter);
			adapter.notifyDataSetChanged();	
			//listview.setEmptyView(findViewById(R.id.txtempty));
		}
	}	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.head_detail, menu);
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