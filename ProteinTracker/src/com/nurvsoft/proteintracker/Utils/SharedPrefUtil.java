package com.nurvsoft.proteintracker.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPrefUtil
{

	public static final String PREF_NAME_TAG = "pref_name";
	public static final String PREF_EMAIL_TAG = "pref_email";
	public static final String PREF_PASSWORD_TAG = "pref_password";
	public static final String PREF_PHONE_TAG = "pref_phone";
	public static final String PREF_ADDRESS_TAG = "pref_ddress";
	public static final String PREF_TARGET_TAG = "target_value";
	public static final String PREF_TARGET_BUNDLE_TAG = "B_Target_Value";

	public static String get_Target_value(Context mContext)
	{

		SharedPreferences Tsp = PreferenceManager.getDefaultSharedPreferences(mContext);
		return Tsp.getString(PREF_TARGET_TAG, null);

	}

	public static void set_Target_value(final Context mContext, final String para_target_value)
	{

		SharedPreferences Tsp = PreferenceManager.getDefaultSharedPreferences(mContext);
		Tsp.edit().putString(PREF_TARGET_TAG, para_target_value).commit();

	}

	public static String getUserName(Context mContext)
	{
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
		return sp.getString(PREF_NAME_TAG, null);
	}

	public static void setPrefUserName(final Context context, final String para_userName)
	{
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		sp.edit().putString(PREF_NAME_TAG, para_userName).commit();
	}

	public static String getUserEmail(Context mContext)
	{
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
		return sp.getString(PREF_EMAIL_TAG, null);
	}

	public static void setPrefUserEmail(final Context context, final String para_userEmail)
	{
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		sp.edit().putString(PREF_EMAIL_TAG, para_userEmail).commit();
	}

	public static String getUserPassword(Context mContext)
	{
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
		return sp.getString(PREF_PASSWORD_TAG, null);
	}

	public static void setPrefUserPassword(final Context context, final String para_userPassword)
	{
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		sp.edit().putString(PREF_PASSWORD_TAG, para_userPassword).commit();
	}

	public static String getUserPhone(Context mContext)
	{
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
		return sp.getString(PREF_PHONE_TAG, null);
	}

	public static void setPrefUserPhone(final Context context, final String para_userPhone)
	{
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		sp.edit().putString(PREF_PHONE_TAG, para_userPhone).commit();
	}

	public static String getUserAddress(Context mContext)
	{
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
		return sp.getString(PREF_ADDRESS_TAG, null);
	}

	public static void setPrefUserAddress(final Context context, final String para_userAddress)
	{
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		sp.edit().putString(PREF_ADDRESS_TAG, para_userAddress).commit();
	}

}
