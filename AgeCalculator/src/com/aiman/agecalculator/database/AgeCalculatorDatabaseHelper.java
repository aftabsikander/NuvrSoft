package com.aiman.agecalculator.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.aiman.agecalculator.model.Age;

public class AgeCalculatorDatabaseHelper extends SQLiteOpenHelper
{

	// Logcat tag
	private static final String TAG = "DatabaseHelper";

	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "AgeCalculator";

	// Table Names
	private static final String TABLE_AGE = "Age";

	// Common column names
	private static final String KEY_AGE_ID = "id";
	private static final String KEY_AGE_DATE = "date";
	private static final String KEY_AGE_MONTH = "month";
	private static final String KEY_AGE_YEAR = "year";
	private static final String KEY_AGE_CURRENT_AGE = "currentage";

	// Table Create Statements
	// AGE table create statement
	private static final String CREATE_TABLE_AGE = "CREATE TABLE " + TABLE_AGE + "(" + KEY_AGE_ID + " INTEGER PRIMARY KEY," + KEY_AGE_DATE + " TEXT," + KEY_AGE_MONTH + " TEXT," + KEY_AGE_YEAR + " TEXT," + KEY_AGE_CURRENT_AGE + " TEXT " + ")";

	public AgeCalculatorDatabaseHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		// creating required tables
		db.execSQL(CREATE_TABLE_AGE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{

		// on upgrade drop older tables
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_AGE);

		// create new tables
		onCreate(db);
	}

	// #region Helper Method code for Age Table #Insert Update Delete

	/***
	 * This method is used to insert record into the Age Table
	 * 
	 * @param age
	 * 
	 * @return return the ID from database record
	 */
	public long insertAge(Age age)
	{
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_AGE_DATE, age.getDate());
		values.put(KEY_AGE_MONTH, age.getMonth());
		values.put(KEY_AGE_YEAR, age.getYear());
		values.put(KEY_AGE_CURRENT_AGE, age.getCurrentAge());

		// insert row
		long age_id = db.insert(TABLE_AGE, null, values);

		return age_id;
	}

	/***
	 * This method is used to get the Age Record from the database
	 * 
	 * @param age_id
	 *             Pass the age ID
	 * @return return the record from table and create and Age Object
	 */
	public Age getAge(long age_id)
	{
		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT  * FROM " + TABLE_AGE + " WHERE " + KEY_AGE_ID + " = " + age_id;

		Log.i(TAG, selectQuery);

		Cursor c = db.rawQuery(selectQuery, null);

		if (c != null)
			c.moveToFirst();

		Age ageObject = new Age();
		ageObject.setID(c.getInt(c.getColumnIndex(KEY_AGE_ID)));
		ageObject.setDate((c.getString(c.getColumnIndex(KEY_AGE_DATE))));
		ageObject.setMonth((c.getString(c.getColumnIndex(KEY_AGE_MONTH))));
		ageObject.setYear(c.getString(c.getColumnIndex(KEY_AGE_YEAR)));
		ageObject.setCurrentAge(c.getString(c.getColumnIndex(KEY_AGE_CURRENT_AGE)));

		return ageObject;
	}

	/***
	 * This method is used to Update the Age Record in the database
	 * 
	 * @param ageObject
	 *             Pass the Age Object
	 * @return After Successful update of the record it returns the record ID;
	 */
	public int updateAge(Age ageObject)
	{
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_AGE_DATE, ageObject.getDate());
		values.put(KEY_AGE_MONTH, ageObject.getMonth());
		values.put(KEY_AGE_YEAR, ageObject.getYear());
		values.put(KEY_AGE_CURRENT_AGE, ageObject.getCurrentAge());

		// updating row
		return db.update(TABLE_AGE, values, KEY_AGE_ID + " = ?", new String []
		{ String.valueOf(ageObject.getID()) });
	}

	/***
	 * This Method is used to delete object from Age Table
	 * 
	 * @param age_id
	 */
	public void deleteAge(long age_id)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_AGE, KEY_AGE_ID + " = ?", new String []
		{ String.valueOf(age_id) });
	}

	// #endregion

}
