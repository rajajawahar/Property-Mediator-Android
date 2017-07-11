
package com.silicontechnnologies.propertymediator;



import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ReminderListActivity extends ListActivity {
    private static final int ACTIVITY_CREATE=0;
    private static final int ACTIVITY_EDIT=1;
    
    private RemindersDbAdapter mDbHelper;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder_list);
        setTitle("View Reminder");
        mDbHelper = new RemindersDbAdapter(this);
        mDbHelper.open();
        fillData();
        registerForContextMenu(getListView());

    }
    

	@SuppressWarnings("deprecation")
	private void fillData() {
        Cursor remindersCursor = mDbHelper.fetchAllReminders();
        startManagingCursor(remindersCursor);
       
        // Create an array to specify the fields we want to display in the list (only TITLE)
        String[] from = new String[]{RemindersDbAdapter.KEY_TITLE,RemindersDbAdapter.KEY_BODY,RemindersDbAdapter.KEY_DATE_TIME};
        
        
        // and an array of the fields we want to bind those fields to (in this case just text1)
        int[] to = new int[]{R.id.text1,R.id.text2,R.id.text3};
      
        
        // Now create a simple cursor adapter and set it to display
        SimpleCursorAdapter reminders = 
        	    new SimpleCursorAdapter(this, R.layout.reminder_row, remindersCursor, from,to);
        setListAdapter(reminders);
        
     /*  String[] strings = new String[]{RemindersDbAdapter.KEY_BODY};
       
        int[] to1 = new int[]{R.id.text2};
        
        SimpleCursorAdapter reminders1 =  new SimpleCursorAdapter(this, R.layout.reminder_row, remindersCursor,strings , to1);
       setListAdapter(reminders1);
       
       String[] strings2 = new String[]{RemindersDbAdapter.KEY_DATE_TIME};
       
       int[] to2 = new int[]{R.id.text3};
       
       SimpleCursorAdapter reminders2 =    new SimpleCursorAdapter(this, R.layout.reminder_row, remindersCursor,strings2 , to2);
       setListAdapter(reminders2);
     */  
      
      
    }
    
    
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.list_menu, menu); 
        return true;
    }

    
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch(item.getItemId()) {
        case R.id.menu_insert: 
            createReminder();
            return true; 
        case R.id.menu_settings: 
        	Intent i = new Intent(this, TaskPreferences.class); 
        	startActivity(i); 
            return true;
        
        }
       
        return super.onMenuItemSelected(featureId, item);
    }
	
    
	/*public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater mi = getMenuInflater(); 
		mi.inflate(R.menu.list_menu_item_longpress, menu); 
	}

    @Override
	public boolean onContextItemSelected(MenuItem item) {
		switch(item.getItemId()) {
    	case R.id.menu_delete:
    		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
	        mDbHelper.deleteReminder(info.position);
	        fillData();
	        return true;
		}
		return super.onContextItemSelected(item);
	}
	*/
    private void createReminder() {
        Intent i = new Intent(this, ReminderEditActivity.class);
        startActivityForResult(i, ACTIVITY_CREATE);
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent i = new Intent(this, NotifyList.class);
        i.putExtra(RemindersDbAdapter.KEY_ROWID, id);
        startActivityForResult(i, ACTIVITY_EDIT); 
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        fillData();
    }
}
