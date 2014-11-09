package com.nuvrsoft.ormsample.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "User")
public class Users extends Model
{

	@Column(name = "FirstName")
	private String firstName;
	@Column(name = "LastName")
	private String lastName;
	@Column(name = "Age")
	private String age;

	public Users()
	{
		super();
	}

	public Users(String para_firstName, String para_lastName, String para_Age)
	{
		this.firstName = para_firstName;
		this.lastName = para_lastName;
		this.age = para_Age;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getAge()
	{
		return age;
	}

	public void setAge(String age)
	{
		this.age = age;
	}
}
