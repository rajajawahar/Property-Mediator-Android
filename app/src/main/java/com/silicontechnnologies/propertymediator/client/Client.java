package com.silicontechnnologies.propertymediator.client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.silicontechnnologies.propertymediator.R;

public class Client extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.clients);
		setTitle("Client Details");
		Button add = (Button)findViewById(R.id.add);
		Button view =(Button)findViewById(R.id.view);
		add.setOnClickListener(new OnClickListener() 
		{
			
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
			
				Intent add= new Intent(Client.this,AddDetails.class);
				startActivity(add);
						
				
			}
		});
		view.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) 
			{
				Intent view = new Intent(getApplicationContext(), com.silicontechnnologies.propertymediator.view.class);
				startActivity(view);
				
			}
			
		});
	}

}
