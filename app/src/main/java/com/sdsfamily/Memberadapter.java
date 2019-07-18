package com.sdsfamily;

import java.util.ArrayList;
import java.util.HashMap;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Memberadapter extends BaseAdapter {

	// Declare Variables
	Context context;
	LayoutInflater inflater;
	ArrayList<HashMap<String, String>> data;

	HashMap<String, String> resultp = new HashMap<String, String>();

	public Memberadapter(Context context,
			ArrayList<HashMap<String, String>> arraylist) {
		this.context = context;
		data = arraylist;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		// Declare Variables
		TextView mem_name;
		TextView city;
		TextView mobile,stat;
		//TextView population;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View itemView = inflater.inflate(R.layout.list_member, parent, false);
		// Get the position
		resultp = data.get(position);

		// Locate the TextViews in listview_item.xml
		mem_name = (TextView) itemView.findViewById(R.id.txt_member_name);

		

		// Capture position and set results to the TextViews
		mem_name.setText(resultp.get(HeadDetail.MEMBER_NAME));


		// Capture ListView item click
		itemView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Get the position
				resultp = data.get(position);
				Intent intent = new Intent(context, MemberDetail.class);
				// Pass all data rank
				intent.putExtra("memid", resultp.get(HeadDetail.MEMBER_ID));
				intent.putExtra("headid", resultp.get(HeadDetail.HEAD_ID));
				intent.putExtra("membername", resultp.get(HeadDetail.MEMBER_NAME));
				// Pass all data country
				intent.putExtra("dob_age", resultp.get(HeadDetail.DOB_AGE));
				intent.putExtra("qualify", resultp.get(HeadDetail.QUALIFICATION));
				intent.putExtra("relation", resultp.get(HeadDetail.RELATION));
				intent.putExtra("maritial", resultp.get(HeadDetail.MAR_STATUS));
				// Pass all data population
				intent.putExtra("business",resultp.get(HeadDetail.BUSINESS));
				// Pass all data flag
				intent.putExtra("mobi", resultp.get(HeadDetail.MOBILE_NO));
				intent.putExtra("hdname", resultp.get(HeadDetail.HEAD_NAME));
				// Start SingleItemView Class
				context.startActivity(intent);

			}
		});
		return itemView;
	}
}
