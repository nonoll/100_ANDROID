package kr.co.usefl.ntest5;

import kr.co.usefl.display.SplashScreen;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SplashTest extends Activity {
	private SplashScreen splash = new SplashScreen(); 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_test);
		splash.onCurrentScreen();
		//splash.onNextScreen(this, MainActivity.class);
		splash.onNextScreen(this, MainActivity.class, R.anim.fade_in, R.anim.fade_out);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash_test, menu);
		return true;
	}

}
