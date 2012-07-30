package gt2.utils;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.PowerManager.WakeLock;
import android.util.Log;

public class CustomTimerTask extends TimerTask {

	Handler handler;
	WakeLock wl;
	Timer timer;
	int counter;
	Activity actv;
	
	public CustomTimerTask(Activity actv, 
						Handler handler, WakeLock wl, Timer timer, int counter) {
		
		this.handler = handler;
		this.wl = wl;
		this.timer = timer;
		this.counter = counter;
		this.actv = actv;
		
	}//public CustomTimerTask(Handler handler, WakeLock wl)

	@Override
	public void run() {
		handler.post(new Runnable(){

			@Override
			public void run() {
				// 
				if (counter == -1) {
					//
					timer.cancel();
					
					//
					if (wl.isHeld()) {
						//
						wl.release();
						
						// Log
						Log.d("TimerService.java" + "["
								+ Thread.currentThread().getStackTrace()[2].getLineNumber()
								+ "]", "wl => Released");
						
					}//if (wl.isHeld())
					
					//
					Methods.showAlarm(actv);
					
				} else {//if (counter == -1)
					//
					Methods.countdown(actv, counter);
					
					//
					counter = counter - 1;
					
				}//if (counter == -1)
				
				
			}//public void run() // Runnable
			
		});//handler.post()
		
	}

}
