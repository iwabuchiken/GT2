package gt2.main;

import gt2.listeners.ButtonOnClickListener;
import gt2.listeners.ButtonOnTouchListener;
import gt2.utils.DBUtils;
import gt2.utils.Methods;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class GT2Activity extends Activity {

	static final int REQUEST_CODE_timer_history = 0;
	
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

	public static Vibrator vib;	
	
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
		 * 5-2. Vibrator
		 * 6. Database setup
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
		
		/*----------------------------
		 * 5-2. Vibrator
			----------------------------*/
		vib = (Vibrator) this.getSystemService(this.VIBRATOR_SERVICE);
		
		/*----------------------------
		 * 6. Database setup
			----------------------------*/
//		setup_database();
		
		
    }//public void onCreate(Bundle savedInstanceState)

	private void setup_database() {
		/*----------------------------
		 * 1. Get db
		 * 2. Create a table
		 * 3. Query
		 * 
		 * 9. Close db
			----------------------------*/
		DBUtils dbu = new DBUtils(this, DBUtils.dbName);
		
		//
		SQLiteDatabase wdb = dbu.getWritableDatabase();

		boolean res = dbu.createTable(
									wdb, 
									DBUtils.tableName_timer_history, 
									DBUtils.cols_timer_history, 
									DBUtils.types_timer_history);
		
		/*----------------------------
		 * 3. Query
			----------------------------*/
		String s = "SELECT * FROM " + DBUtils.tableName_timer_history;
		
		Cursor c = wdb.rawQuery(s, null);
		
		this.startManagingCursor(c);
		
		// Log
		Log.d("GT2Activity.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "c.getCount(): " + c.getCount());
		
		c.moveToFirst();
		
		String message = c.getString(1);
		long dur = c.getLong(2);
		long at = c.getLong(3);
		
		// Log
		Log.d("GT2Activity.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "msg: " + message + "/" + "dur: " + dur + "/" + "at: " + at);
		
		
		/*----------------------------
		 * 9. Close db
			----------------------------*/
		wdb.close();
		
	}//private void setup_database()

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

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
		// 
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.menu_main, menu);
		
		return super.onCreateOptionsMenu(menu);
	}//public boolean onCreateOptionsMenu(Menu menu)

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		/*----------------------------
		 * Steps
		 * 1. 
			----------------------------*/
		
		vib.vibrate(Methods.vibLength_click);
		
        switch (item.getItemId()) {
			/*----------------------------
			 * Steps
			 * 1. 
			 * 9. Default
				----------------------------*/
        	/*----------------------------
			 * 1. case 0	=> 
				----------------------------*/
            case R.id.gt2actv_menu_history://--------------------------------
            	
            	Intent i = new Intent();
            	
            	i.setClass(this, TimerHistoryActivity.class);
            	
//            	startActivity(i);
//            	this.startActivityForResult(i, 0);
            	startActivityForResult(i, REQUEST_CODE_timer_history);
            	
            	break;//case 0
            
            	
        }//switch (item.getItemId())
        
		return true;
    }//public boolean onOptionsItemSelected(MenuItem item)

	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO 自動生成されたメソッド・スタブ
		super.onActivityResult(requestCode, resultCode, data);
		
		// Log
		Log.d("GT2Activity.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "requestCode: " + requestCode);
		
//			if(resultCode==RESULT_OK){
//				bitmap=loadImage(data.getStringExtra("fn"));
//				canvas = new Canvas(bitmap);
//				ImageView iv=(ImageView)this.findViewById(R.id.imageView1);
//				iv.setImageBitmap(bitmap);
//			}

		
	}//protected void onActivityResult(int requestCode, int resultCode, Intent data)


}