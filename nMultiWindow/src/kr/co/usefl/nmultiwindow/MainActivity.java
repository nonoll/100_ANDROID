package kr.co.usefl.nmultiwindow;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Toast.makeText(getApplicationContext(), "아이러브 삼국지 실행", Toast.LENGTH_LONG).show();
		Intent intent2 = getApplicationContext().getPackageManager().getLaunchIntentForPackage("com.uqee.lying.maingameHanguo");
		getApplicationContext().startActivity(intent2);
		
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
