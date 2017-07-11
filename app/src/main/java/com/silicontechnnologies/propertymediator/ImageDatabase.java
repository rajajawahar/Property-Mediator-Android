package com.silicontechnnologies.propertymediator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ImageDatabase {
	public static SQLiteDatabase db;
	public static final String KEY_imageId = "_id";
	public static String KEY_name = "Name";
	public static final String KEY_Type = "Type";

	public static final String KEY_phone = "Phone";
	public static final String KEY_email = "Email";
	public static final String KEY_address = "Address";

	public static final String KEY_detail = "Details";
	public static final String KEY_path = "Pathname";

	private static final String TAG = "Property Form";
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	private static final String DATABASE_NAME = "Realss.db";
	private static final String SQLITE_TABLE = "Image";
	private static final int DATABASE_VERSION = 1;

	private final Context mCtx;

	private static final String DATABASE_CREATE = "CREATE TABLE if not exists "
			+ SQLITE_TABLE + " (" + KEY_imageId
			+ " integer PRIMARY KEY autoincrement," + KEY_name + "," + KEY_Type
			+ "," + KEY_phone + "," + KEY_email + "," + KEY_address + ","
			+ KEY_detail + "," + KEY_path + "," + " UNIQUE (" + KEY_imageId
			+ "));";

	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			Log.w(TAG, DATABASE_CREATE);
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
			onCreate(db);
		}
	}

	public ImageDatabase(Context ctx) {
		this.mCtx = ctx;
	}

	public ImageDatabase open() throws SQLException {
		mDbHelper = new DatabaseHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		if (mDbHelper != null) {
			mDbHelper.close();
		}
	}

	public long createProperty(String name, String type, String phone,
			String email, String address, String details, String path) {

		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_name, name);
		initialValues.put(KEY_Type, type);
		initialValues.put(KEY_phone, phone);
		initialValues.put(KEY_email, email);
		initialValues.put(KEY_address, address);
		initialValues.put(KEY_detail, details);
		initialValues.put(KEY_path, path);

		return mDb.insert(SQLITE_TABLE, null, initialValues);
	}
	
	

	public boolean deleteContact(String inputText) 
	{
		return mDb.delete(SQLITE_TABLE, KEY_name + " like '%" + inputText + "%'", null) > 0;
    }


	public Cursor fetchpropertyByType(String inputText) throws SQLException {
		Log.w(TAG, inputText);
		Cursor mCursor = null;
		/*if (inputText == null || inputText.length() == 0) {
			mCursor = mDb.query(SQLITE_TABLE, new String[] { KEY_imageId,
					KEY_name, KEY_Type, KEY_phone, KEY_email, KEY_address,
					KEY_detail, KEY_path }, null, null, null, null, null);

		} else {
			*/mCursor = mDb.query(true, SQLITE_TABLE, new String[] { KEY_imageId,
					KEY_name, KEY_Type, KEY_phone, KEY_email, KEY_address,
					KEY_detail, KEY_path }, KEY_Type + " like '%" + inputText
					+ "%'", null, null, null, null, null);
	//	}
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;

	}

	/*public Cursor fetchpropertyByName(String inputText) throws SQLException {
		Log.w(TAG, inputText);
		Cursor mCursor = null;
		if (inputText == null || inputText.length() == 0) {
			mCursor = mDb.query(SQLITE_TABLE, new String[] { KEY_imageId,
					KEY_name, KEY_Type, KEY_phone, KEY_email, KEY_address,
					KEY_detail, KEY_path }, null, null, null, null, null);

		} else {
			mCursor = mDb.query(true, SQLITE_TABLE, new String[] { KEY_imageId,
					KEY_name, KEY_Type, KEY_phone, KEY_email, KEY_address,
					KEY_detail, KEY_path }, KEY_name + " like '%" + inputText
					+ "%'", null, null, null, null, null);
		}
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;

	}*/
//
//	public Cursor empty() {
//		Cursor cur = db.rawQuery("SELECT COUNT(*) FROM Image", null);
//		if (cur != null) {
//			cur.moveToFirst(); // Always one row returned.
//			if (cur.getInt(0) == 0) { // Zero count means empty table.
//
//			}
//		}
//		return cur;
//	}

	public Cursor fetchAllproperty() {

		Cursor mCursor = mDb.query(SQLITE_TABLE, new String[] { KEY_imageId,
				KEY_name, KEY_Type, KEY_phone, KEY_email, KEY_address,
				KEY_detail, KEY_path }, null, null, null, null, null);

		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public void insertSomeproperty() {

		createProperty("Susi", "House", "9790266106", "susi@gmail.com",
				"Chennai", "5BHK", " ");
		createProperty("Anbu", "Land", "989898989", "anbu@gmail.com", "Chennai",
				"3BHK", " ");

	}

}
