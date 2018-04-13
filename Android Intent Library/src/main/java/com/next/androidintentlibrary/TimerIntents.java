package com.next.androidintentlibrary;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.AlarmClock;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

/**
 * Created by masoud on 11/1/2017.
 */

public class TimerIntents
{
	private Context context;
	private Intent intent;

	TimerIntents(Context context)
	{
		this.context = context;
	}

	public TimerIntents from(@NonNull Context context)
	{
		return new TimerIntents(context);
	}

	// NOTE: requires com.android.alarm.permission.SET_ALARM permission
	@RequiresApi(api = Build.VERSION_CODES.KITKAT)
	public TimerIntents createTimerIntent(String message, int length, boolean skipUi)
	{
		intent = new Intent(AlarmClock.ACTION_SET_TIMER);
		intent.putExtra(AlarmClock.EXTRA_MESSAGE, message);
		intent.putExtra(AlarmClock.EXTRA_LENGTH, length);
		intent.putExtra(AlarmClock.EXTRA_SKIP_UI, skipUi);
		return this;
	}

	public Intent build()
	{
		return intent;
	}

	private void startActivity(Intent intent)
	{
		if (!(context instanceof Activity))
		{
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		}
		context.startActivity(intent);
	}

	public boolean show()
	{
		Intent timerIntent = build();
		try
		{
			startActivity(timerIntent);
		} catch (ActivityNotFoundException e)
		{
			return false;
		}
		return true;
	}
}