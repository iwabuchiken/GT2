package gt2.main;

import gt2.listeners.ButtonOnClickListener;
import gt2.listeners.ButtonOnTouchListener;
import gt2.utils.Methods;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class GT2Activity extends Activity {
	
	// Time
	public static int timeLeft = 0;

	// Time set
	public static int timeSet = -1;

	public static TextView tv_time;
	public static Spinner sp_min;
	public static Spinner sp_sec;
	
	// Activity
	public static Activity gt2Activity;
	
	//
	public static boolean stopButtonStatus = false;
	
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
		Button bt_start = (Button) findViewById(R.id.main_bt_start);
		
		bt_start.setTag(Methods.ButtonTags.main_bt_start);
		
		bt_start.setOnTouchListener(new ButtonOnTouchListener(this));
		bt_start.setOnClickListener(new ButtonOnClickListener(this));
		
		/*----------------------------
		 * 2. "Stop"
			----------------------------*/
		Button bt_stop = (Button) findViewById(R.id.main_bt_stop);
		
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
		
		minutes.add(1); minutes.add(3); minutes.add(5);
		minutes.add(10); minutes.add(15); minutes.add(20);
		minutes.add(30); minutes.add(45); minutes.add(60);
		
		seconds.add(10); seconds.add(20); seconds.add(30);
		seconds.add(40); seconds.add(50);
		
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
		
	}//private void setup_spinner()


}