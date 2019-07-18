package com.sdsfamily;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MemberDetail extends Activity {

	String MEMBERID,HEADID,MEMBERNAME,DOB,qualify,rel,maritial,busi,mobile,hdnm,email,bloodgroup;
	TextView tvhname,tvmname,tvdob,tvqualify,tvrel,tvmar,tvbusiness,tvmob,tvemail,tvblood;
	ImageButton call,msg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_member_detail);
		
		ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1b75ba")));
        bar.setIcon(android.R.color.transparent);
        bar.setDisplayHomeAsUpEnabled(true);
		
		Intent i = getIntent();
		MEMBERID = i.getStringExtra("memid");
		HEADID = i.getStringExtra("headid");
		hdnm = i.getStringExtra("hdname");
		MEMBERNAME = i.getStringExtra("membername");
		DOB = i.getStringExtra("dob_age");
		qualify = i.getStringExtra("qualify");
		rel = i.getStringExtra("relation");
		maritial = i.getStringExtra("maritial");
		busi = i.getStringExtra("business");
		mobile = i.getStringExtra("mobi");
		email = i.getStringExtra("emailid");
		bloodgroup = i.getStringExtra("bloodGroup");
		
		tvmname = (TextView) findViewById(R.id.txtmem_name);
		tvhname = (TextView) findViewById(R.id.txthead_name);
		tvbusiness = (TextView) findViewById(R.id.txtbusiness);
		tvqualify = (TextView) findViewById(R.id.txtquali);
		tvdob = (TextView) findViewById(R.id.txtage);
		tvmar = (TextView) findViewById(R.id.txtmar_stat);
		tvmob = (TextView) findViewById(R.id.txtmobile);
		tvrel = (TextView) findViewById(R.id.txtrelation);
		tvemail = findViewById(R.id.txtemail_id);
		tvblood = findViewById(R.id.txtBloodGroup);
		msg = (ImageButton) findViewById(R.id.imgbtnmsg);
		
		tvmname.setText(MEMBERNAME);
		tvhname.setText(hdnm);
		tvrel.setText(rel);
		tvbusiness.setText(busi);
		tvqualify.setText(qualify);
		tvdob.setText(DOB);
		tvmar.setText(maritial);
		tvmob.setText(mobile);
		tvemail.setText(email);
		tvblood.setText(bloodgroup);
		
		msg.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent smsIntent = new Intent(Intent.ACTION_SENDTO,Uri.parse("sms:"+mobile)); 
				smsIntent.putExtra("sms_body", "Hello");
				startActivity(smsIntent);
			}
		});

		if(mobile.isEmpty()){
			msg.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.member_detail, menu);
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
