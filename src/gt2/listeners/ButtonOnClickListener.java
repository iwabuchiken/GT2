package gt2.listeners;
import gt2.main.GT2Activity;
import gt2.utils.Methods;

import java.io.File;

import android.app.Activity;
import android.app.Dialog;
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
			
//			Methods.startTimer(actv);
			Methods.startTimerService(actv);
			
			break;

		case main_bt_stop:
			
			if (GT2Activity.stopButtonStatus == false && GT2Activity.timeLeft != 0) {
				
				GT2Activity.timeLeft = 0;
				
				Methods.showTime(actv, GT2Activity.timeLeft);
				
				break;
				
			} //if (GT2Activity.stopButtonStatus == false)
			
			
			Methods.stopTimer(actv);
			
			break;
		}//switch (tag_name)
		
	}//public void onClick(View v)

}
