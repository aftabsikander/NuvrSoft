package com.nvursoft.employeecontactsystem.models;

public class Employee
{

	private String name, address, email, phone, gender;

	public Employee(String para_name, String para_address, String para_email, String para_phone, String para_gender)
	{
		this.name = para_name;
		this.address = para_address;
		this.email = para_email;
		this.phone = para_phone;
		this.gender = para_gender;

	}

	// #region Setter Methods
	public void setName(String name)
	{
		this.name = name;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	// #endregion

	// #region Getter Methods
	public String getName()
	{
		return name;
	}

	public String getAddress()
	{
		return address;
	}

	public String getEmail()
	{
		return email;
	}

	public String getPhone()
	{
		return phone;
	}

	public String getGender()
	{
		return gender;
	}

	// #endregion

}
