package com.silicontechnnologies.propertymediator;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.silicontechnnologies.propertymediator.utils.ConnectionDetector;

public class Checkinternet extends Activity {

	// flag for Internet connection status
	Boolean isInternetPresent = false;

	// Connection detector class
	ConnectionDetector cd;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chec);

		Button btnStatus = (Button) findViewById(R.id.bu1);

		// creating connection detector class instance
		cd = new ConnectionDetector(getApplicationContext());

		/**
		 * Check Internet status button click event
		 * */
		btnStatus.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// get Internet status
				isInternetPresent = cd.isConnectingToInternet();

				// check for Internet status
				if (isInternetPresent) {
					// Internet Connection is Present
					// make HTTP requests
					showAlertDialog(Checkinternet.this, "Internet Connection",
							"You have internet connection", true);
				} else {
					// Internet connection is not present
					// Ask user to connect to Internet
					showAlertDialog(Checkinternet.this,
							"No Internet Connection",
							"You don't have internet connection.", false);
				}

			}

		});

	}

	
	@SuppressWarnings("deprecation")
	public void showAlertDialog(Context context, String title, String message,
			Boolean status) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		// Setting Dialog Title
		alertDialog.setTitle(title);

		// Setting Dialog Message
		alertDialog.setMessage(message);

		// Setting alert dialog icon

		// Setting OK Button
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Intent serviceback = new Intent(Checkinternet.this,
						PropertyMediatorMainActivity.class);
				startActivity(serviceback);
			}
		});

		
		alertDialog.show();
	}
}