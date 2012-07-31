package gt2.main;

import gt2.listeners.ButtonOnClickListener;
import gt2.listeners.ButtonOnTouchListener;
import gt2.utils.Methods;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class GT2Activity extends Activity {
	
	// Time
	public static int timeLeft = 0;

	// Time set
	public static int timeSet = -1;

	public static TextView tv_time;
	public static Button bt_start;
	public static Button bt_stop; 
	
	public static Spinner sp_min;
	public static Spinner sp_sec;
	
	// Activity
	public static Activity gt2Activity;
	
	//
	public static boolean stopButtonStatus = false;

	public static int[] min_items = {0, 1, 3, 5, 10, 15, 20, 30, 45, 60, 90};
	public static int[] sec_items = {0, 10, 20, 30, 40, 50};

	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	/*----------------------------
		 * 1. super
		 * 2. Set content
		 * 2-1. Set activity
		 * 3. Spinner
		 * 4. Set up views
		 * 5. Listeners
			----------------------------*/
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        /*----------------------------
		 * 2-1. Set activity
			----------------------------*/
        gt2Activity = this;
        
        /*----------------------------
		 * 3. Spinner
			----------------------------*/
        setup_spinner();
        
        /*----------------------------
		 * 4. Set up views => tv_time
			----------------------------*/
		setup_views();
		
		/*----------------------------
		 * 5. Listeners
			----------------------------*/
		set_listeners();
		
		
    }//public void onCreate(Bundle savedInstanceState)

	private void set_listeners() {
		/*----------------------------
		 * 1. "Start"
		 * 2. "Stop"
			----------------------------*/
		bt_start = (Button) findViewById(R.id.main_bt_start);
		
		bt_start.setTag(Methods.ButtonTags.main_bt_start);
		
		bt_start.setOnTouchListener(new ButtonOnTouchListener(this));
		bt_start.setOnClickListener(new ButtonOnClickListener(this));
		
		/*----------------------------
		 * 2. "Stop"
			----------------------------*/
		bt_stop = (Button) findViewById(R.id.main_bt_stop);
		
		bt_stop.setTag(Methods.ButtonTags.main_bt_stop);
		
		bt_stop.setOnTouchListener(new ButtonOnTouchListener(this));
		bt_stop.setOnClickListener(new ButtonOnClickListener(this));
		
		
	}//private void set_listeners()

	private void setup_views() {
		// TODO 自動生成されたメソッド・スタブ
		tv_time = (TextView) this.findViewById(R.id.main_tv_time);
		
		
	}

	private void setup_spinner() {
		/*----------------------------
		 * 1. Get spinners
		 * 
			----------------------------*/
		/*----------------------------
		 * 1. Get spinners
			----------------------------*/
		sp_min = (Spinner) findViewById(R.id.main_sp_minutes);
		sp_sec = (Spinner) findViewById(R.id.main_sp_seconds);
		
		/*----------------------------
		 * 2. Get lists
			----------------------------*/
		List<Integer> minutes = new ArrayList<Integer>();
		List<Integer> seconds = new ArrayList<Integer>();
		
		for (int i = 0; i < min_items.length; i++) {
			
			minutes.add(min_items[i]);
			
		}//for (int i = 0; i < min_items.length; i++)
		
//		minutes.add(0);
//		minutes.add(1); minutes.add(3); minutes.add(5);
//		minutes.add(10); minutes.add(15); minutes.add(20);
//		minutes.add(30); minutes.add(45); minutes.add(60);
		
		for (int i = 0; i < sec_items.length; i++) {
			
			seconds.add(sec_items[i]);
			
		}//for (int i = 0; i < min_items.length; i++)

//		seconds.add(0);
//		seconds.add(10); seconds.add(20); seconds.add(30);
//		seconds.add(40); seconds.add(50);
		
		/*----------------------------
		 * 3. Setup adapters
			----------------------------*/
		// Min
		ArrayAdapter<Integer> adapterMin = new ArrayAdapter<Integer>(
//	              this, android.R.layout.simple_spinner_item, minutes);
				this, R.layout.spinner_row, minutes);
		
		// Sec
		ArrayAdapter<Integer> adapterSec = new ArrayAdapter<Integer>(
//	              this, android.R.layout.simple_spinner_item, seconds);
				this, R.layout.spinner_row, seconds);
		
		// Drop down view
		adapterMin.setDropDownViewResource(
//				android.R.layout.simple_spinner_dropdown_item);
				R.layout.spinner_dropdown_row);
		
		adapterSec.setDropDownViewResource(
//				android.R.layout.simple_spinner_dropdown_item);
				R.layout.spinner_dropdown_row);

		
        /*----------------------------
		 * 4. Set adapter
			----------------------------*/
		sp_min.setAdapter(adapterMin);
		sp_sec.setAdapter(adapterSec);

//		sp_min.setSelection(3);
		
		sp_min.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View v,
					int position, long id) {
				// TODO 自動生成されたメソッド・スタブ
				
				int min = (Integer) parent.getItemAtPosition(position);
				
				int sec = (Integer) sp_sec.getSelectedItem();
				
				GT2Activity.timeLeft = min * 60 + sec;
				
				Methods.showTimeToView(gt2Activity, GT2Activity.timeLeft);
				
//				// debug
//				Toast.makeText(gt2Activity, "min=" + min, 2000).show();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO 自動生成されたメソッド・スタブ
				
			}
			
		});

		sp_sec.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View v,
					int position, long id) {
				/*----------------------------
				 * 1. Get min and sec
				 * 2. Set timeLeft
				 * 3. Show time
					----------------------------*/
				int min = (Integer) sp_min.getSelectedItem();
				
				int sec = (Integer) parent.getItemAtPosition(position);
				
				/*----------------------------
				 * 2. Set timeLeft
					----------------------------*/
				GT2Activity.timeLeft = min * 60 + sec;
				
				/*----------------------------
				 * 3. Show time
					----------------------------*/
				Methods.showTimeToView(gt2Activity, GT2Activity.timeLeft);
				
//				// debug
//				Toast.makeText(gt2Activity, "min=" + min, 2000).show();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO 自動生成されたメソッド・スタブ
				
			}
			
		});
		
		
	}//private void setup_spinner()

	@Override
	protected void onStart() {
		// TODO 自動生成されたメソッド・スタブ
		super.onStart();
		
//		/*----------------------------
//		 * 1. AlarmDialog # TextView
//			----------------------------*/
//		if (AlarmDialog.tv != null) {
//		
//			AlarmDialog.tv.setText(this.getString(R.string.alarm_dialog_tv));
//			
//		}//if (AlarmDialog.tv != null)
//		
//		
//		// Log
//		Log.d("GT2Activity.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "onStart");
		

	}//protected void onStart()

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
		
		GT2Activity.bt_start.setEnabled(true);
		GT2Activity.bt_start.setTextColor(Color.BLUE);
		
	}//protected void onResume()
}