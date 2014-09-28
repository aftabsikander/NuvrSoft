package com.aiman.agecalculator;

import java.util.Calendar;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import com.aiman.agecalculator.database.AgeCalculatorDatabaseHelper;
import com.aiman.agecalculator.model.Age;
import com.aiman.agecalculator.util.ProjectUtli;

public class MainActivity extends ActionBarActivity
{

	DatePicker datePicker_dob;
	Button btn_calculate;
	int datePicker_date, datePicker_month, datePicker_year, currentAge;
	Context mContext;
	String totalCurrentAgeInString;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;
		setupReference();

		setupDatabaseObjectInstance();
		btn_calculate.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// This is where we calculate the User DOB

				getDataFromDatePicker();
			}
		});
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

	@SuppressLint("NewApi")
	public void setupReference()
	{
		datePicker_dob = (DatePicker) findViewById(R.id.datePicker_dob);
		datePicker_dob.setCalendarViewShown(false);
		btn_calculate = (Button) findViewById(R.id.button_calculate_age);
	}

	public void setupDatabaseObjectInstance()
	{
		ProjectUtli.ageCalculatorDatabaseHelper = new AgeCalculatorDatabaseHelper(mContext);
	}

	public void getDataFromDatePicker()
	{
		datePicker_date = datePicker_dob.getDayOfMonth();// Day
		datePicker_year = datePicker_dob.getYear();// Year

		// Month (Note Month starts from Zero Index so when showing it to user ALWAYS +1 to month
		// I.E May is "5" but Android will return May as "4" DUE TO ZERO INDEX BASED ARRAY
		datePicker_month = datePicker_dob.getMonth();

		ProjectUtli.showToast(mContext, calculateCurrentAge(datePicker_date, datePicker_month, datePicker_year), ProjectUtli.MESSAGE_LENGTH_LONG);

		addAgeIntoTheDatabase();
		ProjectUtli.showToast(mContext, "Age record is saved in Database", ProjectUtli.MESSAGE_LENGTH_LONG);

	}

	public String calculateCurrentAge(int para_userSelectedDay, int para_userSelectedMonth, int para_userSelectedYear)
	{
		// This gets the current Data Instance from the Device
		final Calendar currentDateFromDevice = Calendar.getInstance();

		int currentYear = currentDateFromDevice.get(Calendar.YEAR);
		int currentMonth = currentDateFromDevice.get(Calendar.MONTH);
		int currentDay = currentDateFromDevice.get(Calendar.DAY_OF_MONTH);

		int calcuatedDay = para_userSelectedDay - currentDay;
		int calculatedMonth = para_userSelectedMonth = currentMonth;
		int calulatedYear = para_userSelectedYear - currentYear;

		currentAge = calulatedYear;

		// We are using Math.abs function to convert (Negative Values to positive Values)
		StringBuilder builder = new StringBuilder();
		builder.append("Year: " + Math.abs(calulatedYear));
		builder.append("-");
		builder.append(" Month: " + Math.abs(calculatedMonth + 1));
		builder.append("-");
		builder.append(" Days: " + Math.abs(calcuatedDay));
		builder.append(" ");

		totalCurrentAgeInString = Math.abs(calulatedYear) + "-" + Math.abs(calculatedMonth + 1) + "-" + Math.abs(calcuatedDay);

		return builder.toString();
	}

	public void addAgeIntoTheDatabase()
	{
		Age ageObject = new Age();
		ageObject.setDate(String.valueOf(datePicker_date));
		ageObject.setMonth(String.valueOf(datePicker_month));
		ageObject.setYear(String.valueOf(datePicker_year));

		// If you want to save only year then uncomment this line.
		// ageObject.setCurrentAge(Math.abs(currentAge));

		// This will save the whole year-month-date difference into the database
		ageObject.setCurrentAge(totalCurrentAgeInString);

		ProjectUtli.showToast(mContext, String.valueOf("Record ID: " + ProjectUtli.ageCalculatorDatabaseHelper.insertAge(ageObject)), ProjectUtli.MESSAGE_LENGTH_LONG);

	}
}
