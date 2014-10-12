package com.nuvrsoft.tabnavigation.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nuvrsoft.tabnavigation.R;

public class HomeFragment extends BaseFragment
{

	View homeFragmentView;
	Context mContext;
	Button btn_home;

	@Override
	public int getTitleResourceId()
	{
		return R.string.app_name;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		setHasOptionsMenu(true);

		homeFragmentView = inflater.inflate(R.layout.fragment_home, container, false);
		mContext = homeFragmentView.getContext();
		setupReference();

		return homeFragmentView;
	}

	public void setupReference()
	{

		btn_home = (Button) homeFragmentView.findViewById(R.id.btn_HomeFragment);
	}

}
