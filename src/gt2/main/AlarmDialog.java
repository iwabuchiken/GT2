package gt2.main;

import gt2.listeners.ButtonOnClickListener;
import gt2.listeners.ButtonOnTouchListener;
import gt2.utils.Methods;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AlarmDialog extends Activity {
	/******************************************************************* 
	 * Class members
	 *******************************************************************/
	//
	public static Vibrator vib;
	
	//
	Context mContext;
	
	//
	Ringtone rt;
	
	// alarmdialog.xml : TextView
	public static TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		/*----------------------------
		 * Steps
		 * 1. super
		 * 2. Set content
			----------------------------*/
		
		super.onCreate(savedInstanceState);
		
		/*----------------------------
		 * // Content view
			----------------------------*/
		setContentView(R.layout.alarmdialog);
	
		//
		mContext = this;

		/*----------------------------
		 * 2. Set alarm message
			----------------------------*/
		// 
		vib = (Vibrator) mContext.getSystemService(mContext.VIBRATOR_SERVICE);
		
	}//protected void onCreate(Bundle savedInstanceState)

	protected void do_before_finish(Activity actv) {
		/*----------------------------
		 * Steps
		 * 1. Set time to the text view
		 * 2. Reset timeLeft
		 * 3. Set enabled => "Start"
			----------------------------*/
		
	}//protected void do_before_finish(Activity actv)

	@Override
	protected void onResume() {
		/*----------------------------
		 * Steps
		 * 1. Super
		 * 1-2. Set listener
		 * 1-3. Set message
		 * 2. Vibrate
			----------------------------*/
		
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
		
		/*----------------------------
		 * 1-2. Set listener
			----------------------------*/
		set_listener();
		
		/*----------------------------
		 * 1-3. Set message
			----------------------------*/
		// main.xml : EditText
		EditText et = (EditText) GT2Activity.gt2Activity.findViewById(R.id.main_et_message);
		
		String message = et.getText().toString();
		
		// Log
		Log.d("AlarmDialog.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "message: " + message);
		
		Log.d("AlarmDialog.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "GT2Activity.timeSet: " + GT2Activity.timeSet);

		// alarmdialog.xml : TextView
		tv = (TextView) findViewById(R.id.alarm_dialog_tv);
		
		String tv_text = tv.getText().toString();
		
		if (message.length() < 1) {

//			tv_text += " : " + GT2Activity.timeSet;
			tv_text = this.getString(R.string.alarm_dialog_tv) + " :: " + 
							Methods.convert_millSeconds2digitsLabel(GT2Activity.timeSet * 1000);
			
			
		} else {//if (condition)
			
//			tv_text = message + " : " + GT2Activity.timeSet;
			tv_text = message + " :: " + 
					Methods.convert_millSeconds2digitsLabel(GT2Activity.timeSet * 1000);
			
		}//if (condition)
		
		
		tv.setText(tv_text);
		
		
		/*----------------------------
		 * 2. Vibrate
			----------------------------*/
		vib.vibrate(new long[]{0, 1000, 500, 1000, 500, 1000}, -1);
			
	}//protected void onResume()

	private void set_listener() {
		/*----------------------------
		 * 
			----------------------------*/
		Button bt_ok = (Button) findViewById(R.id.alarm_dialog_bt);
		
		bt_ok.setTag(Methods.ButtonTags.alarmdialog_bt_ok);
		
		bt_ok.setOnTouchListener(new ButtonOnTouchListener(this));
		bt_ok.setOnClickListener(new ButtonOnClickListener(this));

		
	}//private void set_listener()

}//public class AlarmDialog extends Activity
