package com.snsfamily;

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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

public class Head extends Activity {

	JSONObject jsonobject;
	JSONArray jsonarray;
	ListView listview;
	ListViewAdapter adapter;
	ProgressDialog mProgressDialog;
	ArrayList<HashMap<String, String>> arraylist;
	static String HEAD_ID = "head_id";
	static String HEAD_NAME = "hname";
	static String HOME_TOWN = "home_town";
	static String INCOME = "monthly_income";
	static String KULDEVI_NAME = "kuldevi_name";
	static String KULDEVI_CITY = "kuldevi_city";
	static String HOME_ADD = "home_add";
	static String OFF_ADD = "office_add";
	private static final String TAG_SUCCESS = "success";
    int success;
    SearchView srchhd;
    View myFragmentView; 
    String hdid[];      
    String hdnm[];
    EditText edtsearch;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_head);
		
		ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1b75ba")));
        bar.setIcon(android.R.color.transparent);
        bar.setDisplayHomeAsUpEnabled(true);
		
        edtsearch = (EditText) findViewById(R.id.edthdsearch);
       
		new DownloadJSON().execute();
		
		edtsearch.addTextChangedListener(new TextWatcher() {
        	String globalQuery="";
            ArrayList<HashMap<String, String>> globalList = new ArrayList<HashMap<String, String>>();
            ListViewAdapter globalListAdapter,globalAdapter=null;
			
        	@Override
			public void afterTextChanged(Editable s) {
        		if (s.toString().length() > 0) {
                    // Search
        			globalQuery=s.toString();
        			//This method will filter all your categories just calling this method.
        			filteredList();
                } else {
                    globalQuery="";
//If the text is empty the list all the content of the list adapter
                justListAll();
                }
            }
        	public void justListAll()
        	{
        	    globalAdapter = new ListViewAdapter(Head.this, globalList);
        	                    listview.setAdapter(adapter);
        	   // setListAdapter(globalAdapter);
        	    ((ListViewAdapter)globalAdapter).notifyDataSetChanged();
        	}

        	public void filteredList()
        	{
        	//First of all checks for our globalList is not a null one.
        	if(globalList!=null)
        	            {

        	    ArrayList<HashMap<String, String>> tempList = new ArrayList<HashMap<String, String>>();
        	//Checks our search term is empty or not.
        	    if(!globalQuery.trim().equals(""))
        	    {
        	        boolean isThereAnyThing=false;
        	    for(int i=0;i<globalList.size();i++)
        	    {
        	//Get the value of globalList that is HashMap indexed at i.
        	        HashMap<String, String> tempMap=globalList.get(i);
        	//Now getting all your HashMap values into local variables.
        	        String h_id=tempMap.get(Head.HEAD_ID);
        	        String h_name=tempMap.get(Head.HEAD_NAME);
        	    
        	//Now all the core checking goes here for which one of these was typed like rank or country or population .....
        	                if(h_id.regionMatches(true, 0, globalQuery,0, globalQuery.length()) || h_name.regionMatches(true, 0, globalQuery,0, globalQuery.length()))
        	                {
        	//If anything matches then it will add to tempList
        	                    tempList.add(tempMap);
        	                    isThereAnyThing=true;
        	                }
        	    } 
        	//Checks for is there anything matched from the ArrayList with the user type search query
        	    if(isThereAnyThing)
        	    {
        	//then set the globalAdapter with the new HashMaps tempList
        	     globalAdapter = new ListViewAdapter(Head.this, tempList);
        	                    listview.setAdapter(globalAdapter);
        	   // setListAdapter(globalAdapter);
        	    ((ListViewAdapter)globalAdapter).notifyDataSetChanged();
        	    }
        	    else
        	    {
        	//If else set list adapter to null
        	        //setListAdapter(null);
        	    }
        	}
        	else
        	{
        	    // Do something when there's no input
        	    if(globalAdapter==null)
        	    {
        	//If no user inputs then it will list everything in the globalList.
        	justListAll();
        	    }
        	    else
        	    {
        	         runOnUiThread(new Runnable()
        	         {
        	             public void run()
        	             {

        	    ((ListViewAdapter)globalAdapter).notifyDataSetChanged();

        	             }
        	         });
        	    }
        	         }
        	     // updating listview
        	            }
			}
        	@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				 ArrayList<HashMap<String, String>> arrayTemplist = new ArrayList<HashMap<String, String>>();
                 String searchString = edtsearch.getText().toString();
                 if(searchString.equals(""))
                 {
                     new DownloadJSON().execute();
                 }
                 else
                 {
                	 for (int i = 0; i < arraylist.size(); i++) {
                         String currentString = arraylist.get(i).get(Head.HEAD_NAME).toLowerCase();
                         if (currentString.contains(searchString)) {
               	          arrayTemplist.add(arraylist.get(i));
                         }
                     }
                     adapter = new ListViewAdapter(Head.this, arrayTemplist);
                     listview.setAdapter(adapter);
                     }
			}
		});	 	
	}
	private class DownloadJSON extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mProgressDialog = new ProgressDialog(Head.this);
			mProgressDialog.setTitle("Load Head Member");
			mProgressDialog.setMessage("Loading...");
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.show();
		}
		@Override
		protected Void doInBackground(Void... params) {
			
			arraylist = new ArrayList<HashMap<String, String>>();
			jsonobject = JSONfunctions
					.getJSONfromURL("http://keypointservices.net/darji/android/readhead.php");

			try {				
				jsonarray = jsonobject.getJSONArray("Head");
				success = jsonobject.getInt(TAG_SUCCESS);

				for (int i = 0; i < jsonarray.length(); i++) {
					HashMap<String, String> map = new HashMap<String, String>();
					jsonobject = jsonarray.getJSONObject(i);
					map.put("head_id", jsonobject.getString("head_id"));
					map.put("hname", jsonobject.getString("hname"));
					map.put("home_town", jsonobject.getString("home_town"));
					map.put("monthly_income", jsonobject.getString("monthly_income"));
					map.put("kuldevi_name", jsonobject.getString("kuldevi_name"));
					map.put("kuldevi_city", jsonobject.getString("kuldevi_city"));
					map.put("home_add", jsonobject.getString("home_add"));
					map.put("office_add", jsonobject.getString("office_add"));
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
			mProgressDialog.dismiss();
			listview = (ListView) findViewById(R.id.listview_search);
			adapter = new ListViewAdapter(Head.this, arraylist);
			listview.setAdapter(adapter);
			adapter.notifyDataSetChanged();
			listview.setEmptyView(findViewById(R.id.txtempty));
			
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.head, menu);
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