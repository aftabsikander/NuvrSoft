package com.nuvrsoft.employeeinfo;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class EmployeeHomeActivity extends ActionBarActivity
{
	String emp_name, emp_email, emp_phone, emp_address, emp_gender;
	Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employee_home);
		mContext = this;
		getBundlesFromIntent();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.employee_home, menu);
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

	public void getBundlesFromIntent()
	{
		Bundle bundle = getIntent().getExtras();
		if (bundle != null)
		{
			emp_name = bundle.getString(Pref_Keys.BUNDLE_NAME_TAG);
			emp_email = bundle.getString(Pref_Keys.BUNDLE_EMAIL_TAG);
			emp_phone = bundle.getString(Pref_Keys.BUNDLE_PHONE_TAG);
			emp_address = bundle.getString(Pref_Keys.BUNDLE_ADDRESS_TAG);
			emp_gender = bundle.getString(Pref_Keys.BUNDLE_GENDER_TAG);
			Toast.makeText(mContext, returnEmployeeLog(), Toast.LENGTH_LONG).show();
		}
	}

	public String returnEmployeeLog()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(emp_name + ",");
		builder.append(emp_email + ",");
		builder.append(emp_phone + ",");
		builder.append(emp_address + ",");
		builder.append(emp_gender);

		return builder.toString();

	}
}
