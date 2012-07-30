package gt2.main;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class GT2Activity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	/*----------------------------
		 * 1. super
		 * 2. Set content
		 * 3. Spinner
		 * 4. Listeners
			----------------------------*/
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        /*----------------------------
		 * 3. Spinner
			----------------------------*/
        setup_spinner();
		
    }//public void onCreate(Bundle savedInstanceState)

	private void setup_spinner() {
		/*----------------------------
		 * 1. Get spinners
		 * 
			----------------------------*/
		/*----------------------------
		 * 1. Get spinners
			----------------------------*/
		Spinner sp_min = (Spinner) findViewById(R.id.main_sp_minutes);
		Spinner sp_sec = (Spinner) findViewById(R.id.main_sp_seconds);
		
		/*----------------------------
		 * 2. Get lists
			----------------------------*/
		List<Integer> minutes = new ArrayList<Integer>();
		List<Integer> seconds = new ArrayList<Integer>();
		
		minutes.add(1); minutes.add(3); minutes.add(5);
		minutes.add(10); minutes.add(15); minutes.add(20);
		minutes.add(30); minutes.add(45); minutes.add(60);
		
		seconds.add(10); seconds.add(20); seconds.add(30);
		seconds.add(40); seconds.add(50);
		
		/*----------------------------
		 * 3. Setup adapters
			----------------------------*/
		// Min
		ArrayAdapter<Integer> adapterMin = new ArrayAdapter<Integer>(
//	              this, android.R.layout.simple_spinner_item, minutes);
				this, R.layout.spinner_row, minutes);
		
		// Sec
		ArrayAdapter<Integer> adapterSec = new ArrayAdapter<Integer>(
//	              this, android.R.layout.simple_spinner_item, seconds);
				this, R.layout.spinner_row, seconds);
		
		// Drop down view
		adapterMin.setDropDownViewResource(
//				android.R.layout.simple_spinner_dropdown_item);
				R.layout.spinner_dropdown_row);
		
		adapterSec.setDropDownViewResource(
//				android.R.layout.simple_spinner_dropdown_item);
				R.layout.spinner_dropdown_row);

		
        /*----------------------------
		 * 4. Set adapter
			----------------------------*/
		sp_min.setAdapter(adapterMin);
		sp_sec.setAdapter(adapterSec);
		
	}//private void setup_spinner()
    
}