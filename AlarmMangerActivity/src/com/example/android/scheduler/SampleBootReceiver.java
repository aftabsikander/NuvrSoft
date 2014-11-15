package com.example.android.scheduler;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * This BroadcastReceiver automatically (re)starts the alarm when the device is rebooted. This receiver is set to be
 * disabled (android:enabled="false") in the application's manifest file. When the user sets the alarm, the receiver is
 * enabled. When the user cancels the alarm, the receiver is disabled, so that rebooting the device will not trigger
 * this receiver.
 */
// BEGIN_INCLUDE(autostart)
public class SampleBootReceiver extends BroadcastReceiver
{
	SampleAlarmReceiver alarm = new SampleAlarmReceiver();

	@Override
	public void onReceive(Context context, Intent intent)
	{
		if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED"))
		{

			alarm.setAlarm(context);
			startApplicationNotification(context, "Application Started");
		}
	}

	// Post a notification indicating whether a doodle was found.
	public void startApplicationNotification(Context mContext, String msg)
	{
		NotificationManager mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

		PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0, new Intent(mContext, MainActivity.class), 0);

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext).setSmallIcon(R.drawable.ic_launcher).setContentTitle("Namaz Time Alert").setStyle(new NotificationCompat.BigTextStyle().bigText(msg)).setContentText(msg);

		mBuilder.setContentIntent(contentIntent);
		mNotificationManager.notify(1, mBuilder.build());
	}
}
// END_INCLUDE(autostart)
