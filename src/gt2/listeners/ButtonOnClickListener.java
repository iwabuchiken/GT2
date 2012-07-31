package gt2.listeners;

import gt2.main.AlarmDialog;
import gt2.main.GT2Activity;
import gt2.utils.Methods;

import gt2.main.R;

import java.io.File;


import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ButtonOnClickListener implements OnClickListener {
	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;

	//
	Vibrator vib;
	
	//
	int position;
	
	//
	ListView lv;
	
	public ButtonOnClickListener(Activity actv) {
		//
		this.actv = actv;
		
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
	}

	public ButtonOnClickListener(Activity actv, int position) {
		//
		this.actv = actv;
		this.position = position;
		
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
		
		
		
	}//public ButtonOnClickListener(Activity actv, int position)

	public ButtonOnClickListener(Activity actv, ListView lv) {
		// 
		this.actv = actv;
		this.lv = lv;
		
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
	}

	@Override
	public void onClick(View v) {
		//
		Methods.ButtonTags tag_name = (Methods.ButtonTags) v.getTag();

		vib.vibrate(Methods.vibLength_click);
		
		//
		switch (tag_name) {
		
		case main_bt_start://---------------------------------------------------------
			/*----------------------------
			 * 1. Get time
			 * 2. "Start" button => set up
			 * 3. Reset TextView if the message view is empty
				----------------------------*/
			
			int min = (Integer) GT2Activity.sp_min.getSelectedItem();
			int sec = (Integer) GT2Activity.sp_sec.getSelectedItem();
			
			if (min == 0 && sec == 0) {
				
				// debug
				Toast.makeText(actv, "���Ԃ��I������Ă܂���", 2000).show();
				
				break;
				
			}//if ()
			
			// Buttons
			GT2Activity.bt_start.setEnabled(false);
			GT2Activity.bt_start.setTextColor(Color.GRAY);
			
			GT2Activity.bt_stop.setEnabled(true);
			
			/*----------------------------
			 * 3. Reset TextView if the message view is empty
				----------------------------*/
			EditText et = (EditText) actv.findViewById(R.id.main_et_message);
			
			
			if (et.getText().toString().equals("")) {
				if(!(AlarmDialog.tv == null)) {
					
					AlarmDialog.tv.setText(actv.getString(R.string.alarm_dialog_tv));
					
				}
				
			}//if (AlarmDialog.tv != null)
			
//			Methods.startTimer(actv);
			Methods.startTimerService(actv);
			
			break;

		case main_bt_stop://------------------------------------------------------
			
			if (GT2Activity.stopButtonStatus == false && GT2Activity.timeLeft != 0) {
				
//				GT2Activity.timeLeft = 0;
				
				min = (Integer) GT2Activity.sp_min.getSelectedItem();
				
				sec = (Integer) GT2Activity.sp_sec.getSelectedItem();
				
				GT2Activity.timeLeft = min * 60 + sec;

				
				Methods.showTime(actv, GT2Activity.timeLeft);
				
				break;// case main_bt_stop
				
			} //if (GT2Activity.stopButtonStatus == false)
			
			// Buttons
			GT2Activity.bt_start.setEnabled(true);
			GT2Activity.bt_start.setTextColor(Color.BLUE);
			
			GT2Activity.bt_stop.setEnabled(true);
			
			Methods.stopTimer(actv);
			
			break;// case main_bt_stop
			
		case alarmdialog_bt_ok://-------------------------------------------------
			/*----------------------------
			 * 1. Vibrate => Cancel
			 * 2. "Start" button
			 * 3. AlarmDialog # TextView
			 * 4. Finish activity => AlarmDialog
				----------------------------*/
			
			AlarmDialog.vib.cancel();
			
			GT2Activity.bt_start.setEnabled(true);
			GT2Activity.bt_start.setTextColor(Color.BLUE);
			
			/*----------------------------
			 * 4. Finish activity => AlarmDialog
				----------------------------*/
			actv.finish();
			
//			/*----------------------------
//			 * 3. AlarmDialog # TextView
//				----------------------------*/
//			AlarmDialog.tv.setText(actv.getString(R.string.alarm_dialog_tv));
			
			break;// case alarmdialog_bt_ok
			
		}//switch (tag_name)
		
	}//public void onClick(View v)

}
