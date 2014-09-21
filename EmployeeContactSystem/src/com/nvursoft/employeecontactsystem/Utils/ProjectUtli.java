package com.nvursoft.employeecontactsystem.Utils;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.nvursoft.employeecontactsystem.R;
import com.nvursoft.employeecontactsystem.models.Employee;

public class ProjectUtli
{
	public static long _splashDelay = 5000; // 5 seconds
	public static final int MESSAGE_LENGTH_LONG = Toast.LENGTH_LONG;
	public static final int MESSAGE_LENGTH_SHORT = Toast.LENGTH_SHORT;
	public static final int _noAnimation = Intent.FLAG_ACTIVITY_NO_ANIMATION;

	// #region Static object Collection Variables

	public static ArrayList<Employee> employeeCollecion = new ArrayList<Employee>();

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
	public static void genericIntent(Context context, Class className, Bundle datapassingBundle)
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

	/*
	 * public static void getCustomeTypeface(Context context) { customeAhronbdFont =
	 * Typeface.createFromAsset(context.getAssets(), "ahronbd.ttf"); }
	 */

	public static void openDialogBoxScreen(final Context context, String alertTitle, String closeButtonTitle, String positiveButtonTitle)
	{
		LayoutInflater factory = LayoutInflater.from(context);
		View layout = factory.inflate(R.layout.dailog_complete, null);

		AlertDialog.Builder alert = new AlertDialog.Builder(context);
		alert.setTitle(alertTitle);
		alert.setIcon(android.R.drawable.ic_dialog_info);
		alert.setView(layout);

		// TextView aboutTextView = (TextView) layout.findViewById(R.id.textView_dialog);

		alert.setPositiveButton(positiveButtonTitle, new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int whichButton)
			{
				ProjectUtli.showToast(context, "Positive Button is Clicked", ProjectUtli.MESSAGE_LENGTH_SHORT);
			}
		});

		alert.setNegativeButton(closeButtonTitle, new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int whichButton)
			{
				ProjectUtli.showToast(context, "Negative Button is Clicked", ProjectUtli.MESSAGE_LENGTH_SHORT);
			}
		});

		alert.show();
	}

	// #endregion

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

		if (wifi.isAvailable())
		{
			// Toast.makeText(this, "Wifi", Toast.LENGTH_LONG).show();
			return true;
		}
		/*
		 * else if (mobile.isAvailable()) { return true; // Toast.makeText(this, "Mobile 3G ",
		 * Toast.LENGTH_LONG).show(); }
		 */
		else
		{
			return false;
			// Toast.makeText(this, "No Network ", Toast.LENGTH_LONG).show();
		}

	}

	// #endregion

}
