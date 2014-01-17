package kr.co.usefl.ntest3;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;

public class MainSplashScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_splash_screen);
		
		/*
		Thread background = new Thread(){
			public void run(){
				try{
					sleep(4*1000);
					
					Intent intent = new Intent(getApplicationContext(), MainActivity.class);
					startActivity(intent);
					finish();
					overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
				}catch(Exception e){
					Log.e("MainSplashScreen", "run Error");
				}
			}
		};
		
		background.start();
		*/
		
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
}
