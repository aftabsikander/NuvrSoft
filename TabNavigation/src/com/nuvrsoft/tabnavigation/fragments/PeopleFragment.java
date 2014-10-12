package com.nuvrsoft.tabnavigation.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nuvrsoft.tabnavigation.R;

public class PeopleFragment extends BaseFragment
{

	View peopleFragmentView;
	Context mContex;
	ImageView peopleImg;

	@Override
	public int getTitleResourceId()
	{
		return R.string.app_name;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		setHasOptionsMenu(true);

		peopleFragmentView = inflater.inflate(R.layout.fragment_people, container, false);
		mContex = peopleFragmentView.getContext();
		setupReference();

		return peopleFragmentView;
	}

	public void setupReference()
	{
		peopleImg = (ImageView) peopleFragmentView.findViewById(R.id.imageView1);

	}
}
