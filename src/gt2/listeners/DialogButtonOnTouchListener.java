package gt2.listeners;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class DialogButtonOnTouchListener implements OnTouchListener {

	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;
	Dialog dlg;
	
	public DialogButtonOnTouchListener(Activity actv, Dialog dlg) {
		//
		this.actv = actv;
		this.dlg = dlg;
	}
	
	public DialogButtonOnTouchListener(Activity actv) {
		//
		this.actv = actv;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO 自動生成されたメソッド・スタブ
//		Methods.DialogTags tag_name = (Methods.DialogTags) v.getTag();
		
		switch (event.getActionMasked()) {
//		case MotionEvent.ACTION_DOWN:
//				switch (tag_name) {
//				
//				case dlg_generic_dismiss:
//					//
//					v.setBackgroundColor(Color.GRAY);
//					
//					break;
//				}//switch (tag_name)
		
//			break;//case MotionEvent.ACTION_DOWN:
//			
//		case MotionEvent.ACTION_UP:
//			switch (tag_name) {
//
//			case dlg_generic_dismiss:
//					//
//					v.setBackgroundColor(Color.WHITE);
//					
//					break;
//				}//switch (tag_name)
		
//			break;//case MotionEvent.ACTION_UP:
		
		}//switch (event.getActionMasked())
		return false;
	}

}
