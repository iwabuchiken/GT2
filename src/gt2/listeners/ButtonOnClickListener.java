package gt2.listeners;
import gt2.main.AlarmDialog;
import gt2.main.GT2Activity;
import gt2.utils.Methods;

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
			
			int min = (Integer) GT2Activity.sp_min.getSelectedItem();
			int sec = (Integer) GT2Activity.sp_sec.getSelectedItem();
			
			if (min == 0 && sec == 0) {
				
				// debug
				Toast.makeText(actv, "ŽžŠÔ‚ª‘I‘ð‚³‚ê‚Ä‚Ü‚¹‚ñ", 2000).show();
				
				break;
				
			}//if ()
			
			// Buttons
			GT2Activity.bt_start.setEnabled(false);
			GT2Activity.bt_start.setTextColor(Color.GRAY);
			
			GT2Activity.bt_stop.setEnabled(true);
			
//			Methods.startTimer(actv);
			Methods.startTimerService(actv);
			
			break;

		case main_bt_stop://------------------------------------------------------
			
			if (GT2Activity.stopButtonStatus == false && GT2Activity.timeLeft != 0) {
				
				GT2Activity.timeLeft = 0;
				
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
			
			AlarmDialog.vib.cancel();
			
			GT2Activity.bt_start.setEnabled(true);
			GT2Activity.bt_start.setTextColor(Color.BLUE);
			
			actv.finish();
			
			break;// case alarmdialog_bt_ok
			
		}//switch (tag_name)
		
	}//public void onClick(View v)

}
