package com.nuvrsoft.soapwebservicsample.util;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Patterns;
import android.widget.Toast;

public class ProjectUtli
{
	public static long _splashDelay = 5000; // 5 seconds
	public static final int MESSAGE_LENGTH_LONG = Toast.LENGTH_LONG;
	public static final int MESSAGE_LENGTH_SHORT = Toast.LENGTH_SHORT;
	public static final int _noAnimation = Intent.FLAG_ACTIVITY_NO_ANIMATION;
	public static final Integer TWITTER_POST_COUNT = 140;

	// #region Constant for the Bundle Passing

	public static final String BUNDLE_CATEGORY_ID_TAG = "CATEGORY_ID";

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

	// #region Helper Methods for the UserAccount

	/***
	 * This method is used to get all the user account from device and return the set<String> containing all its
	 * account
	 * 
	 * @param mContext
	 * @return return the account name from the device
	 */
	public static Set<String> getUserAccountFromDevice(Context mContext)
	{
		final Account [] accounts = AccountManager.get(mContext).getAccounts();
		final Set<String> emailSet = new HashSet<String>();
		for (Account account : accounts)
		{
			if (Patterns.EMAIL_ADDRESS.matcher(account.name).matches())
			{
				emailSet.add(account.name);
			}
		}
		return emailSet;
	}

	// #endregion

	// #region Validation Methods

	// #region Email Regex
	public static final String DOTNET_EMAIL_REGEX =
											"/^((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?$/i";
	public static String EMAIL_REGEX_PATTERN = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+";

	public static final Pattern CHECK_EMAIL_ADDRESS_PATTERN = Pattern.compile(EMAIL_REGEX_PATTERN);

	// #endregion

	/**
	 * This method is used to validate Email address input by the user
	 * 
	 * @param email
	 *             Entered Email address
	 * @return return true if email is in correct format else return false
	 */
	public static boolean isValidEmail(String email)
	{
		return ProjectUtli.CHECK_EMAIL_ADDRESS_PATTERN.matcher(email).matches();
	}

	// #endregion

}
