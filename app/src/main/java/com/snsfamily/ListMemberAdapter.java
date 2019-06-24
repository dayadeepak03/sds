package com.snsfamily;

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

public class ListMemberAdapter extends BaseAdapter {

	// Declare Variables
	Context context;
	LayoutInflater inflater;
	ArrayList<HashMap<String, String>> data;

	HashMap<String, String> resultp = new HashMap<String, String>();

	public ListMemberAdapter(Context context,
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
		TextView member_name;

		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View itemView = inflater.inflate(R.layout.list_member, parent, false);

		resultp = data.get(position);

		member_name = (TextView) itemView.findViewById(R.id.txt_member_name);

		member_name.setText(resultp.get(Member.MEMBER_NAME));


		// Capture ListView item click
		itemView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Get the position
				resultp = data.get(position);
				Intent intent = new Intent(context, MemberDetail.class);
				// Pass all data rank
				intent.putExtra("memid", resultp.get(Member.MEMBER_ID));
				intent.putExtra("headid", resultp.get(Member.HEAD_ID));
				intent.putExtra("membername", resultp.get(Member.MEMBER_NAME));
				// Pass all data country
				intent.putExtra("dob_age", resultp.get(Member.DOB_AGE));
				intent.putExtra("qualify", resultp.get(Member.QUALIFICATION));
				intent.putExtra("relation", resultp.get(Member.RELATION));
				intent.putExtra("maritial", resultp.get(Member.MAR_STATUS));
				// Pass all data population
				intent.putExtra("business",resultp.get(Member.BUSINESS));
				// Pass all data flag
				intent.putExtra("mobi", resultp.get(Member.MOBILE_NO));
				intent.putExtra("hdname", resultp.get(Member.HEAD_NAME));
				intent.putExtra("emailid",resultp.get(Member.EMAILID));
				intent.putExtra("bloodGroup",resultp.get(Member.BLOOD_GROUP));
				// Start SingleItemView Class
				context.startActivity(intent);

			}
		});
		return itemView;
	}
}
