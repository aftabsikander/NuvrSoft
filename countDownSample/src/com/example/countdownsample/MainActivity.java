package com.example.countdownsample;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements OnClickListener
{
	private static final String tag = "Main";

	private boolean timerHasStarted = false;
	private Button btn_start;
	private TextView text, timeElapsedView;

	// private final long startTime = 50000;
	private final long startTime = 9900000;// 2 hours and 45 mintues
	private final long interval = 1000;
	// Timer to update the elapsedTime display
	private final long mFrequency = 100; // milliseconds
	private final int TICK_WHAT = 2;

	// Connection to the backgorund StopwatchService
	private CountDownService m_ParkingMeterService;

	Intent serviceIntent;

	private Handler mHandler = new Handler()
	{
		public void handleMessage(Message m)
		{
			updateElapsedTime();
			sendMessageDelayed(Message.obtain(this, TICK_WHAT), mFrequency);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupReference();
		startServiceAndBinding();
		mHandler.sendMessageDelayed(Message.obtain(mHandler, TICK_WHAT), mFrequency);

	}

	public void setupReference()
	{
		btn_start = (Button) this.findViewById(R.id.button);
		text = (TextView) this.findViewById(R.id.timer);
		timeElapsedView = (TextView) this.findViewById(R.id.timeElapsed);
		btn_start.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		if (!timerHasStarted)
		{
			// countDownTimer.start();

			m_ParkingMeterService.start();
			text.setText("Starting Time: "+ m_ParkingMeterService.formatElapsedTime(startTime));
			timerHasStarted = true;
			btn_start.setText("RESET");
		}
		else
		{
			m_ParkingMeterService.stop();
			// countDownTimer.cancel();
			timerHasStarted = false;
			btn_start.setText("Start");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void startServiceAndBinding()
	{
		Bundle bundle = new Bundle();
		bundle.putLong("startingTime", startTime);
		bundle.putLong("intervalTime", interval);

		serviceIntent = new Intent(this, CountDownService.class);
		serviceIntent.putExtras(bundle);
		startService(serviceIntent);
		bindStopwatchService();
	}

	public void updateElapsedTime()
	{
		if (m_ParkingMeterService != null)
		{
			timeElapsedView.setText(m_ParkingMeterService.getFormatedElapsedTime());
			if (m_ParkingMeterService.isCounterTimeFinished())
			{
				text.setText("Time's up!");
			}

		}

	}

	private ServiceConnection m_ParkingMeterServiceConn = new ServiceConnection()
	{
		@Override
		public void onServiceConnected(ComponentName name, IBinder service)
		{
			m_ParkingMeterService = ((CountDownService.LocalBinder) service).getService();
			m_ParkingMeterService.startTime = startTime;
			m_ParkingMeterService.interval = interval;

		}

		@Override
		public void onServiceDisconnected(ComponentName name)
		{
			m_ParkingMeterService = null;
		}
	};

	private void bindStopwatchService()
	{
		bindService(new Intent(this, CountDownService.class), m_ParkingMeterServiceConn, Context.BIND_AUTO_CREATE);
	}

	private void unbindStopwatchService()
	{
		if (m_ParkingMeterService != null)
		{
			unbindService(m_ParkingMeterServiceConn);
		}
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		// unbindStopwatchService();
	}

}
