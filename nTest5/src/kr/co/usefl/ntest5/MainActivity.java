package kr.co.usefl.ntest5;

import kr.co.usefl.net.AppVersion;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/*
		AppVersion app = new AppVersion(getApplicationContext());
		String current = app.getCurrentAppVersion();
		String server = app.getServerAppVersion("http://usefl.co.kr/app/appVersion.php");
		Log.d("AppVersion", "current : " + current + " , server : " + server);
		*/
		
		/*
		AppVersion app = new AppVersion();
		String server = app.getServerAppVersion("http://usefl.co.kr/app/appVersion.php");
		Log.d("AppVersion", "server : " + server);
		*/
		
		
		
		AppVersion app = new AppVersion(getApplicationContext(), "http://usefl.co.kr/app/appVersion.php");
		boolean isUpdated = app.isUpdatedVersion();
		if(isUpdated){
			Toast.makeText(getApplicationContext(), "최신버전입니다", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
