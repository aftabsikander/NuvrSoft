package com.nuvrsoft.tabnavigation.adapter;

import java.util.Locale;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nuvrsoft.tabnavigation.R;
import com.nuvrsoft.tabnavigation.fragments.CommunityFragment;
import com.nuvrsoft.tabnavigation.fragments.HomeFragment;
import com.nuvrsoft.tabnavigation.fragments.PeopleFragment;

public class FragmentAdapter extends FragmentPagerAdapter
{

	public Context mContext;

	public FragmentAdapter(FragmentManager fm)
	{
		super(fm);
	}

	@Override
	public Fragment getItem(int position)
	{

		Fragment fragment = null;
		switch (position)
		{
			case 0:
				fragment = Fragment.instantiate(mContext, HomeFragment.class.getName());
				break;
			case 1:
				fragment = Fragment.instantiate(mContext, PeopleFragment.class.getName());
				break;
			case 2:
				fragment = Fragment.instantiate(mContext, CommunityFragment.class.getName());
				break;
		}
		return fragment;

	}

	@Override
	public int getCount()
	{

		return 3;
	}

	@Override
	public CharSequence getPageTitle(int position)
	{
		Locale locale = Locale.getDefault();
		switch (position)
		{
			case 0:
				return mContext.getString(R.string.tab_Home_Section).toUpperCase(locale);
			case 1:
				return mContext.getString(R.string.tab_People_Section).toUpperCase(locale);
			case 2:
				return mContext.getString(R.string.tab_Community_Section).toUpperCase(locale);
		}
		return null;
	}

}
