package com.example.countdownsample;

import android.R.bool;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

public class CountDownService extends Service
{
	private static final String TAG = "CountDownService";
	private static final int NOTIFICATION_ID = 1;

	public long startTime = 50000;
	public long interval = 1000;

	// Timer to update the ongoing notification
	private final long mFrequency = 100; // milliseconds
	private final int TICK_WHAT = 2;
	private Handler mHandler = new Handler()
	{
		public void handleMessage(Message m)
		{
			updateNotification();
			sendMessageDelayed(Message.obtain(this, TICK_WHAT), mFrequency);
		}
	};

	public class LocalBinder extends Binder
	{
		CountDownService getService()
		{
			return CountDownService.this;
		}
	}

	private ParkingCountDownTimer m_CountDownTimer;
	private LocalBinder m_binder = new LocalBinder();
	private NotificationManager m_notificationMgr;
	private Notification m_notification;

	@Override
	public IBinder onBind(Intent intent)
	{
		Log.d(TAG, "bound");

		return m_binder;
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
		Log.d(TAG, "created");

		m_CountDownTimer = new ParkingCountDownTimer(startTime, interval);
		m_notificationMgr = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		createNotification();

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		Bundle bundle = intent.getExtras();
		if (bundle != null)
		{
			Log.i(TAG, "Bundle Received");
			startTime = bundle.getLong("startingTime");
			interval = bundle.getLong("intervalTime");
			m_CountDownTimer = new ParkingCountDownTimer(startTime, interval);
		}

		Log.i(TAG, "Received start id " + startId + ": " + intent);
		// We want this service to continue running until it is explicitly
		// stopped, so return sticky.
		return START_STICKY;
	}

	public void createNotification()
	{
		Log.d(TAG, "creating notification");

		int icon = R.drawable.ic_launcher;
		CharSequence tickerText = "Parking Meter Time";
		long when = System.currentTimeMillis();

		m_notification = new Notification(icon, tickerText, when);
		m_notification.flags |= Notification.FLAG_ONGOING_EVENT;
		m_notification.flags |= Notification.FLAG_NO_CLEAR;
	}

	public void updateNotification()
	{
		Log.d(TAG, "updating notification");

		Context context = getApplicationContext();
		CharSequence contentTitle = "Parking Meter Time";
		CharSequence contentText = getFormatedElapsedTime();

		Intent notificationIntent = new Intent(this, MainActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

		// the next two lines initialize the Notification, using the configurations above
		m_notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
		m_notificationMgr.notify(NOTIFICATION_ID, m_notification);
	}

	public void showNotification()
	{
		Log.d(TAG, "showing notification");

		updateNotification();
		mHandler.sendMessageDelayed(Message.obtain(mHandler, TICK_WHAT), mFrequency);

	}

	public void hideNotification()
	{
		Log.d(TAG, "removing notification");

		m_notificationMgr.cancel(NOTIFICATION_ID);

	}

	public void start()
	{
		Log.d(TAG, "start");
		m_CountDownTimer.start();

		showNotification();
	}

	public void stop()
	{
		Log.d(TAG, "pause");
		m_CountDownTimer.cancel();

		hideNotification();
	}

	public long getCurrentElapsedTime()
	{
		return m_CountDownTimer.getCurrentTimeElapsed();
	}

	public String getFormatedElapsedTime()
	{
		return m_CountDownTimer.getFormatedElapsedTime();
	}

	public Boolean isCounterTimeFinished()
	{
		return m_CountDownTimer.isCounterTimerReachedFinished();
	}

	// CountDownTimer class
	public class ParkingCountDownTimer extends CountDownTimer
	{

		private long para_startTime, para_interval, currentElapsedTime;
		private Boolean finished = false;

		public ParkingCountDownTimer(long startTime, long interval)
		{
			super(startTime, interval);
			para_startTime = startTime;
			para_interval = interval;
		}

		@Override
		public void onFinish()
		{
			finished = true;
			// text.setText("Time's up!");
			// timeElapsedView.setText("Time Elapsed: " + formatElapsedTime(startTime));
		}

		@Override
		public void onTick(long millisUntilFinished)
		{
			// text.setText("Time remain:" + formatElapsedTime(millisUntilFinished));
			finished = false;
			currentElapsedTime = para_startTime - millisUntilFinished;

			Log.d("ReverseCurrenTime", String.valueOf(formatElapsedTime(millisUntilFinished)));

			// timeElapsedView.setText("Time Elapsed: " + formatElapsedTime(timeElapsed));
		}

		public long getCurrentTimeElapsed()
		{
			return currentElapsedTime;
		}

		public String getFormatedElapsedTime()
		{
			return formatElapsedTime(getCurrentTimeElapsed());
		}

		public Boolean isCounterTimerReachedFinished()
		{
			return finished;
		}

	}

	/***
	 * Given the time elapsed in tenths of seconds, returns the string representation of that time.
	 * 
	 * @param now
	 *             , the current time in tenths of seconds
	 * @return String with the current time in the format MM:SS.T or HH:MM:SS.T, depending on elapsed time.
	 */
	public String formatElapsedTime(long now)
	{
		long hours = 0, minutes = 0, seconds = 0, tenths = 0;
		StringBuilder sb = new StringBuilder();

		if (now < 1000)
		{
			tenths = now / 100;
		}
		else if (now < 60000)
		{
			seconds = now / 1000;
			now -= seconds * 1000;
			tenths = (now / 100);
		}
		else if (now < 3600000)
		{
			hours = now / 3600000;// 3600000 = 1 hour
			now -= hours * 3600000;
			minutes = now / 60000; // 60000 = 1 minute
			now -= minutes * 60000;
			seconds = now / 1000; // 1000 = 1 sec
			now -= seconds * 1000;
			tenths = (now / 100);
		}
		else if (now > 3600000)
		{
			hours = now / 3600000;// 3600000 = 1 hour
			now -= hours * 3600000;
			minutes = now / 60000; // 60000 = 1 minute
			now -= minutes * 60000;
			seconds = now / 1000; // 1000 = 1 sec
			now -= seconds * 1000;
			tenths = (now / 100);
		}

		if (hours > 0)
		{
			// sb.append(hours).append(":").append(formatDigits(minutes)).append(":").append(formatDigits(seconds)).append(".").append(tenths);
			sb.append(hours).append(":").append(formatDigits(minutes)).append(":").append(formatDigits(seconds));
		}
		else
		{
			// sb.append(formatDigits(minutes)).append(":").append(formatDigits(seconds)).append(".").append(tenths);
			sb.append(formatDigits(minutes)).append(":").append(formatDigits(seconds));
		}

		return sb.toString();
	}

	private String formatDigits(long num)
	{
		return (num < 10) ? "0" + num : new Long(num).toString();
	}

}
