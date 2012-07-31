package gt2.listeners;

import gt2.main.TimerHistoryActivity;
import gt2.utils.Methods;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class ListOnItemClickListener implements OnItemClickListener {

	//
	Activity actv;
	//
	Vibrator vib;
	
	//
//	Methods.DialogTags dlgTag = null;

	public ListOnItemClickListener(Activity actv) {
		// 
		this.actv = actv;
		
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}//public DialogOnItemClickListener(Activity actv, Dialog dlg)

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		/*----------------------------
		 * Steps
		 * 1. Get tag
		 * 2. Vibrate
		 * 3. Switching
			----------------------------*/
		
		Methods.ListItemTags tag = (Methods.ListItemTags) parent.getTag();
//		
		vib.vibrate(Methods.vibLength_click);
		
		/*----------------------------
		 * 3. Switching
			----------------------------*/
		switch (tag) {
		
		case timer_history_actv_lv://---------------------------------------------
			
//			// Log
//			Log.d("ListOnItemClickListener.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "parent # ItemIdAtPosition: " + parent.getItemAtPosition(position));
//			
//			Log.d("ListOnItemClickListener.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "idList # ItemIdAtPosition: " + TimerHistoryActivity.idList.get(position));
//			
//			Log.d("ListOnItemClickListener.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "parent(idList) # ItemIdAtPosition: " + parent.getItemAtPosition(TimerHistoryActivity.idList.get(position).intValue() - 1));
			
			Intent i=new Intent();
			
			i.putExtra("chosenFileId", TimerHistoryActivity.idList.get(position));
			
			actv.setResult(actv.RESULT_OK, i);
			
			actv.finish();
			
			
			break;// case timer_history_actv_lv
			
		}//switch (tag)
		
	}//public void onItemClick(AdapterView<?> parent, View v, int position, long id)
}
