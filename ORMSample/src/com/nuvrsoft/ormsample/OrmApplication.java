package com.nuvrsoft.ormsample;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

public class OrmApplication extends Application
{

	@Override
	public void onCreate()
	{
		super.onCreate();
		ActiveAndroid.initialize(this);
	}
}
