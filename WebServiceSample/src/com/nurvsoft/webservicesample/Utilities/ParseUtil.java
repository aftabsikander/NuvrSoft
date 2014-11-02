package com.nurvsoft.webservicesample.Utilities;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nurvsoft.webservicesample.models.User;

public class ParseUtil
{
	public static ObjectMapper mapper = new ObjectMapper();

	public static ArrayList<User> parseNamazFromJsonManually(String para_jsonStream)
	{
		JsonFactory jfactory = new JsonFactory();
		User userObject = new User();
		ArrayList<User> userCollection = new ArrayList<User>();
		JsonParser jParser = null;

		try
		{
			jParser = jfactory.createJsonParser(para_jsonStream);

		}
		catch (JsonParseException e)
		{

			e.printStackTrace();
		}
		catch (IOException e)
		{

			e.printStackTrace();
		}

		try
		{

			while (jParser.nextToken() != JsonToken.END_OBJECT)
			{
				String fieldname = jParser.getCurrentName();
				if ("id".equals(fieldname))
				{
					userObject.setUserID(jParser.getValueAsString());

				}

				if ("name".equals(fieldname))
				{
					userObject.setName(jParser.getValueAsString());
					// jParser.nextToken();
				}
				if ("email".equals(fieldname))
				{
					userObject.setEmailAddress(jParser.getValueAsString());
					// jParser.nextToken();
				}
				if ("password".equals(fieldname))
				{
					userObject.setPassword(jParser.getValueAsString());
					// jParser.nextToken();
				}
				// userCollection.add(userObject);
				jParser.nextToken();

			}
			jParser.close();
			return userCollection;
		}

		catch (JsonParseException e)
		{

			e.printStackTrace();
			return null;
		}
		catch (IOException e)
		{

			e.printStackTrace();
			return null;
		}

	}

	public static User parseUserObjectJson(String Json)
	{
		User userObjectFromServer = null;
		try
		{
			userObjectFromServer = mapper.readValue(Json, mapper.getTypeFactory().constructType(User.class));

		}
		catch (JsonParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (JsonMappingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userObjectFromServer;
	}

	public static ArrayList<User> parseUserCollection(String Json)
	{
		ArrayList<User> userCollection = null;
		try
		{
			userCollection = mapper.readValue(Json, mapper.getTypeFactory().constructCollectionType(ArrayList.class, User.class));

		}
		catch (JsonParseException e)
		{
			e.printStackTrace();
		}
		catch (JsonMappingException e)
		{

			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return userCollection;
	}

}
