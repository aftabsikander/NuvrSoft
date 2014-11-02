package com.nurvsoft.webservicesample.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User
{

	// For parsing XML Data we will use KSOAP2 Library

	@JsonProperty("id")
	private String id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("email")
	private String emailAddress;

	@JsonProperty("password")
	private String password;

	@JsonIgnoreProperties("CreatedDate")
	public User()
	{

	}

	public String getUserID()
	{
		return id;
	}

	public void setUserID(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getEmailAddress()
	{
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress)
	{
		this.emailAddress = emailAddress;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("UserID :" + getUserID() + "\n");
		builder.append("UserName :" + getName() + "\n");
		builder.append("EmailAddress :" + getEmailAddress() + "\n");
		builder.append("Password :" + getPassword());
		return builder.toString();

	}
}
