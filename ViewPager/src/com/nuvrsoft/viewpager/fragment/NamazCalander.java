package com.nuvrsoft.viewpager.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.ViewGroup;

import com.nuvrsoft.viewpager.R;
import com.nuvrsoft.viewpager.adapter.NamazCalanderAdapter;
import com.nuvrsoft.viewpager.animations.ZoomOutPageTransformer;
import com.nuvrsoft.viewpager.base.BaseFragment;

public class NamazCalander extends BaseFragment
{
	private static final String ARG_SECTION_NUMBER = "section_number";

	private static final String ISLOCKED_ARG = "isLocked";
	private ViewPager mViewPager;
	private MenuItem menuLockItem;

	View namazCalanderView;
	Context mContext;

	@Override
	public int getTitleResourceId()
	{

		return R.string.app_name;
	}

	public static NamazCalander newInstance(int sectionNumber)
	{
		NamazCalander fragment = new NamazCalander();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public NamazCalander()
	{
	}

	public void gotoCurrentMonthCalander(int index)
	{
		mViewPager.setCurrentItem(index, true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		setHasOptionsMenu(true);

		namazCalanderView = inflater.inflate(R.layout.fragment_namazcalander, container, false);
		mContext = namazCalanderView.getContext();
		setupReference();

		mViewPager.setAdapter(new NamazCalanderAdapter());
		
		/*int count = mViewPager.getCurrentItem() + 1;
		mViewPager.setCurrentItem(count);*/
		
		// gotoCurrentMonthCalander(ProjectUtli.getCurrentIndexForMonth(mContext));

		return namazCalanderView;
	}

	public void setupReference()
	{
		mViewPager = (ViewPager) namazCalanderView.findViewById(R.id.namaz_calander_view_pager);
		mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		inflater.inflate(R.menu.viewpager_menu, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public void onPrepareOptionsMenu(Menu menu)
	{
		menuLockItem = menu.findItem(R.id.menu_lock);

		menuLockItem.setOnMenuItemClickListener(new OnMenuItemClickListener()
		{
			@Override
			public boolean onMenuItemClick(MenuItem item)
			{

				return true;
			}
		});

	}

	private boolean isViewPagerActive()
	{
		return(mViewPager != null && mViewPager instanceof ViewPager);
	}

}
