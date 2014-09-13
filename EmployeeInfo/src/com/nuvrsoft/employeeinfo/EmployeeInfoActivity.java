package com.nuvrsoft.employeeinfo;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class EmployeeInfoActivity extends ActionBarActivity
{

	String emp_name, emp_email, emp_phone, emp_address, emp_gender, logValue;
	EditText edit_empName, edit_empEmail, edit_empPhone, edit_emp_address;
	RadioGroup radioGroupGender;
	RadioButton radioSelectedGender;
	Button btn_registerEmployee;
	int selectedGenderRadioId;
	Context mContext;

	String TAG = EmployeeInfoActivity.class.getName();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employee_info);
		mContext = this;

		setupReference();
		getPreferenceAndSetValues();

		btn_registerEmployee.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				getInputsFromWidgets(v.getContext());
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.employee_info, menu);
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
		edit_empName = (EditText) findViewById(R.id.editText_EmpName);
		edit_empEmail = (EditText) findViewById(R.id.editText_EmpEmail);
		edit_empPhone = (EditText) findViewById(R.id.editText_EmpNumber);
		edit_emp_address = (EditText) findViewById(R.id.editText_EmpAddress);
		radioGroupGender = (RadioGroup) findViewById(R.id.radioGroup_Gender);
		btn_registerEmployee = (Button) findViewById(R.id.btn_submit);

	}

	public void getInputsFromWidgets(Context context)
	{
		emp_name = edit_empName.getText().toString();
		emp_email = edit_empEmail.getText().toString();
		emp_phone = edit_empPhone.getText().toString();
		emp_address = edit_emp_address.getText().toString();
		getGenderForEmployeeFromWidgets();

		saveSharedPreference();

		logValue = returnEmployeeLog();

		// Toast.makeText(context, logValue, Toast.LENGTH_SHORT).show();
		sendIntentData(context);
		Log.i(TAG, logValue);

	}

	public void getGenderForEmployeeFromWidgets()
	{
		selectedGenderRadioId = radioGroupGender.getCheckedRadioButtonId();
		radioSelectedGender = (RadioButton) findViewById(selectedGenderRadioId);
		emp_gender = radioSelectedGender.getText().toString();
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

	public void sendIntentData(Context context)
	{
		Bundle bundle = new Bundle();
		bundle.putString(Pref_Keys.BUNDLE_NAME_TAG, emp_name);
		bundle.putString(Pref_Keys.BUNDLE_EMAIL_TAG, emp_email);
		bundle.putString(Pref_Keys.BUNDLE_PHONE_TAG, emp_phone);
		bundle.putString(Pref_Keys.BUNDLE_ADDRESS_TAG, emp_address);
		bundle.putString(Pref_Keys.BUNDLE_GENDER_TAG, emp_gender);

		Intent intent = new Intent();
		intent.setClass(context, EmployeeHomeActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	public void getPreferenceAndSetValues()
	{
		emp_name = SharedPrefUtil.getEmployeeName(mContext);
		emp_email = SharedPrefUtil.getEmployeeEmail(mContext);
		emp_phone = SharedPrefUtil.getEmployeePhone(mContext);
		emp_address = SharedPrefUtil.getEmployeeAddress(mContext);
		emp_gender = SharedPrefUtil.getEmployeeGender(mContext);

		if (emp_name != null)
			edit_empName.setText(emp_name);
		if (emp_email != null)
			edit_empEmail.setText(emp_email);
		if (emp_phone != null)
			edit_empPhone.setText(emp_phone);
		if (emp_address != null)
			edit_emp_address.setText(emp_address);

		setupValuesForGenderRadio();
		Log.i(TAG, "Shared Preference Retrieved");
	}

	public void setupValuesForGenderRadio()
	{
		int count = radioGroupGender.getChildCount();
		for (int i = 0; i < count; i++)
		{
			View view = radioGroupGender.getChildAt(i);
			if (view instanceof RadioButton)
			{

				RadioButton radioBtn = (RadioButton) view;
				if (emp_gender != null)
				{
					if (emp_gender.contentEquals("Male") && radioBtn.getText().toString().contentEquals("Male"))
					{
						radioBtn.setChecked(true);
					}
					else if (emp_gender.contentEquals("Female") && radioBtn.getText().toString().contentEquals("Female"))
					{
						radioBtn.setChecked(true);
					}

				}

			}
		}
	}

	public void saveSharedPreference()
	{
		SharedPrefUtil.setPrefEmployeeName(mContext, emp_name);
		SharedPrefUtil.setPrefEmployeeEmail(mContext, emp_email);
		SharedPrefUtil.setPrefEmployeePhone(mContext, emp_phone);
		SharedPrefUtil.setPrefEmployeeAddress(mContext, emp_address);
		SharedPrefUtil.setPrefEmployeeGender(mContext, emp_gender);
		Log.i(TAG, "Shared Preference SAVED");
	}
}
