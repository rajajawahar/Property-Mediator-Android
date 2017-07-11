package com.silicontechnnologies.propertymediator;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.silicontechnnologies.propertymediator.client.DeleteDetails;
import com.silicontechnnologies.propertymediator.database.DBAdapter;

public class view extends Activity implements OnItemClickListener
{
	ListView list;
	
	DBAdapter contactdb;
	SimpleCursorAdapter dataAdapter;

	
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);
		setTitle("View Details");
		contactdb = new DBAdapter(this);
		contactdb.open();
		list = (ListView) findViewById(R.id.lv);
		displaylist();
		list.setOnItemClickListener(this);
	}

	@SuppressWarnings("deprecation")
	private void displaylist() 
	{
		// TODO Auto-generated method stub
		
		Cursor cursor = contactdb.fetchAllContacts();
	
		String[] columns = new String[] {DBAdapter.KEY_NAME,DBAdapter.KEY_PHONE };
		
		int[] to = new int[] { R.id.txtviewname, R.id.txtviewphone };

		
		SimpleCursorAdapter dataAdapter = new  SimpleCursorAdapter(this, R.layout.listviewcontent,cursor, columns, to);
		list.setAdapter(dataAdapter);
	}

	
	public void onItemClick(AdapterView<?> arg0, View view, int position,long id)
	{
		// TODO Auto-generated method stub
		Cursor cursor = (Cursor) list.getItemAtPosition(position);

		
		@SuppressWarnings("unused")
		String contactname = list.getItemAtPosition(position).toString();

		Intent intent = new Intent(getApplicationContext(), DeleteDetails.class);
		intent.putExtra("name", cursor.getString(cursor
				.getColumnIndex(DBAdapter.KEY_NAME)));
		
		intent.putExtra("notes", cursor.getString(cursor
				.getColumnIndex(DBAdapter.KEY_NOTES)));
		
		intent.putExtra("pan", cursor.getString(cursor
				.getColumnIndex(DBAdapter.KEY_PAN)));
		
		intent.putExtra("phone", cursor.getString(cursor
				.getColumnIndex(DBAdapter.KEY_PHONE)));
		
		intent.putExtra("email", cursor.getString(cursor
				.getColumnIndex(DBAdapter.KEY_EMAIL)));
		
		startActivity(intent);
	}
}

