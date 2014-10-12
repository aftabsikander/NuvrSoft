package com.nuvrsoft.viewpager.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.nuvrsoft.viewpager.R;

public class NamazCalanderAdapter extends PagerAdapter
{

	public static final int [] new_NamazCalendarImages =
	{ R.drawable.new_jan, R.drawable.new_feb, R.drawable.new_march, R.drawable.new_april, R.drawable.new_may, R.drawable.new_june, R.drawable.new_july, R.drawable.new_august, R.drawable.new_sept, R.drawable.new_october, R.drawable.new_november, R.drawable.new_december };

	@Override
	public int getCount()
	{
		return new_NamazCalendarImages.length;
	}

	@Override
	public View instantiateItem(ViewGroup container, int position)
	{
		ImageView photoView = new ImageView(container.getContext());
		photoView.setImageResource(new_NamazCalendarImages[position]);

		// Now just add PhotoView to ViewPager and return it
		container.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

		return photoView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object)
	{
		container.removeView((View) object);
	}

	@Override
	public boolean isViewFromObject(View view, Object object)
	{
		return view == object;
	}

}
