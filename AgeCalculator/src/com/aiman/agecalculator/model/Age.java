package com.aiman.agecalculator.model;

public class Age
{

	private int ID;
	private String day, month, year, currentAge;

	public Age()
	{

	}

	public Age(String para_date, String para_month, String para_year, String para_age)
	{
		this.day = para_date;
		this.month = para_date;
		this.year = para_year;
		this.currentAge = para_age;
	}

	// #region Getter Methods

	public int getID()
	{
		return ID;
	}

	public String getDate()
	{
		return day;
	}

	public String getMonth()
	{
		return month;
	}

	public String getYear()
	{
		return year;
	}

	public String getCurrentAge()
	{
		return currentAge;
	}

	// #endregion

	// #region Setter Methods

	public void setID(int iD)
	{
		ID = iD;
	}

	public void setDate(String date)
	{
		this.day = date;
	}

	public void setMonth(String month)
	{
		this.month = month;
	}

	public void setYear(String year)
	{
		this.year = year;
	}

	public void setCurrentAge(String age)
	{
		this.currentAge = age;
	}

	// #endregion

}
