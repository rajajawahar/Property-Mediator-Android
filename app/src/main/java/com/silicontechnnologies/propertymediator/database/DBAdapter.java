package com.silicontechnnologies.propertymediator.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter
{
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "name";
	
	public static final String KEY_NOTES = "notes";
	public static final String KEY_PAN = "pan";
	public static final String KEY_PHONE = "phone";
	public static final String KEY_EMAIL = "email";

	
	private DatabaseHelper DbHelper;
	private SQLiteDatabase Db;
	private static final String DATABASE_NAME = "PropertyMediator";
	private static final String SQLITE_TABLE = "Contacts";
	private static final int DATABASE_VERSION = 1;
	private final Context Ctx;

	private static final String DATABASE_CREATE = "CREATE TABLE if not exists "
			+ SQLITE_TABLE + " (" + KEY_ROWID
			+ " integer PRIMARY KEY autoincrement," + KEY_NAME + "," + KEY_NOTES + "," + KEY_PAN + "," + KEY_PHONE + "," + KEY_EMAIL + ","
			+ " UNIQUE (" + KEY_NAME + "));";

	private static class DatabaseHelper extends SQLiteOpenHelper 
	{

		DatabaseHelper(Context context)
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		public void onCreate(SQLiteDatabase db)
		{
			db.execSQL(DATABASE_CREATE);
		}

		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
		{
			db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
			onCreate(db);
		}
	}

	public DBAdapter(Context ctx) 
	{
		this.Ctx = ctx;
	}

	public DBAdapter open() throws SQLException
	{
		DbHelper = new DatabaseHelper(Ctx);
		Db = DbHelper.getWritableDatabase();
		return this;
	}

	public void close() 
	{
		if (DbHelper != null) 
		{
			DbHelper.close();
		}
	}

	public long createContact(String name,String notes,String pan,String phone, String email) 
	{
		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_NAME, name);
		
		initialValues.put(KEY_NOTES, notes);
		initialValues.put(KEY_PAN, pan);
		initialValues.put(KEY_PHONE, phone);
		initialValues.put(KEY_EMAIL, email);
		return Db.insert(SQLITE_TABLE, null, initialValues);
	}

	
	public boolean deleteContact(String inputText) 
	{
		return Db.delete(SQLITE_TABLE, KEY_NAME + " like '%" + inputText + "%'", null) > 0;
    }

	

	public Cursor fetchAllContacts() 
	{
		Cursor mCursor = Db.query(SQLITE_TABLE, new String[] { KEY_ROWID,KEY_NAME,KEY_NOTES,KEY_PAN,KEY_PHONE,KEY_EMAIL}, null, null,null, null, null,null);

		if (mCursor != null)
		{
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	
}