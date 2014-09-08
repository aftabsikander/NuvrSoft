package com.nuvrsoft.viewssample;

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

public class SampleViewActivity extends ActionBarActivity
{

	EditText editText_name, edit_phone;
	Button buttonSubmit;

	String TAG = SampleViewActivity.class.getName();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sample_view);

		editText_name = (EditText) findViewById(R.id.editTextName);
		edit_phone = (EditText) findViewById(R.id.editTextPhone);
		buttonSubmit = (Button) findViewById(R.id.button1);

		buttonSubmit.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				Log.i(TAG, "Called From OnClickListener");
				String name = editText_name.getText().toString();
				String phone = edit_phone.getText().toString();

				Toast.makeText(v.getContext(), name + "," + phone, Toast.LENGTH_LONG).show();
				Log.i(TAG, name + "," + phone);

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sample_view, menu);
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

	public void getValuesFromUserInput(View v)
	{
		Log.i(TAG, "Called From xml Method");
		String name = editText_name.getText().toString();
		String phone = edit_phone.getText().toString();

		Toast.makeText(v.getContext(), name + "," + phone, Toast.LENGTH_LONG).show();
		Log.i(TAG, name + "," + phone);
	}

}
