package com.silicontechnnologies.propertymediator.category;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.silicontechnnologies.propertymediator.database.ImageDatabase;
import com.silicontechnnologies.propertymediator.R;

import java.util.ArrayList;
import java.util.Arrays;

public class House extends Activity {
	ListView list, list1;
	private ImageDatabase imagedb;
	Cursor cursor;
	String TAG = "Property Form";
	String b, item, one;
	// Spinner sp;
	
	Button cancel;
	int n = 0;
	String[] colum;
	ArrayList<String> listitem;
	ArrayAdapter<String> aa;
	private SimpleCursorAdapter dataAdapter;
	private static final String[] items = {"","", "House", "","","Land" };
	private ArrayAdapter<String> listAdapter;
	RelativeLayout real;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.house);
		setTitle("Category");
		real = (RelativeLayout) findViewById(R.id.relativelayout1);
		
		imagedb = new ImageDatabase(this);
		imagedb.open();

		displaylist();
		
	}

	private void displaylist() {
		// TODO Auto-generaed method stub
		list = (ListView) findViewById(R.id.listView1);
		list1 = (ListView) findViewById(R.id.listView2);
		
		cursor = imagedb.fetchAllproperty();

		// The desired columns to be bound
		colum = new String[] { ImageDatabase.KEY_name, ImageDatabase.KEY_phone, };

		// the XML defined views which the data will be bound to
		int[] to = new int[] { R.id.textView1, R.id.textView2 };

		// create the adapter using the cursor pointing to the desired data
		// as well as the layout information
		dataAdapter = new SimpleCursorAdapter(this, R.layout.prop_info, cursor,
				colum, to, 0);

		// Assign adaqpter to list
		list1.setAdapter(dataAdapter);

		// list1.setVisibility(View.INVISIBLE);
		list1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> list, View view,
					final int position, long id) {
				// Get the cursor, positioned to the corresponding row in
				// the
				// result set

				cursor = (Cursor) list.getItemAtPosition(position);

				// Get the Students name from this row in the database.
				// final String studentcode = cursor.getString(cursor
				// getColumnIndexOrThrow("Type"));
				one = list.getItemAtPosition(position).toString();
				Intent intent = new Intent(getApplicationContext(),
						Landlist.class);
				intent.putExtra("Name", cursor.getString(cursor
						.getColumnIndex(ImageDatabase.KEY_name))); // assuming

				
				intent.putExtra("Type", cursor.getString(cursor
						.getColumnIndex(ImageDatabase.KEY_Type))); 
				intent.putExtra("Phone", cursor.getString(cursor
						.getColumnIndex(ImageDatabase.KEY_phone))); // assuming
																	// that
			
				intent.putExtra("Email", cursor.getString(cursor
						.getColumnIndex(ImageDatabase.KEY_email))); // assuming
																	// that
			

				intent.putExtra("Address", cursor.getString(cursor
						.getColumnIndex(ImageDatabase.KEY_address))); // assuming
																		// that
			
				intent.putExtra("Details", cursor.getString(cursor
						.getColumnIndex(ImageDatabase.KEY_detail))); // assuming
																		// that
				
				intent.putExtra("Pathname", cursor.getString(cursor
						.getColumnIndex(ImageDatabase.KEY_path))); // assuming
																	// that
				
				startActivity(intent);

				
			}
		});

		
		listitem = new ArrayList<String>();

		listitem.addAll(Arrays.asList(items));

		// Create ArrayAdapter using the planet list.
		listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow,
				listitem);

		// Add more planets. If you passed a String[] instead of a List<String>
		// into the ArrayAdapter constructor, you must not add more items.
		// Otherwise an exception will occur.

		// Set the ArrayAdapter as the ListView's adapter.
		list.setAdapter(listAdapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				item = arg0.getItemAtPosition(arg2).toString();
				b = item.toString();
				
				list1.setVisibility(View.VISIBLE);
				dataAdapter.getFilter().filter(b.toString());
				//imagedb.fetchpropertyByType(b.toString());
				
				list.setVisibility(View.INVISIBLE);
				if (dataAdapter.isEmpty())
					Toast.makeText(getApplicationContext(), "No Contacts Available", Toast.LENGTH_LONG).show();
				
			}
		});
		// sp = (Spinner) findViewById(R.id.spinner1);

		dataAdapter.setFilterQueryProvider(new FilterQueryProvider() {
			public Cursor runQuery(CharSequence constraint) {

				return imagedb.fetchpropertyByType(constraint.toString());

			}
		});

	}

}
