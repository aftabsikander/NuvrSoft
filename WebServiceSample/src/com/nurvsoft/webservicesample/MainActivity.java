package com.nurvsoft.webservicesample;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nurvsoft.webservicesample.Utilities.ParseUtil;
import com.nurvsoft.webservicesample.Utilities.ProjectUtil;
import com.nurvsoft.webservicesample.Utilities.WebUtil;

public class MainActivity extends ActionBarActivity
{

	TextView txt_userData;
	ProgressBar asyncTaskProgressBar;
	Context mContext;
	UserAsyncTask userAsyncTask;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;
		setupReference();
		startAsyncTask();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void setupReference()
	{
		txt_userData = (TextView) findViewById(R.id.textView_userData);
		asyncTaskProgressBar = (ProgressBar) findViewById(R.id.progressBar_AsyncTask);
	}

	public void startAsyncTask()
	{
		if (ProjectUtil.checkNetworkStatus(mContext))
		{
			userAsyncTask = new UserAsyncTask();
			userAsyncTask.url = WebUtil.NURVSOFT_API_GET_USER_BY_ID + "ID HERE";
			userAsyncTask.execute();
		}
		else
		{
			asyncTaskProgressBar.setVisibility(View.GONE);
			Toast.makeText(mContext, "Internet Not Working Please Turn On Internet!", Toast.LENGTH_LONG).show();
			// Show ERROR
		}
	}

	public class UserAsyncTask extends AsyncTask<Void, Void, Boolean>
	{
		String url;
		String responseFromServer;

		@Override
		protected Boolean doInBackground(Void... params)
		{
			URL getUserUrl = null;
			try
			{
				getUserUrl = new URL(url);
				try
				{
					responseFromServer = WebUtil.getDataFromURL(getUserUrl);
					ProjectUtil.userCollectionFromServer = ParseUtil.parseUserCollection(responseFromServer);
					return true;
				}
				catch (IOException e)
				{

					e.printStackTrace();
					return false;
				}
			}
			catch (MalformedURLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}

		}

		@Override
		protected void onPostExecute(Boolean result)
		{

			if (result)
			{
				asyncTaskProgressBar.setVisibility(View.GONE);
				txt_userData.setVisibility(View.VISIBLE);
				txt_userData.setText(ProjectUtil.userCollectionFromServer.get(0).toString());
			}
			else
			{
				// Show Error Message
				Toast.makeText(mContext, "ParseError", Toast.LENGTH_LONG).show();
			}
			super.onPostExecute(result);
		}

	}
}
