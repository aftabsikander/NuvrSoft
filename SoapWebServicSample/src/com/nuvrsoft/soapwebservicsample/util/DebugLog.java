package com.nuvrsoft.soapwebservicsample.util;

import android.util.Log;

public class DebugLog
{

	/**
	 * This Method shows the activity is being killed successfully by showing log
	 * 
	 * @param _activityName
	 *             Give the activity name you want to kill
	 */
	public static void showActivityKillLog(String _activityName)
	{

		Log.i(_activityName, "Activity being killed");
	}

	public static void showLogs(String tag, String message)
	{
		Log.i(tag, message);
	}

	public static void showOriginalPattern(String message)
	{
		Log.i("OriginalPattern", message);
	}

	public static void showEncryptedPattern(String message)
	{
		Log.i("EncryptedPattern", message);
	}

	public static void showExceptionsLog(String Tag, String message)
	{
		Log.e(Tag, message);
	}
}
