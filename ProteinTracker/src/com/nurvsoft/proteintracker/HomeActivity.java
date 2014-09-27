package com.nurvsoft.proteintracker;

import com.nurvsoft.proteintracker.Utils.SharedPrefUtil;

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

public class HomeActivity extends ActionBarActivity
{

	EditText Edit_target;
	Button b_save;

	String TAG, User_Target;
	Intent intent;
	Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		mContext = this;

		setReference();
		getSharedPrefValToTarget();

		Log.i(TAG, "App Created");
		Toast.makeText(getApplicationContext(), SharedPrefUtil.get_Target_value(mContext), Toast.LENGTH_SHORT).show();

		b_save.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				getTargetValue();

			}
		});

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
		if (id == R.id.action_Addproteins)
		{

			Intent intent = new Intent(HomeActivity.this, ProteinListActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void setReference()
	{

		Edit_target = (EditText) findViewById(R.id.utarget);
		b_save = (Button) findViewById(R.id.save_btn);

	}

	public void getTargetValue()
	{

		User_Target = Edit_target.getText().toString();

		// Toast.makeText(getApplicationContext(), User_Target, Toast.LENGTH_SHORT).show();

		SaveTargetvalueInPref();

	}

	public void getSharedPrefValToTarget()
	{

		User_Target = SharedPrefUtil.get_Target_value(mContext);

		if (User_Target != null)
			Edit_target.setText(User_Target);

	}

	public void SaveTargetvalueInPref()
	{

		SharedPrefUtil.set_Target_value(mContext, User_Target);

		Intent intent = new Intent(HomeActivity.this, ProteinListActivity.class);
		startActivity(intent);

	}

	/*
	 * public void setlayoutandlistviewRef(){
	 * 
	 * emplty_Layout = (LinearLayout) findViewById(R.id.empt_screen); U_list_view = (ListView)
	 * findViewById(R.id.User_listView);
	 * 
	 * }
	 * 
	 * public void setDatetoView(){
	 * 
	 * 
	 * 
	 * 
	 * 
	 * }
	 */

	/*
	 * public void hidelayout(Boolean toshow){
	 * 
	 * if(toshow) { emplty_Layout.setVisibility(View.INVISIBLE); U_list_view.setVisibility(View.VISIBLE); }else {
	 * emplty_Layout.setVisibility(View.VISIBLE); U_list_view.setVisibility(View.INVISIBLE); }
	 * 
	 * 
	 * }
	 */

}
