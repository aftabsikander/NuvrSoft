package com.nuvrsoft.ormsample.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.nuvrsoft.ormsample.R;
import com.nuvrsoft.ormsample.model.Users;

public class UserFragment extends Fragment
{
	View userView;
	Context mContext;
	EditText editText_userFirstName, editText_userLastName, editText_userAge;
	Button btn_saveObject, btn_RetriveObject;
	String firstName, lastName, age;

	public UserFragment()
	{
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		userView = inflater.inflate(R.layout.fragment_main, container, false);
		mContext = userView.getContext();
		setupReference();
		btn_saveObject.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{

				getValuesFromWidgets();

			}
		});
		btn_RetriveObject.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				Users databaseObject = getObjectFromDataBaseByID(1);
				if (databaseObject != null)
				{
					editText_userFirstName.setText(databaseObject.getFirstName());
					editText_userLastName.setText(databaseObject.getLastName());
					editText_userAge.setText(databaseObject.getAge());

				}
				else
				{
					Toast.makeText(mContext, "Object not found", Toast.LENGTH_LONG).show();
				}

			}
		});

		return userView;
	}

	public void setupReference()
	{
		editText_userFirstName = (EditText) userView.findViewById(R.id.editText_firstName);
		editText_userLastName = (EditText) userView.findViewById(R.id.editText_LastName);
		editText_userAge = (EditText) userView.findViewById(R.id.editText_Age);

		btn_saveObject = (Button) userView.findViewById(R.id.button_save);
		btn_RetriveObject = (Button) userView.findViewById(R.id.button_retrive);
	}

	public void getValuesFromWidgets()
	{
		firstName = editText_userFirstName.getText().toString();
		lastName = editText_userLastName.getText().toString();
		age = editText_userAge.getText().toString();
		if (!(TextUtils.isEmpty(firstName)) || (!(TextUtils.isEmpty(lastName)) || (!(TextUtils.isEmpty(age)))))
		{
			// If values are not empty save into Database
			saveObjectIntoTable(firstName, lastName, age);
			clearAllWidgets();
		}
		else
		{
			Toast.makeText(mContext, "Please fill all values", Toast.LENGTH_LONG).show();
		}
	}

	public void saveObjectIntoTable(String para_firstName, String para_lastName, String para_Age)
	{
		Users users = new Users(para_firstName, para_lastName, para_Age);
		users.save();
		Toast.makeText(mContext, "User Saved ID is:" + String.valueOf(users.getId()), Toast.LENGTH_LONG).show();
	}

	public Users getObjectFromDataBaseByID(long userID)
	{

		return new Select().from(Users.class).where("id=?", userID).executeSingle();
	}

	public void clearAllWidgets()
	{

		editText_userFirstName.setText("");
		editText_userLastName.setText("");
		editText_userAge.setText("");
	}

	public ArrayList<Users> getWholeDataFromTable()
	{
		List<Users> dataCollectionFromDatabase = new Select().from(Users.class).execute();
		ArrayList<Users> userObjectsCollection = new ArrayList<Users>(dataCollectionFromDatabase);
		return userObjectsCollection;
	}
}
