package com.nuvrsoft.tabnavigation.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.nuvrsoft.tabnavigation.R;

public class CommunityFragment extends BaseFragment
{
	View communityFragmentView;
	Context mContext;
	TextView txt_communityText;
	Button btn_community;

	@Override
	public int getTitleResourceId()
	{
		return R.string.app_name;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		setHasOptionsMenu(true);

		communityFragmentView = inflater.inflate(R.layout.fragment_commnunity, container, false);
		mContext = communityFragmentView.getContext();
		setupReference();

		return communityFragmentView;
	}

	public void setupReference()
	{
		txt_communityText = (TextView) communityFragmentView.findViewById(R.id.textView1);
		btn_community = (Button) communityFragmentView.findViewById(R.id.button1);
	}

}
