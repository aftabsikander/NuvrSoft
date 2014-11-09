package com.example.videosample;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.videosample.utilities.ProjectUtli;

public class MainActivity extends ActionBarActivity
{
	VideoView videoView;
	VideoDownloaderAsyncTask videoDownloaderAsyncTask;
	Context mContext;
	public String APPLICATION_FOLDER_TAG = "sample";
	public String VIDEO_FOLDER_TAG = "VIDEOS";
	MediaController mediaController;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;
		createFolderForApplication();
		ProjectUtli.setupLocalPathForVideoDirectory(mContext);

		setupReference();
		setupVideoPlayerForAssetSource(ProjectUtli.localPathDirectory + R.raw.sample);
		// setupVideoPlayerAndDataSource("http://www.fieldandrurallife.tv/videos/Benltey%20Mulsanne.mp4");

		// URI either from net
		// http://ksars.org/videos/URS_1996.mp4
		// http://www.fieldandrurallife.tv/videos/Benltey%20Mulsanne.mp4

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void setupReference()
	{
		videoView = (VideoView) findViewById(R.id.videoViewa);
		mediaController = new MediaController(mContext);
		mediaController.setAnchorView(videoView);
		mediaController.setMediaPlayer(videoView);
	}

	public void setupVideoPlayerForAssetSource(String para_localfilePath)
	{
		Uri uri = Uri.parse(para_localfilePath);
		videoView.setMediaController(mediaController);
		// videoView.setVideoPath(para_localfilePath);
		videoView.setVideoURI(uri);
		videoView.start();
	}

	public void setupVideoPlayerAndDataSource(String para_url)
	{
		//
		Uri video = Uri.parse(para_url);
		videoView.setMediaController(mediaController);
		videoView.setVideoURI(video);
		videoView.start();
	}

	public void startAsycTaskForVideoDownload()
	{

		videoDownloaderAsyncTask = new VideoDownloaderAsyncTask();
		videoDownloaderAsyncTask.mContext = mContext;
		videoDownloaderAsyncTask.videoURL = "http://www.fieldandrurallife.tv/videos/Benltey%20Mulsanne.mp4";

		File file = new File(Environment.getExternalStorageDirectory() + "/" + APPLICATION_FOLDER_TAG + "/" + VIDEO_FOLDER_TAG);
		File downloadFileDirectory = createFileInsideTheFolder(file, "Benltey Mulsanne" + ".mp4");

		videoDownloaderAsyncTask.downloadFileDirectory = downloadFileDirectory;

		videoDownloaderAsyncTask.execute();

	}

	public void createFolderForApplication()
	{
		String extStorageDirectory = Environment.getExternalStorageDirectory().toString();

		File applicationfolder = new File(extStorageDirectory, APPLICATION_FOLDER_TAG);
		applicationfolder.mkdir();

		File videoFolder = new File(applicationfolder, VIDEO_FOLDER_TAG);
		videoFolder.mkdir();

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{

			case R.id.action_download:
			{
				startAsycTaskForVideoDownload();
				break;
			}
			case R.id.action_share:
			{
				Intent shareIntent = ProjectUtli.startShareIntent(mContext, "Hello World Application", "http://www.google.com");
				startActivity(shareIntent);
				break;
			}
		}
		return super.onOptionsItemSelected(item);
	}

	public File createFileInsideTheFolder(File directoryPath, String filename)
	{
		File file = new File(directoryPath, filename);
		try
		{
			file.createNewFile();
			return file;
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
			return file;
		}

	}

	public class VideoDownloaderAsyncTask extends AsyncTask<Void, Void, Boolean>
	{
		ProgressDialog PD;
		Context mContext;
		File downloadFileDirectory;
		String videoURL;

		@Override
		protected void onPreExecute()
		{
			PD = ProgressDialog.show(mContext, "Downloading Video", "Please Wait ...", true);
			PD.setCancelable(true);
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... params)
		{
			return DownloadFile(videoURL, downloadFileDirectory);
		}

		@Override
		protected void onPostExecute(Boolean result)
		{
			if (result)
			{
				ProjectUtli.showToast(mContext, "Video Downloaded", ProjectUtli.MESSAGE_LENGTH_LONG);
			}
			else
			{
				ProjectUtli.showToast(mContext, "Saving error occured", ProjectUtli.MESSAGE_LENGTH_LONG);
			}
			PD.dismiss();
			super.onPostExecute(result);
		}

		public Boolean DownloadFile(String fileURL, File directory)
		{
			try
			{

				FileOutputStream f = new FileOutputStream(directory);
				URL u = new URL(fileURL);
				HttpURLConnection c = (HttpURLConnection) u.openConnection();
				c.setRequestMethod("GET");
				c.setDoOutput(true);
				c.connect();

				InputStream in = c.getInputStream();

				byte [] buffer = new byte [1024];
				int len1 = 0;
				while ((len1 = in.read(buffer)) > 0)
				{
					f.write(buffer, 0, len1);
				}
				f.close();
				return true;
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return false;
			}

		}

	}

}
