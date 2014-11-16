package com.nuvrsoft.soapwebservicsample.model;

public class UserProfile
{

	private String userIDFromServer;

	private String userName;

	private String password;

	private String firstName;

	private String lastName;
	private String address;

	public UserProfile()
	{
		super();
	}

	public UserProfile(String para_userIDFromServer, String para_username, String para_Password, String para_lastName, String para_firstName, String para_address)
	{
		super();
		this.userIDFromServer = para_userIDFromServer;
		this.userName = para_username;
		this.password = para_Password;
		this.lastName = para_lastName;
		this.firstName = para_firstName;
		this.address = para_address;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
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

	public String getUserIDFromServer()
	{
		return userIDFromServer;
	}

	public void setUserIDFromServer(String userIDFromServer)
	{
		this.userIDFromServer = userIDFromServer;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("UserName:" + getUserName() + "\n");
		builder.append("Password:" + getPassword() + "\n");
		builder.append("First Name:" + getFirstName() + "\n");
		builder.append("Last Name:" + getLastName() + "\n");
		builder.append("Address:" + getAddress() + "\n");
		builder.append("ID:" + getUserIDFromServer() + "\n");
		return builder.toString();

	}
}
