package gt2.utils;

import gt2.main.R;
import gt2.main.TimerItem;
import gt2.main.R.id;
import gt2.main.R.layout;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TimerItemListAdapter extends ArrayAdapter<TimerItem> {

	// Inflater
	LayoutInflater inflater;

	//
	Activity actv;

	public TimerItemListAdapter(Context context, int resourceId, List<TimerItem> list) {
		super(context, resourceId, list);

		this.actv = (Activity) context;

		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		// Log
		Log.d("TimerItemListAdapter.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "message");
		
		
		/*----------------------------
		 * Steps
		 * 1. Get view
		 * 2. Get item
		 * 3. Set data to view
			----------------------------*/
		/*----------------------------
		 * 1. Get view
			----------------------------*/
		View v;

    	if (convertView != null) {
			v = convertView;
		} else {//if (convertView != null)

			v = inflater.inflate(R.layout.list_row_timer_history, null);

		}//if (convertView != null)

    	/*----------------------------
		 * 2. Get item
			----------------------------*/
    	TimerItem ti = getItem(position);

    	/*----------------------------
		 * 3. Set data to view
		 * 		1. Message
		 * 		2. Duration
		 * 		3. Date
			----------------------------*/
		TextView tv_message = (TextView) v.findViewById(R.id.list_row_timer_history_tv_message);

		tv_message.setText(ti.getMessage());

		/*----------------------------
		 * 3.2. Duration
			----------------------------*/
		TextView tv_dur = (TextView) v.findViewById(R.id.list_row_timer_history_tv_duration);

//		tv_dur.setText(String.valueOf(ti.getDuration()));
//		tv_dur.setText(Methods.convert_millSeconds2digitsLabel(ti.getDuration()));
		tv_dur.setText(Methods.convert_millSeconds2digitsLabel(ti.getDuration() * 1000));

//		// Log
//		Log.d("TimerItemListAdapter.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "ti.getDuration(): " + ti.getDuration());
//		
//		Log.d("TimerItemListAdapter.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "convert: " + Methods.convert_millSeconds2digitsLabel(ti.getDuration()));
		
		
		/*----------------------------
		 * 3.3. Date
			----------------------------*/
		TextView tv_created_at = (TextView) v.findViewById(R.id.list_row_timer_history_tv_created_at);

		tv_created_at.setText(Methods.convert_longDate2FormattedString(ti.getCreated_at()));
		
//		// Log
//		Log.d("TimerItemListAdapter.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "ti.getDuration(): " + ti.getDuration());
		

//		return null;
		return v;

//		return super.getView(position, convertView, parent);
	}//public View getView(int position, View convertView, ViewGroup parent)

}
