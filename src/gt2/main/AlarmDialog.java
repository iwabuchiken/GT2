package gt2.main;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class AlarmDialog extends Activity {
	/******************************************************************* 
	 * Class members
	 *******************************************************************/
	//
	Vibrator vib;
	
	//
	Context mContext;
	
	//
	Ringtone rt;
	
	//
	TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. Set alarm message
			----------------------------*/
		
		super.onCreate(savedInstanceState);
		
		// Content view
//		setContentView(R.layout.alarmdialog);
	
		//
		mContext = this;

		/*----------------------------
		 * 2. Set alarm message
			----------------------------*/
		//
//		tv = (TextView) this.findViewById(R.id.textView1);
		
		//
//		if (S_01_TimerActivity.alarmMessage != null) {
//			tv.setText(S_01_TimerActivity.alarmMessage);
//		}//if (S_01_TimerActivity.alarmMessage != null)
//			tv.setText("时间到了!");
		
		// Initialize vibrator
		vib = (Vibrator) mContext.getSystemService(mContext.VIBRATOR_SERVICE);
		
		// Set listener
//		((Activity) mContext).findViewById(R.id.button1).setOnClickListener(new OnClickListener(){
//
//			@Override
//			public void onClick(View v) {
//				// Stop ringtone
//				if (rt != null) {
//					//
//					rt.stop();
//					
//				}//if (rt != null)
//				
//				// Stop vibrator
//				vib.cancel();
//				
//				//
//				S_01_TimerActivity.sb.setEnabled(true);
//				
//				do_before_finish(AlarmDialog.this);
//				
//				// Finish
//				((Activity) mContext).finish();
//				
//			}//public void onClick(View v)
//		});//((Activity) mContext).findViewById(R.id.button1).setOnClickListener()
		
		
		
		
		
	}//protected void onCreate(Bundle savedInstanceState)

	protected void do_before_finish(Activity actv) {
		/*----------------------------
		 * Steps
		 * 1. Set time to the text view
		 * 2. Reset timeLeft
		 * 3. Set enabled => "Start"
			----------------------------*/
		
//		S_01_TimerActivity.showTime(S_01_TimerActivity.timeSet * 60 * 1000);
//		S_01_TimerActivity.showTime(S_01_TimerActivity.timeSet);
//		S_01_TimerActivity.showTime(S_01_TimerActivity.timeSet * 60);
		
//		S_01_TimerActivity.timeLeft = S_01_TimerActivity.timeSet;
		
//		// Log
//		Log.d("AlarmDialog.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "S_01_TimerActivity.timeSet: " + S_01_TimerActivity.timeSet);
		
		/*----------------------------
		 * 3. Set enabled => "Start"
			----------------------------*/
//		S_01_TimerActivity.btnStart.setEnabled(true);
		
		
	}//protected void do_before_finish(Activity actv)

	@Override
	protected void onResume() {
		/*----------------------------
		 * Steps
		 * 1. Super
		 * 2. Prefs
			----------------------------*/
		
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
		
		/*----------------------------
		 * 2. Prefs
			----------------------------*/
		//
		SharedPreferences prefs;
		
		// Get prefs
//		prefs = this.getSharedPreferences("CountDownTimerPrefs", 0);
		prefs = this.getSharedPreferences("CountdownTimerPrefs", 0);
		
		// Get string
		String fn = prefs.getString("alarm", "");
		
		// Log
		Log.d("AlarmDialog.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "fn => " + fn);
		
		
		// Ringtone
		if (fn != "") {
			//
			rt = RingtoneManager.getRingtone(this, Uri.parse(fn));
		
			// Log
			Log.d("AlarmDialog.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "rt => " + rt.toString());
			
			
			// Play
			if (rt != null && !rt.isPlaying()) {
				// Play
				rt.play();
				
			}//if (rt != null && !rt.isPlaying())
			
		}//if (fn != "")
		
		// Vibrator
		if (prefs.getBoolean("vibrator", true)) {
			//
			vib.vibrate(new long[]{0, 1000, 500, 1000, 500, 1000}, -1);
			
		}//if (prefs.getBoolean("vibrator", true))
		
	}//protected void onResume()

}//public class AlarmDialog extends Activity
