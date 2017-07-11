package com.silicontechnnologies.propertymediator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;



public class main extends Activity {

	
	public static final long TIME = 3000;

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.opening1);
		
		
		 Thread welcomeThread = new Thread()
		 {

		        @Override
		        public void run() {
		            try {
		                sleep(TIME);
		            } catch (Exception e) {
		                Log.e(getClass().getName(), e.toString());
		            } finally {
		                startActivity(new Intent(main.this,PropertyMediatorMainActivity.class));
		                finish();
		            }
		        }
		    };
		    welcomeThread.start();
	
		   	
	}	
		    
}
	
