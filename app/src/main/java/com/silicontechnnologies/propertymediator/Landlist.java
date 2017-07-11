package com.silicontechnnologies.propertymediator;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Landlist extends Activity {

	String b, temp, temp2, temp3, temp4, temp5, temp6, temp7, pathname, c,s;
	TextView name, type, phone, email, address, detail;
	Button bk, cl, delete;
	ImageView viewnow;
	File mfile;
	ImageDatabase mdb1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.land);
		setTitle("Detail");
		viewnow = (ImageView) findViewById(R.id.imageView1);
		delete = (Button) findViewById(R.id.bk);
		mdb1 = new ImageDatabase(this);
		mdb1.open();
		// do {
		// Intent net = new Intent(getApplicationContext(),
		// ImageFromDatabaseActivity.class);
		// net.putExtra("net", 0);
		// startActivity(net);
		// } while (viewnow.callOnClick());
		temp = getIntent().getExtras().getString("Name");
		temp2 = getIntent().getExtras().getString("Type");

		temp3 = getIntent().getExtras().getString("Phone");

		temp4 = getIntent().getExtras().getString("Email");

		temp5 = getIntent().getExtras().getString("Address");

		temp6 = getIntent().getExtras().getString("Details");

		temp7 = getIntent().getExtras().getString("Pathname");

		name = (TextView) findViewById(R.id.textView2);

		type = (TextView) findViewById(R.id.textView4);

		phone = (TextView) findViewById(R.id.textView6);

		email = (TextView) findViewById(R.id.textView9);

		address = (TextView) findViewById(R.id.textView10);

		detail = (TextView) findViewById(R.id.textView12);

		name.setText(temp);
		type.setText(temp2);
		phone.setText(temp3);
		email.setText(temp4);

		address.setText(temp5);
		detail.setText(temp6);
		pathname = temp7.toString();
		
		//detail.setText(pathname);
		mfile = new File(pathname);
		if (mfile.exists()) {
			Bitmap picture = BitmapFactory.decodeFile(mfile.getAbsolutePath());
			viewnow.setImageBitmap(picture);
		}
		viewnow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent in = new Intent(getApplicationContext(), Viewer.class);

				in.putExtra("fine", pathname);
				startActivity(in);
			}
		});
		

		delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				mdb1.deleteContact(temp);
				Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(getApplicationContext(),House.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(intent);
				//startActivity(new Intent(getApplicationContext(), House.class));
				
			}
		});
		cl = (Button) findViewById(R.id.button2);

		cl.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				c = temp3.toString();
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:" + c));
				startActivity(callIntent);
			}
		});
		
		
	}
	
	protected void onDestroy()
	{
		super.onDestroy();
		mdb1.close();
	}
}
