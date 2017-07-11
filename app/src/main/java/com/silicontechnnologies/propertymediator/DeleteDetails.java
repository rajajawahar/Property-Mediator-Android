
package com.silicontechnnologies.propertymediator;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteDetails extends Activity
{
	DBAdapter contactdb;
	String b, viewname,viewnotes,viewpan, viewphone, viewemail;
	EditText name,notes,pan, phone, email;
	TextView lblname,lblnotes,lblpan,lblphone,lblemail; 
	Button btndelete,btncall;
	

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details);
		
		contactdb=new DBAdapter(this);
		contactdb.open();
		viewname = getIntent().getExtras().getString("name");
		viewnotes = getIntent().getExtras().getString("notes");
		viewpan = getIntent().getExtras().getString("pan");
		viewphone = getIntent().getExtras().getString("phone");
		viewemail = getIntent().getExtras().getString("email");
		
		
		btncall = (Button)findViewById(R.id.btncall);
		btndelete = (Button)findViewById(R.id.btndelet);
		
		name = (EditText) findViewById(R.id.txtname);		
		notes = (EditText) findViewById(R.id.txtnotes);
		pan = (EditText)findViewById(R.id.txtpan);
		phone = (EditText) findViewById(R.id.txtphone);
		email = (EditText) findViewById(R.id.txtmail);
		lblname = (TextView)findViewById(R.id.lblname);
		lblnotes = (TextView)findViewById(R.id.lblnotes);
		lblpan = (TextView)findViewById(R.id.lblpan);
		lblphone = (TextView)findViewById(R.id.lblphone);
		lblemail = (TextView)findViewById(R.id.lblemail);
		

		name.setText(viewname);
		notes.setText(viewnotes);
		pan.setText(viewpan);
		phone.setText(viewphone);
		email.setText(viewemail);

		btndelete.setOnClickListener(new OnClickListener()
		{
			
				public void onClick(View v)
				{
					contactdb.deleteContact(viewname);
					Toast.makeText(getApplicationContext(), "Contact deleted successfully", Toast.LENGTH_LONG).show();
					
					Intent intent = new Intent(getApplicationContext(),Client.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		            startActivity(intent);
					
				}
		});
		btncall.setOnClickListener(new OnClickListener()
		{
			
				public void onClick(View v)
				{
					String url = "tel:" + phone.getText().toString().trim();
					Intent i = new Intent(Intent.ACTION_CALL, Uri.parse(url));
					startActivity(i);
				}
		});
		
	}

}
