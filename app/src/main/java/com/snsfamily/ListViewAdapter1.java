package com.snsfamily;

import java.util.ArrayList;
import java.util.HashMap;

import android.graphics.Typeface;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filterable;
import android.widget.SearchView;
import android.widget.TextView;

public class ListViewAdapter1 extends BaseAdapter implements Filterable {

	// Declare Variables
	Context context;
	LayoutInflater inflater;
	ArrayList<HashMap<String, String>> Headlist;
	ArrayList<HashMap<String, String>> data;
	//String fontPath = "fonts/guj.ttf";
	private Typeface tf;
	HashMap<String, String> resultp = new HashMap<String, String>();

  
	public ListViewAdapter1(Context context,ArrayList<HashMap<String, String>> arraylist) {
		this.context = context;
		data = arraylist;
		Headlist = arraylist;
		//this.tf = Typeface.createFromAsset(context.getAssets(), fontPath);
	}

	@Override
	public int getCount() {
		return Headlist.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
	public class ViewHolder {
		TextView head_name;
		TextView city;
		TextView mobile,stat;
		SearchView srchhd;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		// Declare Variables
		final ViewHolder holder;
		View itemView = convertView;
		
		if(itemView==null){
		
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		itemView = inflater.inflate(R.layout.list_head, parent, false);
		holder = new ViewHolder();
		resultp = data.get(position);
		holder.head_name = (TextView) itemView.findViewById(R.id.txt_head_name);
		itemView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder) itemView.getTag();	
		}
		//holder.head_name.setTypeface(tf);
		holder.head_name.setText(resultp.get(Head.HEAD_NAME));

		itemView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
			//	resultp = data.get(position);
			//	Intent intent = new Intent(context, HeadDetail.class);
				
				//intent.putExtra("headid", resultp.get(Head.HEAD_ID));
				//intent.putExtra("headname", resultp.get(Head.HEAD_NAME));
				//context.startActivity(intent);

			}
		});
		return itemView;
	}

	@Override
	public android.widget.Filter getFilter() {
		// TODO Auto-generated method stub
		return null;
	}
	
	class customfilter extends android.widget.Filter{

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			// TODO Auto-generated method stub
			
			FilterResults results = new FilterResults();
			
			if(constraint !=null && constraint.length()>0)
			{
				constraint = constraint.toString().toUpperCase();
				
				ArrayList<HashMap<String, String>> filters = new ArrayList<HashMap<String, String>>();
				
				for(int i=0;i<data.size();i++)
				{
					
				}
			}
			return null;
		}

		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {
			// TODO Auto-generated method stub
			
		}
	}
}
