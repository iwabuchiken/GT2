package gt2.main;

import gt2.utils.CustomTimerTask;
import gt2.utils.Methods;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

public class TimerService extends Service {
	
	/******************************************************************* 
	 * Class members
	 *******************************************************************/
	//
	Context mContext;

	//
	int counter;

	//
	public PowerManager.WakeLock wl;
	
	//
	Timer timer;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// Log
		Log.d("TimerService.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "onBind");
		
		return null;
	}//public IBinder onBind(Intent arg0)

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO 自動生成されたメソッド・スタブ
		super.onStart(intent, startId);
		
		// Log
		Log.d("TimerService.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "onStart()");
		
		/*----------------------------
		 * 0. Context
		 * 1. Get intent
		 * 2. Counter is zero?
		 * 3. Set up WakeLock
		 * 4. Start timer
			----------------------------*/
		/*----------------------------
		 * 0. Context
			----------------------------*/
		mContext = this;
		
		/*----------------------------
		 * 1. Get intent
			----------------------------*/
		counter = intent.getIntExtra("counter", 0);
		
		/*----------------------------
		 * 2. Counter is zero?
			----------------------------*/
		if (counter == 0) {
			
			this.stopSelf();
			
		}//if (counter == 0)
		
		/*----------------------------
		 * 3. Set up WakeLock
			----------------------------*/
		setup_wakelock();
		
		/*----------------------------
		 * 4. Start timer
			----------------------------*/
		timer = new Timer();
		
		Methods.startTimer(mContext, timer, wl, counter);
		
		
	}//public void onStart(Intent intent, int startId)

	private void setup_wakelock() {
		/*----------------------------
		 * 1. PowerManager
		 * 2. WakeLock
		 * 3. Acquire
			----------------------------*/
		
		PowerManager pm = 
					(PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
		
		wl = pm.newWakeLock(
						PowerManager.SCREEN_DIM_WAKE_LOCK + 
							PowerManager.ON_AFTER_RELEASE, 
						"My Tag");
		
		//
		wl.acquire();
		
		// Log
		Log.d("TimerService.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "WakeLock obtained");
		
	}//private void setup_wakelock()

	@Override
	public void onDestroy() {
		/*----------------------------
		 * 1. super
			----------------------------*/
		
		// TODO 自動生成されたメソッド・スタブ
		super.onDestroy();
		
		// Log
		Log.d("TimerService.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "onDestroy");
		
		/*----------------------------
		 * 2. Cancel timer
			----------------------------*/
		timer.cancel();
		
		/*----------------------------
		 * 3. Release lock
			----------------------------*/
		if (wl.isHeld()) {
			//
			wl.release();
			
			// Log
			Log.d("TimerService.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "wl => Released");
			
		}//if (wl.isHeld())

		

	}//public void onDestroy()

	@Override
	public void onCreate() {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate();
		
		// Log
		Log.d("TimerService.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "onCreate");
		
	}//public void onCreate()

	public  void startTimer( ) {
			/*************************
			 * Steps
			 * 1. 
			 *************************/
			//
			if (timer != null) {
				timer.cancel();
			}//if (timer != null)
			
			//
			timer = new Timer();
			
			// Handler
			final android.os.Handler handler = new android.os.Handler();
			
			// Log
			Log.d("TimerService.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Staring schedule ...");
			
			// Thread
			timer.schedule(new CustomTimerTask((Activity) mContext, handler, wl, timer, counter),
					0, 1000
				);//timer.schedule()
			
			
	}//public  void startTimer( )

	  void showAlarm( ) {
			/*************************
			 * Steps
			 * 1. 
			 *************************/
		  // Stop the service
		  Intent i = new Intent(mContext, TimerService.class);
		  
		  mContext.stopService(i);
		  
		  // New intent
		  i = new Intent(mContext, AlarmDialog.class);
		  
		  i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		  
		  // Start
		  mContext.startActivity(i);
		  
//			// debug
//			Toast.makeText(TimerService.this, "时到了!", Toast.LENGTH_SHORT).show();
			
		}//  void showAlarm( )
}//public class TimerService extends Service
