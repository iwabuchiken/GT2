package gt2.main;

import java.util.List;

import gt2.listeners.ButtonOnClickListener;
import gt2.listeners.ButtonOnTouchListener;
import gt2.utils.Methods;
import gt2.utils.TimerItemListAdapter;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;

public class TimerHistoryActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		/*----------------------------
		 * Steps
		 * 1. Super
		 * 2. Set content
		----------------------------*/
		super.onCreate(savedInstanceState);

		//
		setContentView(R.layout.timer_history_actv);
		
		initial_setup();

	}//public void onCreate(Bundle savedInstanceState)

	private void initial_setup() {
		/*----------------------------
		 * 1. Listeners
		 * 2. Set list
			----------------------------*/
		/*----------------------------
		 * 1. Listeners
			----------------------------*/
		set_listeners();
		
		/*----------------------------
		 * 2. Set list
			----------------------------*/
		set_list();
		
	}//private void initial_setup()

	private void set_list() {
		/*----------------------------
		 * 1. Get list
		 * 2. Prepare adapter
		 * 3. Set adapter to list
			----------------------------*/
		/*----------------------------
		 * 1. Get list
			----------------------------*/
		List<TimerItem> tiList = Methods.getTimerItemList_fromDB(this);
		
//		// Log
//		Log.d("TimerHistoryActivity.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "ti.size(): " + ti.size());
		
		
		/*----------------------------
		 * 2. Prepare adapter
			----------------------------*/
		TimerItemListAdapter tilAdapter = new TimerItemListAdapter(
				this,
//				R.layout.list_row_timer_history,
				R.layout.timer_history_actv,
				tiList
		);

		
		/*----------------------------
		 * 3. Set adapter to list
			----------------------------*/
		setListAdapter(tilAdapter);
		
	}//private void set_list()

	private void set_listeners() {
		/*----------------------------
		 * 1. Button
		 * 2. List view
			----------------------------*/
		
		Button bt_back = (Button) findViewById(R.id.timer_history_actv_bt_back);
		
		bt_back.setTag(Methods.ButtonTags.timer_history_actv_bt_back);
		
		bt_back.setOnTouchListener(new ButtonOnTouchListener(this));
		bt_back.setOnClickListener(new ButtonOnClickListener(this));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPause() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onDestroy();
	}
	
}//public class TimerHistoryActivity extends ListActivity
