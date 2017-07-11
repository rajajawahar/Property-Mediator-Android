package com.silicontechnnologies.propertymediator.photo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.silicontechnnologies.propertymediator.database.ImageDatabase;
import com.silicontechnnologies.propertymediator.PropertyMediatorMainActivity;
import com.silicontechnnologies.propertymediator.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Customer extends Activity {

	private static final String[] items = { "Choose", "House", "Land" };
	TextView tv;
	Bitmap thumbnail;
	private ImageDatabase dbHelper;
	String nm, ph, em, add, det, ty, m, root;
	Button save, cancel;
	FileOutputStream fos;
	Spinner spinner;
	String path = "", fill = "";
	BitmapFactory.Options options;
	int n;
	EditText name, phone, email, address, details;
	ImageView imageView1;
	private static final int CAM_REQUREST = 0;
	File file, file1, f;
	String it;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.contact);
		// Spinner element
		setTitle("Owner Details");
		
		dbHelper = new ImageDatabase(this);
		dbHelper = dbHelper.open();
		// dbHelper.deleteAllproperty();
		// // Add some data
		// dbHelper.insertSomeproperty();
		if (dbHelper == null) {
			dbHelper.insertSomeproperty();
		}
		name = (EditText) findViewById(R.id.editText1);
		phone = (EditText) findViewById(R.id.editText2);
		email = (EditText) findViewById(R.id.editText3);
		address = (EditText) findViewById(R.id.editText4);
		details = (EditText) findViewById(R.id.editText5);
		spinner = (Spinner) findViewById(R.id.spinner1);
	
		
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, items);
		aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(aa);
		// Spinner click listener
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				//change the spinner text color
				it = (String) arg0.getItemAtPosition(arg2);
				   ((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
				
				   // TODO Auto-generated method stub
				if (arg2 != 0) {
					String item = arg0.getItemAtPosition(arg2).toString();
					m = item.toString();
					// Showing selected spinner item
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(arg0.getContext(), "Please Select:Type ",
						Toast.LENGTH_LONG).show();

			}
		});
		imageView1 = (ImageView) findViewById(R.id.imageView1);

		imageView1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
								Intent cameraIntent = new Intent(
						android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(cameraIntent, CAM_REQUREST);
			  }

		});
	


		cancel = (Button) findViewById(R.id.cancel);
		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),
						PropertyMediatorMainActivity.class));
			}
		});
		save = (Button) findViewById(R.id.save);
		save.setOnClickListener(new OnClickListener() {

			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				nm = name.getText().toString();
				m = spinner.getSelectedItem().toString();
				ph = phone.getText().toString();
				em = email.getText().toString();
				add = address.getText().toString();
				det = details.getText().toString();
				if (nm.equals("") || ph.equals("") || em.equals("")
						|| add.equals("") || det.equals("") || path.equals("")) {
					Toast.makeText(Customer.this,
							"Please add values..and Image", Toast.LENGTH_LONG)
							.show();

				} else {

			dbHelper.createProperty(nm, m, ph, em, add, det, path);

					Toast.makeText(Customer.this, "Record Added successfully.",
							Toast.LENGTH_LONG).show();
					name.setText("");
					phone.setText("");
					email.setText("");
					address.setText("");
					details.setText("");
					spinner.setSelection(0);
					imageView1.setImageResource(R.drawable.photo);
					//imageView1.setBackgroundDrawable(null);
			
				}
				

			}

		});
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		  if (requestCode == CAM_REQUREST) {
			    if (resultCode == RESULT_OK) {
			    	int iW = 50;
					int iH = 50;

					thumbnail = (Bitmap) data.getExtras().get("data");
					options = new BitmapFactory.Options();
					
					thumbnail = Bitmap.createScaledBitmap(thumbnail, iW, iH, true);

				
					imageView1.setImageBitmap(thumbnail);
					try {
						getimagepath();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			

			      Toast.makeText(this, "Image saved successfully " , 
				               Toast.LENGTH_LONG).show();
			    } else if (resultCode == RESULT_CANCELED) {
			      Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
			    } else {
			      Toast.makeText(this, "Callout for image capture failed!", 
			                     Toast.LENGTH_LONG).show();
			    }
			  }
			}

	@SuppressLint("SimpleDateFormat")
	private void getimagepath() throws FileNotFoundException {
		// TODO Auto-generated method stub
	/*	root = Environment.getExternalStorageDirectory() + "/RealEstate";
		file = new File(root);
		file.mkdirs();
*/		
		root = Environment.getExternalStorageDirectory() + "/Property Mediator";
		file = new File(root);
		
		if(file.isDirectory()) 
		{	
					
		// Name the image file in sdcard with images.png
			
			/*	n = n + 1;
				file1 = new File(root + "/" + n + "images" + ".png");

				path = file1.toString();*/
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
				String timeStamp = dateFormat.format(new Date());
				String imageFileName = "picture_" + timeStamp + ".jpg";
				file1 = new File(root + "/" + imageFileName);
				path = file1.toString();
				
		}
		else
		{
						
			file.mkdirs();
			
			if (path.equals(fill))
			{
				file1 = new File(root + "/" + "images" + ".jpg");

			// Now open the file and compress our bitmap image and make the file
			// image file with png ow Open

				path = file1.toString();
			} 
			
			
		}
			fos = new FileOutputStream(file1);

			if (fos != null) {

				thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, fos);

				try {
					fos.close();
				} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onDestroy() {

		super.onDestroy();

		dbHelper.close();
	}

}
