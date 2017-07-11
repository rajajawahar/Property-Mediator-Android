package com.silicontechnnologies.propertymediator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Reminder extends Activity implements
		OnClickListener {

	private Button b1, b2;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainremind);
		setTitle("Reminder");
		b1 = (Button) findViewById(R.id.set1);
		b2 = (Button) findViewById(R.id.view1);
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
	}

	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.set1:
			Intent addintent = new Intent(Reminder.this,
					ReminderEditActivity.class);
			startActivity(addintent);
			break;

		case R.id.view1:
			Intent viewintent = new Intent(Reminder.this,
					ReminderListActivity.class);
			startActivity(viewintent);
			break;
		default:
			break;
		}

	}
}