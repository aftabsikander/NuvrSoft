package com.nurvsoft.webservicesample.Utilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class WebUtil
{

	public static final String NURVSOFT_API_BASE_URL = "http://nurvsoft.pk/android/test.php/";
	public static final String NURVSOFT_API_GET_USER = NURVSOFT_API_BASE_URL + "hello";
	public static final String NURVSOFT_API_GET_USER_BY_ID = NURVSOFT_API_BASE_URL + "hello?id=";

	// #region Helper methods for URL Response Using okHTTP

	public static String getDataFromURL(URL url) throws IOException
	{
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(url).build();
		Response response = client.newCall(request).execute();

		return response.body().string();

	}

	public static URL generateUserDataByIDURL(String para_userID)
	{
		URL url = null;
		try
		{
			url = new URL(NURVSOFT_API_GET_USER_BY_ID + para_userID);
			return url;
		}
		catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
