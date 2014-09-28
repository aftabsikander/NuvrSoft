/**
 * 
 */
package com.aiman.agecalculator.util;

import java.io.File;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import com.aiman.agecalculator.R;
import com.aiman.agecalculator.database.AgeCalculatorDatabaseHelper;

/**
 * @author Aftab-Ali
 * @date Apr 11, 2014
 * 
 */
public class ProjectUtli
{
	public static long _splashDelay = 5000; // 5 seconds
	public static final int MESSAGE_LENGTH_LONG = Toast.LENGTH_LONG;
	public static final int MESSAGE_LENGTH_SHORT = Toast.LENGTH_SHORT;
	public static final int _noAnimation = Intent.FLAG_ACTIVITY_NO_ANIMATION;

	public static AgeCalculatorDatabaseHelper ageCalculatorDatabaseHelper;

	// #region Internet Helper Methods

	public static boolean isWiFiEnabled(final Context context)
	{
		final WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		return wifiManager.isWifiEnabled();
	}

	/***
	 * This method is used to check for network connection if the user is connected to WI-FI or network mobile we can
	 * access Network related method
	 * 
	 * @param context
	 *             the context
	 * @return if WI-FI or Mobile net is on return true Else false;
	 */
	public static Boolean checkNetworkStatus(Context context)
	{

		final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		final NetworkInfo wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		final NetworkInfo mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

		if ((wifi.isAvailable() && wifi.isConnected()) || (mobile.isAvailable() && mobile.isConnected()))
		{
			return true;
		}
		else
		{
			return false;
		}

	}

	// #endregion

	// #region Helper Methods for AlarmManager And Get Alarm PendingIntent

	// #endregion

	// #region Helper method for finding the current month calendar

	public static int getCurrentIndexForMonth(Context mContext)
	{
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MONTH);
	}

	// #endregion

	// #region helper Method for database existing
	public static boolean doesDatabaseExist(ContextWrapper context, String dbName)
	{
		File dbFile = context.getDatabasePath(dbName);
		return dbFile.exists();
	}

	/**
	 * Check if the database already exist to avoid re-copying the file each time you open the application.
	 * 
	 * @return true if it exists, false if it doesn't
	 */
	public static boolean checkDataBase(Context mContext)
	{
		boolean checkdb = false;
		try
		{
			String myPath = mContext.getFilesDir().getAbsolutePath().replace("files", "databases") + File.separator + mContext.getString(R.string.db_name);
			File dbfile = new File(myPath);
			checkdb = dbfile.exists();
		}
		catch (SQLiteException e)
		{
			System.out.println("Database doesn't exist");
		}

		return checkdb;
	}

	/**
	 * Check if the database already exist to avoid re-copying the file each time you open the application.
	 * 
	 * @return true if it exists, false if it doesn't
	 */
	public static Boolean checkdbMethod(Context context)
	{
		File database = context.getDatabasePath(context.getString(R.string.db_name));

		if (!database.exists())
		{
			// Database does not exist so copy it from assets here
			Log.i("Database", "Not Found");
			return false;
		}
		else
		{
			Log.i("Database", "Found");
			return true;
		}
	}

	// #endregion

	// #region general Helper Methods

	/***
	 * This method is used to allow Network stream works on UI treads.
	 */
	@SuppressLint("NewApi")
	public static void SetStrickPolicy()
	{
		if (android.os.Build.VERSION.SDK_INT > 9)
		{
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

	}

	/**
	 * This method is used to go across the application i.e opening new Activities
	 */
	public static void genericIntent(Context context, Class<?> className, Bundle datapassingBundle)
	{
		Intent intent = new Intent();
		intent.setClass(context, className);
		intent.setFlags(_noAnimation);
		if (datapassingBundle != null)
			intent.putExtras(datapassingBundle);
		context.startActivity(intent);
	}

	/**
	 * This Method is use to get the Home LauncherName from the device so that
	 * 
	 */
	public static void GotoHomeScreen(Context context)
	{
		Intent startMain = new Intent(Intent.ACTION_MAIN);
		startMain.addCategory(Intent.CATEGORY_HOME);
		startMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(startMain);
	}

	/**
	 * This method is used to display message on Screen
	 * 
	 * @param context
	 *             From where this function is being called.
	 * @param message
	 *             The message we want to display.
	 * @param duration
	 *             How long the message should stay on Screen.
	 */
	public static void showToast(Context context, String message, int duration)
	{
		Toast.makeText(context, message, duration).show();

	}

	// #endregion

}
