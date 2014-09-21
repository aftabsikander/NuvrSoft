package com.nvursoft.employeecontactsystem;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.nvursoft.employeecontactsystem.Utils.ProjectUtli;
import com.nvursoft.employeecontactsystem.models.Employee;

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

		// TODO save object and add into our collection
		openDialogBoxScreenForSavingEmployee(mContext, "Save Employee", "No", "Yes");

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

	public void openDialogBoxScreenForSavingEmployee(final Context context, String alertTitle, String closeButtonTitle, String positiveButtonTitle)
	{
		LayoutInflater factory = LayoutInflater.from(context);
		View layout = factory.inflate(R.layout.dailog_complete, null);

		AlertDialog.Builder alert = new AlertDialog.Builder(context);
		alert.setIcon(android.R.drawable.ic_dialog_info);
		alert.setTitle(alertTitle);
		alert.setView(layout);

		// TextView aboutTextView = (TextView) layout.findViewById(R.id.textView_dialog);

		alert.setPositiveButton(positiveButtonTitle, new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int whichButton)
			{
				ProjectUtli.employeeCollecion.add(new Employee(emp_name, emp_address, emp_email, emp_phone, emp_gender));
				ProjectUtli.showToast(context, "Positive Button is Clicked", ProjectUtli.MESSAGE_LENGTH_SHORT);

				logValue = returnEmployeeLog();

				ProjectUtli.genericIntent(mContext, HomeActivity.class, null);

				Log.i(TAG, logValue);
			}
		});

		alert.setNegativeButton(closeButtonTitle, new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int whichButton)
			{
				ProjectUtli.showToast(context, "Negative Button is Clicked", ProjectUtli.MESSAGE_LENGTH_SHORT);
			}
		});

		alert.show();
	}

}
