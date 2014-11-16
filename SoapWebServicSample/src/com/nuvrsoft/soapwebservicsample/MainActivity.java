package com.nuvrsoft.soapwebservicsample;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.nuvrsoft.soapwebservicsample.model.UserProfile;
import com.nuvrsoft.soapwebservicsample.util.LinksUtils;
import com.nuvrsoft.soapwebservicsample.util.ProjectUtli;

public class MainActivity extends ActionBarActivity
{
	Context mContext;
	TextView textView_ResponseFromServer;
	Button btn_gotoRegisterScreen, btn_login;

	EditText userNameEditText, userPasswordEditText;
	CheckBox checkBox_keepMeLogedIN;

	String userName, userPassword;
	LoginAsyncTask loginAsyncTask;
	HelloWorldAsyncTask helloWorldAsyncTask;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;
		setupControlReference();
		// startHelloWorldAsyncTask();
		startUserLoginAsyncTask();
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

	public void setupControlReference()
	{
		textView_ResponseFromServer = (TextView) findViewById(R.id.textView_ResponseFromServer);
	}

	public void startHelloWorldAsyncTask()
	{
		helloWorldAsyncTask = new HelloWorldAsyncTask();
		helloWorldAsyncTask.context = mContext;
		helloWorldAsyncTask.execute();
	}

	public void startUserLoginAsyncTask()
	{
		loginAsyncTask = new LoginAsyncTask();
		loginAsyncTask.context = mContext;
		String [] userArr = new String []
		{ "aftabali", "aftab123" };
		loginAsyncTask.execute(userArr);
	}

	public class HelloWorldAsyncTask extends AsyncTask<Void, Void, String>
	{
		private ProgressDialog progressDialog;
		Context context;
		String responseFromServer;

		@Override
		protected void onPreExecute()
		{
			super.onPreExecute();
			progressDialog = new ProgressDialog(context);
			progressDialog.setMessage("Please wait...");
			progressDialog.setIndeterminate(true);
			progressDialog.show();
		}

		@Override
		protected String doInBackground(Void... params)
		{

			responseFromServer = getHelloWorldResponse();
			return responseFromServer;

		}

		@Override
		protected void onPostExecute(String result)
		{
			super.onPostExecute(result);
			progressDialog.hide();
			if (TextUtils.isEmpty(responseFromServer))
			{
				ProjectUtli.showToast(context, "Response From Server: " + responseFromServer, ProjectUtli.MESSAGE_LENGTH_SHORT);
				textView_ResponseFromServer.setText(responseFromServer);
			}

			else
				ProjectUtli.showToast(context, "Response From Server: " + "Error Occured!", ProjectUtli.MESSAGE_LENGTH_SHORT);
		}

		public String getHelloWorldResponse()
		{
			SoapObject request = new SoapObject(LinksUtils.NAMESPACE, LinksUtils.METHOD_NAME_HELLOWORLD);

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request);

			HttpTransportSE androidHttpTransport = new HttpTransportSE(LinksUtils.WEB_SERVICE_IIS_OFFICE_URL, LinksUtils.SERVER_RESPONSE_TIMEOUT);

			try
			{
				androidHttpTransport.call(LinksUtils.SOAP_ACTION_HELLOWORLD, envelope);

				// SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

				SoapObject resultsRequestSOAP = (SoapObject) envelope.bodyIn;

				return parseHelloWorldFromServer(resultsRequestSOAP);
				// String userID = resultsRequestSOAP.getProperty(0).toString();

			}
			catch (Exception e)
			{
				ProjectUtli.showToast(context, e.getMessage(), ProjectUtli.MESSAGE_LENGTH_SHORT);
				return null;
			}

		}

		public String parseHelloWorldFromServer(SoapObject resultsRequestSOAP)
		{
			String userResponse = resultsRequestSOAP.getProperty(0).toString();
			return userResponse;
		}

	}

	public class LoginAsyncTask extends AsyncTask<String [], Void, UserProfile>
	{
		private ProgressDialog progressDialog;
		Context context;
		String [] userLoginData;

		@Override
		protected void onPreExecute()
		{
			super.onPreExecute();
			progressDialog = new ProgressDialog(context);
			progressDialog.setMessage("Please wait...");
			progressDialog.setIndeterminate(true);
			progressDialog.show();
		}

		@Override
		protected UserProfile doInBackground(String []... params)
		{
			userLoginData = params[0];

			return sendLoginData(userLoginData[0], userLoginData[1]);

		}

		@Override
		protected void onPostExecute(UserProfile result)
		{
			super.onPostExecute(result);
			progressDialog.hide();
			if (result != null)
			{
				ProjectUtli.showToast(context, result.toString(), ProjectUtli.MESSAGE_LENGTH_SHORT);
				textView_ResponseFromServer.setText(result.toString());
			}
			else
			{
				ProjectUtli.showToast(context, "An error occured please try again later!", ProjectUtli.MESSAGE_LENGTH_SHORT);

			}
		}

		public UserProfile sendLoginData(String para_username, String para_Password)
		{
			SoapObject request = new SoapObject(LinksUtils.NAMESPACE, LinksUtils.METHOD_NAME_LOGIN);

			PropertyInfo pi = new PropertyInfo();
			pi.namespace = LinksUtils.NAMESPACE;
			pi.setName("para_userName");
			pi.setValue(para_username);
			pi.setType(String.class);
			request.addProperty(pi);

			PropertyInfo pi2 = new PropertyInfo();
			pi2.namespace = LinksUtils.NAMESPACE;
			pi2.setName("para_password");
			pi2.setValue(para_Password);
			pi2.setType(String.class);
			request.addProperty(pi2);

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request);

			HttpTransportSE androidHttpTransport = new HttpTransportSE(LinksUtils.WEB_SERVICE_IIS_OFFICE_URL, LinksUtils.SERVER_RESPONSE_TIMEOUT);

			try
			{
				androidHttpTransport.call(LinksUtils.SOAP_ACTION_LOGIN, envelope);

				// SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

				SoapObject resultsRequestSOAP = (SoapObject) envelope.bodyIn;

				return parseObjectFromServer(resultsRequestSOAP);
				// String userID = resultsRequestSOAP.getProperty(0).toString();

			}
			catch (Exception e)
			{
				// Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
				return null;
			}

		}

		public UserProfile parseObjectFromServer(SoapObject resultsRequestSOAP)
		{
			String userResponse = resultsRequestSOAP.getProperty(0).toString();
			SoapObject userSoapResponseFromServer = (SoapObject) resultsRequestSOAP.getProperty(0);
			// int count = userSoapResponseFromServer.getPropertyCount(); For finding out the collection
			if (userResponse.contentEquals(""))
			{
				return null;
			}
			else
			{

				UserProfile userProfileObject = new UserProfile();
				userProfileObject.setUserIDFromServer(userSoapResponseFromServer.getProperty(0).toString());
				userProfileObject.setPassword(userSoapResponseFromServer.getProperty(1).toString());
				userProfileObject.setAddress(userSoapResponseFromServer.getProperty(2).toString());
				userProfileObject.setUserName(userSoapResponseFromServer.getProperty(3).toString());
				userProfileObject.setLastName(userSoapResponseFromServer.getProperty(4).toString());
				userProfileObject.setFirstName(userSoapResponseFromServer.getProperty(5).toString());

				// userProfileObject.setUserIDFromServer(userSoapResponseFromServer.getProperty(0).toString());
				// userProfileObject.save();
				return userProfileObject;

			}

		}
	}

}
