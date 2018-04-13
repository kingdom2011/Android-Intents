package com.next.androidintentlibrary;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.io.File;

/**
 * Created by masoud on 11/1/2017.
 */

public class MusicIntents
{
	private Context context;
	private Intent intent;

	MusicIntents(Context context)
	{
		this.context = context;
	}

	public static MusicIntents from(@NonNull Context context)
	{
		return new MusicIntents(context);
	}

	public MusicIntents openMusicIntent()
	{
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_APP_MUSIC);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		return this;
	}

	public MusicIntents openAudio(File file)
	{
		return openAudio(Uri.fromFile(file));
	}

	public MusicIntents openAudio(String file)
	{
		return openAudio(new File(file));
	}

	public MusicIntents openAudio(Uri uri)
	{
		return openMedia(uri, "audio/*");
	}

	private MusicIntents openMedia(Uri uri, String mimeType)
	{
		intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(uri, mimeType);
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
		Intent musicIntent = build();
		try
		{
			startActivity(musicIntent);
		} catch (ActivityNotFoundException e)
		{
			return false;
		}
		return true;
	}
}