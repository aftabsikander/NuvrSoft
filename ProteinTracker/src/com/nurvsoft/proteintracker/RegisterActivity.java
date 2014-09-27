package com.nurvsoft.proteintracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nurvsoft.proteintracker.Utils.SharedPrefUtil;
import com.nurvsoft.proteintracker.adapter.Key_holder;

public class RegisterActivity extends ActionBarActivity
{

	String UserName, UserEmail, UserPassword, UserPhone, UserAddress, bundle, TAG;
	EditText editName, editEmail, editPassowrd, editPhone, editAddress;
	Button reg_btn;
	Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		mContext = this;

		setRegreference();
		getShrPreferenceandSetValues();

		reg_btn.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{

				getRegisterValues(v.getContext());

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
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

	public void setRegreference()
	{

		editName = (EditText) findViewById(R.id.editR_Name);
		editEmail = (EditText) findViewById(R.id.editR_Email);
		editPassowrd = (EditText) findViewById(R.id.editRpassword);
		editPhone = (EditText) findViewById(R.id.editR_Phone);
		editAddress = (EditText) findViewById(R.id.editR_Address);
		reg_btn = (Button) findViewById(R.id.btn_submit);

	}

	public void getRegisterValues(Context mContext)
	{

		UserName = editName.getText().toString();
		UserEmail = editEmail.getText().toString();
		UserPassword = editPassowrd.getText().toString();
		UserPhone = editPhone.getText().toString();
		UserAddress = editAddress.getText().toString();

		SaveValuesinSharedPreference();

		// Toast.makeText(mContext, UserName+","+UserEmail+","+UserPassword+","+UserPhone+","+UserAddress,
		// Toast.LENGTH_SHORT).show();
		passValuethrowIntentinLoginActive(mContext);
	}

	public void passValuethrowIntentinLoginActive(Context context)
	{

		Bundle bundle = new Bundle();
		bundle.putString(Key_holder.BUNDLA_NAME_TAG, UserName);
		bundle.putString(Key_holder.BUNDLA_EMAIL_TAG, UserEmail);
		bundle.putString(Key_holder.BUNDLA_PASSWORD_TAG, UserPassword);
		bundle.putString(Key_holder.BUNDLA_PHONE_TAG, UserPhone);
		bundle.putString(Key_holder.BUNDLA_ADDRESS_TAG, UserAddress);

		Intent intent = new Intent();
		intent.setClass(context, LoginActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);

	}

	public void getShrPreferenceandSetValues()
	{

		UserName = SharedPrefUtil.getUserName(mContext);
		UserEmail = SharedPrefUtil.getUserEmail(mContext);
		UserPassword = SharedPrefUtil.getUserPassword(mContext);
		UserPhone = SharedPrefUtil.getUserPhone(mContext);
		UserAddress = SharedPrefUtil.getUserAddress(mContext);

		if (UserName != null)
			editName.setText(UserName);
		if (UserEmail != null)
			editEmail.setText(UserEmail);
		if (UserPassword != null)
			editPassowrd.setText(UserPassword);
		if (UserPhone != null)
			editPhone.setText(UserPhone);
		if (UserAddress != null)
			editAddress.setText(UserAddress);

		Toast.makeText(mContext, "Get Shared Preference Values", Toast.LENGTH_LONG).show();
		Log.i(TAG, "Get Shared Preference Values");

	}

	public void SaveValuesinSharedPreference()
	{

		SharedPrefUtil.setPrefUserName(mContext, UserName);
		SharedPrefUtil.setPrefUserEmail(mContext, UserEmail);
		SharedPrefUtil.setPrefUserPassword(mContext, UserPassword);
		SharedPrefUtil.setPrefUserPhone(mContext, UserPhone);
		SharedPrefUtil.setPrefUserAddress(mContext, UserAddress);

		Toast.makeText(mContext, "All Values save in Shared Preferences and im very happy!", Toast.LENGTH_LONG).show();
		Log.i(TAG, "All Values save in Shared Preferences and im very happy!");

	}

}
