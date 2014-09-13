package com.nuvrsoft.employeeinfo;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPrefUtil
{
	public static final String PREF_NAME_TAG = "pref_name";
	public static final String PREF_EMAIL_TAG = "pref_email";
	public static final String PREF_PHONE_TAG = "pref_phone";
	public static final String PREF_ADDRESS_TAG = "pref_address";
	public static final String PREF_GENDER_TAG = "pref_gender";

	public static String getEmployeeName(Context mContext)
	{
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
		return sp.getString(PREF_NAME_TAG, null);
	}

	public static void setPrefEmployeeName(final Context context, final String para_employeeName)
	{
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		sp.edit().putString(PREF_NAME_TAG, para_employeeName).commit();
	}

	public static String getEmployeeEmail(Context mContext)
	{
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
		return sp.getString(PREF_EMAIL_TAG, null);
	}

	public static void setPrefEmployeeEmail(final Context context, final String para_employeeEmail)
	{
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		sp.edit().putString(PREF_EMAIL_TAG, para_employeeEmail).commit();
	}

	public static String getEmployeeAddress(Context mContext)
	{
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
		return sp.getString(PREF_ADDRESS_TAG, null);
	}

	public static void setPrefEmployeeAddress(final Context context, final String para_employeeAddress)
	{
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		sp.edit().putString(PREF_ADDRESS_TAG, para_employeeAddress).commit();
	}

	public static String getEmployeePhone(Context mContext)
	{
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
		return sp.getString(PREF_PHONE_TAG, null);
	}

	public static void setPrefEmployeePhone(final Context context, final String para_employeePhone)
	{
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		sp.edit().putString(PREF_PHONE_TAG, para_employeePhone).commit();
	}

	public static String getEmployeeGender(Context mContext)
	{
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
		return sp.getString(PREF_GENDER_TAG, null);
	}

	public static void setPrefEmployeeGender(final Context context, final String para_employeeGender)
	{
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		sp.edit().putString(PREF_GENDER_TAG, para_employeeGender).commit();
	}
}
