package kr.co.usefl.nhybridapp;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

public class MainSplashScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_splash_screen);
		
		onCheckApkVersion();
		onSplashScreen();
	}
	
	private void onCheckApkVersion() {
		String version = null;
		
		try {
			PackageInfo pkgInfo = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0);
			version = pkgInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		
		AsyncTask<Void, Integer, String> test = new VersionCheckTask().execute();
		try {
			if( !test.get().equals(version) )
			{
					alert(Config.APP_VERSION_MSG_1, new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Config.APP_STROE_URL));
						startActivity(webIntent);
					}
				});
			}else{
				Toast.makeText(getApplicationContext(), Config.APP_VERSION_MSG_2, Toast.LENGTH_LONG).show();
				onSplashScreen2();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	private void alert(String message, OnClickListener listener) {
		new AlertDialog.Builder(this)
			.setIcon(android.R.drawable.ic_dialog_alert)
			.setTitle(R.string.app_name)
			.setMessage(message)
			.setPositiveButton(android.R.string.ok, listener)
			.create().show();
	}
	
	class VersionCheckTask extends AsyncTask<Void, Integer, String>
	{
		protected void onPostExecute(Long result) {
		}
		protected void onProgressUpdate(Integer... progress) {
		}
		@Override
		protected String doInBackground(Void... params) {
			URL url = null;
			String out = "";
			try {
				url = new URL(Config.APP_VERSION_URL);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			
			HttpURLConnection con = null;
			try {
				con = (HttpURLConnection) url.openConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}
			con.setDoOutput(true);
			
			try {
				InputStream ips = (InputStream) con.getInputStream();
				InputStreamReader ipr = new InputStreamReader(ips);
				int data = ipr.read();
				
				while (data != -1) {
					char current = (char) data;
					data = ipr.read();
					out += String.valueOf(current);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return out;
		}
	}

	public void onSplashScreen(){
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
			}
		}, 2*1000);
	}
	
	public void onSplashScreen2(){
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(intent);
				finish();
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			}
		}, 2*1000);
	}
	
	/*
	public void onSplashScreen(){
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(intent);
				finish();
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			}
		}, 2*1000);
		
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
			}
		}, 2*1000);
	}
	*/
}
