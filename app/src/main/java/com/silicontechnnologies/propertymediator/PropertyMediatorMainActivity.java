package com.silicontechnnologies.propertymediator;




import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class PropertyMediatorMainActivity extends Activity {

	
	ImageButton btnreminder,btnclient,btnphoto,btncategories,btnnews;
	TextView cl,ph,re,cat,news;
	Boolean isInternetPresent = false;

	// Connection detector class
	ConnectionDetector cd;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_property_mediator_main);
		btnclient = (ImageButton)findViewById(R.id.imgclient);
		btnreminder = (ImageButton)findViewById(R.id.imgremind);
		btnnews = (ImageButton)findViewById(R.id.imgfeed);
		btnphoto = (ImageButton)findViewById(R.id.imgphoto);
		btncategories = (ImageButton)findViewById(R.id.imgcategory);
		cl  = (TextView)findViewById(R.id.txtcllient);
		ph= (TextView)findViewById(R.id.txtphoto);
		cat = (TextView)findViewById(R.id.txtcategory);
		news = (TextView)findViewById(R.id.txtfeed);
		re =(TextView)findViewById(R.id.txtremind);
		btnclient.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v) 
			{
				Intent i = new Intent(getApplicationContext(),Client.class);
				startActivity(i);
				
			}
			
		});
		btnreminder.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v) 
			{
				Intent i = new Intent(getApplicationContext(),Reminder.class);
				startActivity(i);
				
			}
			
		});
		btnnews.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v) 
			{
				cd = new ConnectionDetector(getApplicationContext());

				// get Internet status
				isInternetPresent = cd.isConnectingToInternet();

				// check for Internet status
				if (isInternetPresent) {
					// Internet Connection is Present
					// make HTTP requests
					// showAlertDialog(NewsreadActivity.this,
					// "Internet Connection",
					// "You have internet connection", true);
				} else {
					// Internet connection is not present
					// Ask user to connect to Internet
					showAlertDialog(PropertyMediatorMainActivity.this,
							"No Internet Connection",
							"You don't have internet connection.", false);
				}
				startActivity(new Intent(getApplicationContext(),
						PropertyNews.class));
				
			}
			
		});
		btnphoto.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v) 
			{
				startActivity(new Intent(getApplicationContext(),Customer.class));
			}
			
		});
		btncategories.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v) 
			{
				startActivity(new Intent(getApplicationContext(),House.class));
			}
			
		});
		
		}
	private void showAlertDialog(PropertyMediatorMainActivity property, String string,
			String string2, boolean b) {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), string2, Toast.LENGTH_SHORT)
				.show();
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if (keyCode == KeyEvent.KEYCODE_BACK) 
		{
			AlertDialog.Builder alert = new AlertDialog.Builder(PropertyMediatorMainActivity.this);

			alert.setTitle("Property Mediator");
			alert.setIcon(R.drawable.ic_launcher);
			alert.setMessage("Are you sure want to exit ?");

			alert.setPositiveButton("YES",new DialogInterface.OnClickListener() 
			{

						public void onClick(DialogInterface arg0, int arg1) 
						{
							finish();
						}
			 });
			alert.setNegativeButton("NO",new DialogInterface.OnClickListener() 
			{

						public void onClick(DialogInterface arg0, int arg1) 
						{
							
						}
			});
			alert.show();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
}
