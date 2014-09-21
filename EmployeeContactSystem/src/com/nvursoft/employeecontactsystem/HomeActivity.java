package com.nvursoft.employeecontactsystem;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.nvursoft.employeecontactsystem.Utils.BundleConstant;
import com.nvursoft.employeecontactsystem.Utils.ProjectUtli;
import com.nvursoft.employeecontactsystem.adapter.EmployeeContactAdapter;

public class HomeActivity extends ActionBarActivity
{

	Context mContext;
	LinearLayout linearLayout_empty;
	ListView list_empListView;
	EmployeeContactAdapter employeeContactAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		mContext = this;
		setupReference();
		list_empListView.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Bundle bundle = new Bundle();
				bundle.putInt(BundleConstant.BUNDLE_EMPLOYEE_COLLECTION_INDEX_KEY, position);
				ProjectUtli.genericIntent(mContext, EmployeeDetailActivity.class, bundle);

			}
		});

		setupDataIntoEmployeeListView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_AddEmployee)
		{

			ProjectUtli.genericIntent(mContext, EmployeeInfoActivity.class, null);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void setupReference()
	{
		linearLayout_empty = (LinearLayout) findViewById(R.id.linearLayout_empty);
		list_empListView = (ListView) findViewById(R.id.employee_listview);
	}

	public void setupDataIntoEmployeeListView()
	{

		int count = ProjectUtli.employeeCollecion.size();
		if (count > 0)
		{
			employeeContactAdapter = new EmployeeContactAdapter(mContext, ProjectUtli.employeeCollecion);
			list_empListView.setAdapter(employeeContactAdapter);
			list_empListView.setFastScrollEnabled(true);
			showListViewLayout(true);
		}
		else
		{
			showListViewLayout(false);
		}
	}

	public void showListViewLayout(Boolean toShow)
	{
		if (toShow)
		{
			linearLayout_empty.setVisibility(View.INVISIBLE);
			list_empListView.setVisibility(View.VISIBLE);
		}
		else
		{
			linearLayout_empty.setVisibility(View.VISIBLE);
			list_empListView.setVisibility(View.INVISIBLE);
		}

	}
}
