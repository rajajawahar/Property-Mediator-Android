package com.silicontechnnologies.propertymediator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddDetails extends Activity
{

	DBAdapter contactdb;
	Button btnsave, btncancel;
	EditText name, notes,pan, phone, email;
	
	protected void onCreate(Bundle savedInstanceState)
	{
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adddetails);
		setTitle("Add Details");
		name = (EditText) findViewById(R.id.txtname);
		notes =(EditText)findViewById(R.id.txtnotes);
		pan = (EditText)findViewById(R.id.txtpan);
		phone = (EditText) findViewById(R.id.txtphone);
		email = (EditText) findViewById(R.id.txtemail);

		btnsave = (Button) findViewById(R.id.btnsave);
		btncancel = (Button) findViewById(R.id.btncancel);

		
		contactdb=new DBAdapter(this);
		contactdb.open();
		
		btnsave.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				if(name.getText().toString().equals("")||notes.getText().toString().equals("")||pan.getText().toString().equals("")|phone.getText().toString().equals("")||email.getText().toString().equals(""))
				{
					Toast.makeText(getApplicationContext(), "Insert Values", Toast.LENGTH_LONG).show();
				}
				else
				{
					String gname = name.getText().toString();
					String gnotes = notes.getText().toString();
					String gpan = pan.getText().toString();
					String gphone = phone.getText().toString();
					String gemail = email.getText().toString();

					contactdb.createContact(gname,  gnotes,gpan, gphone, gemail);
					name.setText("");
					notes.setText("");
					pan.setText("");
					phone.setText("");
					email.setText("");
					Toast.makeText(getApplicationContext(), "Values Added", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		btncancel.setOnClickListener(new OnClickListener()
		{
			
			public void onClick(View v)
			{
				
				finish();
			}
		});

	}

	
	
}
