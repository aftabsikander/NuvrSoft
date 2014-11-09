/**
 * 
 */
package com.example.videosample.utilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

/**
 * @author Aftab-Ovrlod
 * @date Apr 11, 2014
 * 
 */
public class ProjectUtli
{
	public static long _splashDelay = 5000; // 5 seconds
	public static final int MESSAGE_LENGTH_LONG = Toast.LENGTH_LONG;
	public static final int MESSAGE_LENGTH_SHORT = Toast.LENGTH_SHORT;
	public static final int _noAnimation = Intent.FLAG_ACTIVITY_NO_ANIMATION;
	public static final Integer TWITTER_POST_COUNT = 140;

	public static String DEVICE_HOME_PACKAGE_NAME = "";
	public static final String NAMAZ_SALAH_TAG = " salah is on ";

	public static final String GOOGLE_PLAY_APPLICATION_SHARE_LINK = "https://play.google.com/store/apps/details?id=com.aftabsikander.apps.aazan";
	public static final String SHARE_APPLICATION_MESSAGE = "Let me recommend you this application\n" + GOOGLE_PLAY_APPLICATION_SHARE_LINK;

	public static final int NOTIFICATION_ID = 1;
	public static String localPathDirectory;

	public static void setupLocalPathForVideoDirectory(Context mContext)
	{
		localPathDirectory = "android.resource://" + mContext.getPackageName() + "/raw/";
	}

	// #region Alarm NotificationID Constant

	public static final int FAJAR_NAMAZ_NOTIFICATION_ID = 1;
	public static final int ZOHAR_NAMAZ_NOTIFICATION_ID = 2;
	public static final int ASAR_NAMAZ_NOTIFICATION_ID = 3;
	public static final int MAGHRIB_NAMAZ_NOTIFICATION_ID = 4;
	public static final int EISHA_NAMAZ_NOTIFICATION_ID = 5;
	public static final int JUMMA_NAMAZ_NOTIFICATION_ID = 6;

	public static final int AM = 0;
	public static final int PM = 1;
	// #endregion

	// #region Constant for Share Bundle

	public static final String NAMAZ_OBJECT_ID_TAG = "NAMAZ_OBJECT_ID";

	// #endregion

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

	// #region Constants for the Share application

	public static final String FACEBOOK_APPLICATION_PACKAGE_NAME = "com.facebook.katana";
	public static final String TWITTER_APPLICATION_PACKAGE_NAME = "com.twitter.android";
	public static final String TWITDROID_APPLICATION_PACKAGE_NAME = "com.twidroid";
	public static final String TWEETCASTER_APPLICATION_PACKAGE_NAME = "com.handmark.tweetcaster";
	public static final String THEDECK_APPLICATION_PACKAGE_NAME = "com.thedeck.android";

	// #endregion

	// #region helper Methods for share Intents

	public static Intent startShareIntent(Context mContext, String applicationName, String applicationLink)
	{
		// Standard message to send
		String msg = "Let me recommend you this application\n" + GOOGLE_PLAY_APPLICATION_SHARE_LINK;
		final String TAG = "IntentShare";
		Intent share = new Intent(Intent.ACTION_SEND);
		share.setType("text/plain");

		List<Intent> targetedShareIntents = new ArrayList<Intent>();
		Intent targetedShareIntent = null;

		List<ResolveInfo> resInfo = mContext.getPackageManager().queryIntentActivities(share, 0);
		if (!resInfo.isEmpty())
		{

			for (ResolveInfo resolveInfo : resInfo)
			{
				String packageName = resolveInfo.activityInfo.packageName;
				targetedShareIntent = new Intent(android.content.Intent.ACTION_SEND);
				targetedShareIntent.setType("text/plain");

				// Find twitter: com.twitter.android...
				if (FACEBOOK_APPLICATION_PACKAGE_NAME.equals(packageName))
				{
					Log.d(TAG, "Facebook Message: " + msg);
					targetedShareIntent.putExtra(Intent.EXTRA_SUBJECT, applicationLink);

					targetedShareIntent.putExtra(android.content.Intent.EXTRA_TEXT, msg);

				}
				else if (TWITTER_APPLICATION_PACKAGE_NAME.equals(packageName))
				{
					targetedShareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, applicationName + " URL:" + applicationLink);
					String twitterShortDesc = getShorterStringForTwitterPost(msg);
					targetedShareIntent.putExtra(android.content.Intent.EXTRA_TEXT, twitterShortDesc);

				}
				else
				{
					targetedShareIntent.putExtra(android.content.Intent.EXTRA_TEXT, msg);
				}

				targetedShareIntent.setPackage(packageName);
				targetedShareIntents.add(targetedShareIntent);
			}

		}

		Intent chooserIntent = Intent.createChooser(targetedShareIntents.remove(0), "Share");
		chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetedShareIntents.toArray(new Parcelable []
		{}));
		return chooserIntent;
	}

	public static Intent startShareIntent(Context mContext, String para_title, String para_URL, String activityName)
	{

		// Standard message to send
		String msg = para_title + NAMAZ_SALAH_TAG + para_URL;
		final String TAG = "IntentShare";
		Intent share = new Intent(Intent.ACTION_SEND);
		share.setType("text/plain");

		List<Intent> targetedShareIntents = new ArrayList<Intent>();
		Intent targetedShareIntent = null;

		List<ResolveInfo> resInfo = mContext.getPackageManager().queryIntentActivities(share, 0);
		if (!resInfo.isEmpty())
		{

			for (ResolveInfo resolveInfo : resInfo)
			{
				String packageName = resolveInfo.activityInfo.packageName;
				targetedShareIntent = new Intent(android.content.Intent.ACTION_SEND);
				targetedShareIntent.setType("text/plain");

				// Find twitter: com.twitter.android...
				if (FACEBOOK_APPLICATION_PACKAGE_NAME.equals(packageName))
				{
					Log.d(TAG, "Facebook Message: " + msg);
					targetedShareIntent.putExtra(android.content.Intent.EXTRA_TEXT, msg);
				}
				else if (TWITTER_APPLICATION_PACKAGE_NAME.equals(packageName))
				{
					Log.d(TAG, "Twitter Title Message: " + para_title);
					Log.d(TAG, "Twitter Subject Message: " + para_URL);
					targetedShareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, para_title);
					if (activityName.contentEquals("NamazTime"))
					{
						String twitterShortDesc = getShorterStringForTwitterPost(para_title + NAMAZ_SALAH_TAG + para_URL);
						targetedShareIntent.putExtra(android.content.Intent.EXTRA_TEXT, twitterShortDesc);
					}
					else
					{
						targetedShareIntent.putExtra(android.content.Intent.EXTRA_TEXT, para_URL);
					}

				}
				/*
				 * else if (TWITDROID_APPLICATION_PACKAGE_NAME.equals(packageName)) { Log.d(TAG,
				 * "Twitter Title Message: " + para_title); Log.d(TAG, "Twitter Subject Message: " + para_URL);
				 * targetedShareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, para_title);
				 * targetedShareIntent.putExtra(android.content.Intent.EXTRA_TEXT, Uri.encode(para_title + "\r\n" +
				 * para_URL)); } else if (TWEETCASTER_APPLICATION_PACKAGE_NAME.equals(packageName)) { Log.d(TAG,
				 * "Twitter Title Message: " + para_title); Log.d(TAG, "Twitter Subject Message: " + para_URL);
				 * targetedShareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, para_title);
				 * targetedShareIntent.putExtra(android.content.Intent.EXTRA_TEXT, Uri.encode(para_title + "\r\n" +
				 * para_URL)); } else if (THEDECK_APPLICATION_PACKAGE_NAME.equals(packageName)) { Log.d(TAG,
				 * "Twitter Title Message: " + para_title); Log.d(TAG, "Twitter Subject Message: " + para_URL);
				 * targetedShareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, para_title);
				 * targetedShareIntent.putExtra(android.content.Intent.EXTRA_TEXT, Uri.encode(para_title + "\r\n" +
				 * para_URL)); }
				 */
				else
				{
					// Rest of Apps
					// Log.d(TAG, "Rest of the app: " + msg);
					targetedShareIntent.putExtra(android.content.Intent.EXTRA_TEXT, msg);
				}

				targetedShareIntent.setPackage(packageName);
				targetedShareIntents.add(targetedShareIntent);
			}

		}
		Intent chooserIntent = Intent.createChooser(targetedShareIntents.remove(0), "Share");
		chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetedShareIntents.toArray(new Parcelable []
		{}));
		return chooserIntent;

	}

	public static String getShorterStringForTwitterPost(String para_content)
	{
		int current_contentCharacterCount = para_content.length();
		String generatedContentForTwitter = "";
		char [] para_contentSplitIntoCharacter = para_content.toCharArray();
		for (int i = 0; i < current_contentCharacterCount; i++)
		{
			if (i == TWITTER_POST_COUNT - 2)
			{
				generatedContentForTwitter += "..";
				break;
			}

			else
			{
				generatedContentForTwitter += para_contentSplitIntoCharacter[i];
			}
		}
		return generatedContentForTwitter;
	}

	// #endregion

	// #endregion

	// #region Helper method for creating Spannable
	public static Spannable createSpanWords(Context mContext, String timeHeading, String namazTime)
	{
		String finalText = timeHeading + ": " + namazTime;
		Spannable wordtoSpan = new SpannableString(finalText);
		wordtoSpan.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(android.R.color.black)), 0, timeHeading.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		wordtoSpan.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(android.R.color.holo_orange_dark)), timeHeading.length() + 2, finalText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

		return wordtoSpan;
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

	// #region Helper Methods for Home Screen Package Name

	public static String getHomeLauncherPackageName(Context mContext)
	{
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		ResolveInfo resolveInfo = mContext.getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
		String currentHomePackage = resolveInfo.activityInfo.packageName;
		// DebugLog.showLogs("HomeScreenPackageName", currentHomePackage);
		return currentHomePackage;

	}

	// #endregion

}
