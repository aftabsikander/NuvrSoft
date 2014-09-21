package com.nvursoft.employeecontactsystem;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.nvursoft.employeecontactsystem.Utils.BundleConstant;
import com.nvursoft.employeecontactsystem.Utils.ProjectUtli;
import com.nvursoft.employeecontactsystem.models.Employee;

public class EmployeeDetailActivity extends ActionBarActivity
{
	TextView txt_empDetail;
	int empPos;
	Employee employeeObject;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employee_detail);
		setupReference();
		getBundles();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.employee_detail, menu);
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
		txt_empDetail = (TextView) findViewById(R.id.txt_EmployeeDetail);
	}

	public void getBundles()
	{
		Bundle bundle = getIntent().getExtras();
		if (bundle != null)
		{
			empPos = bundle.getInt(BundleConstant.BUNDLE_EMPLOYEE_COLLECTION_INDEX_KEY);
			txt_empDetail.setText(returnEmployeeLog(empPos));
		}
	}

	public String returnEmployeeLog(int para_pos)
	{
		employeeObject = ProjectUtli.employeeCollecion.get(para_pos);
		StringBuilder builder = new StringBuilder();
		builder.append(employeeObject.getName() + ",");
		builder.append(employeeObject.getPhone() + ",");
		builder.append(employeeObject.getGender() + ",");
		builder.append(employeeObject.getAddress() + ",");
		builder.append(employeeObject.getEmail());

		return builder.toString();

	}
}
