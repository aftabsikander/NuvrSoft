package com.nvursoft.employeecontactsystem.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nvursoft.employeecontactsystem.R;
import com.nvursoft.employeecontactsystem.Utils.ProjectUtli;
import com.nvursoft.employeecontactsystem.models.Employee;

public class EmployeeContactAdapter extends BaseAdapter
{

	private final List<Employee> employeeContactCollection;
	private Context mContext;
	private final String TAG = EmployeeContactAdapter.class.getName();

	public EmployeeContactAdapter(final Context context, final ArrayList<Employee> para_employeeContactCollection)
	{
		this.employeeContactCollection = para_employeeContactCollection;
		this.mContext = context;
	}

	@Override
	public int getCount()
	{

		return employeeContactCollection.size();
	}

	@Override
	public Employee getItem(int position)
	{

		return employeeContactCollection.get(position);
	}

	@Override
	public long getItemId(int position)
	{

		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{

		ViewHolder holder;
		// Bitmap bmThumbnail;
		if (convertView == null)
		{
			Log.i(TAG, "View is empty");
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.employee_item_row, null);
			Log.i(TAG, "View is created");
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
			Log.i(TAG, "holder pattern is passed to View");
		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
			Log.i(TAG, "holder Pattern object is returned from view");
		}
		Employee employeeItem = getItem(position);

		if (employeeItem != null)
			holder.txt_EmployeeName.setText(employeeItem.getName());

		return convertView;

	}

	class ViewHolder
	{
		ImageView img_Employee;
		TextView txt_EmployeeName;
		Button btn_sample;

		public ViewHolder(View view)
		{
			img_Employee = (ImageView) view.findViewById(R.id.employeeImage);
			txt_EmployeeName = (TextView) view.findViewById(R.id.txt_employeeName);

		}
	}

}
