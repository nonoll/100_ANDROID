package kr.co.usefl.napkversion;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpConnection;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {
	//private static final URL APP_VERSION_URL = new URL("http://usefl.co.kr/app/appVersion.php");
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		String version = null;
		try {
			PackageInfo pkgInfo = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0);
			version = pkgInfo.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Log.v("btn", version);
		//HttpPostData();
		AsyncTask<Void, Integer, String> test = new MyDownloadTask().execute();
		try {
			Log.v("btn", "기초가 부족하니 고생이네 : " + test.get() );
			if( !test.get().equals(version) )
			{
				alert("업데이트가 필요합니다", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=air.co.kr.usefl.asamAlarm"));
						startActivity(webIntent);
					}
				});
			}else{
				Toast.makeText(getApplicationContext(), "최신버전입니다.", Toast.LENGTH_LONG).show();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
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
	
	public void HttpPostData(){ 
		URL url;
	    try {
	        url = new URL("http://usefl.co.kr/php/asamAlarm/user.php");

	        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

	        java.io.InputStream in = (java.io.InputStream) urlConnection.getInputStream();

	        InputStreamReader isw = new InputStreamReader(in);
	        /*
	        int data = isw.read();
	        while (data != -1) {
	            String current = (String) data;
	            data = isw.read();
	            Log.v("btn", current)
	        }*/
	        Log.v("btn", isw.toString() );
	    } catch (Exception e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}
	
	class MyDownloadTask extends AsyncTask<Void, Integer, String>
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
				url = new URL("http://usefl.co.kr/app/appVersion.php");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			HttpURLConnection con = null;
			try {
				con = (HttpURLConnection) url.openConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
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
				Log.v("btn", "이게 php에서 날리는 버전인가... : " + out);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//con.getInputStream()
			return out;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
