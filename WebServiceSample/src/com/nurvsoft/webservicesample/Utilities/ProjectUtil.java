package com.nurvsoft.webservicesample.Utilities;

import java.util.ArrayList;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

import com.nurvsoft.webservicesample.models.User;

public class ProjectUtil
{

	public static ArrayList<User> userCollectionFromServer = new ArrayList<User>();

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

}
