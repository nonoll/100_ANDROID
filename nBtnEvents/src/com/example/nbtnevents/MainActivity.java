package com.example.nbtnevents;

import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.btn_1).setOnClickListener(mClickListener);
		findViewById(R.id.btn_2).setOnClickListener(mClickListener);
		findViewById(R.id.btn_3).setOnClickListener(mClickListener);
		findViewById(R.id.btn_4).setOnClickListener(mClickListener);
	}
	
	Button.OnClickListener mClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Log.v("btn", "click");
			Integer id = v.getId();
			switch (id) {
				case R.id.btn_1:
					Intent callIntent = new Intent(Intent.ACTION_CALL);
					callIntent.setData(Uri.parse("tel:01094667686"));
					startActivity(callIntent);
					break;
				case R.id.btn_2:
					Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
					//long[] pattern = {1000,200,1000,2000,1200};
					//vb.vibrate(pattern, 1);
					vb.vibrate(3000);
					break;
				case R.id.btn_3:
					Toast.makeText(getApplicationContext(), "토스트 메시지출력", Toast.LENGTH_LONG).show();
					break;
				case R.id.btn_4:
					//Notification ntf = new Notification(android.R.drawable.ic_dialog_alert, "노티피 테스트", System.currentTimeMillis());
					Notification ntf = new Notification(R.drawable.ic_launcher, "노티피 테스트", System.currentTimeMillis());
					
					//ntf.vibrate = new long[]{3000,3000,3000};
					//ntf.flags = Notification.FLAG_AUTO_CANCEL;
					//ntf.ledARGB = Color.RED;
					/*
					ntf.vibrate = new long[] {100,3000};
					ntf.flags = Notification.FLAG_AUTO_CANCEL;
					ntf.ledARGB = Color.GREEN;
					ntf.ledOffMS = 1000;
					ntf.ledOnMS = 1000;
					
					ntf.sound = Uri.parse("file:/system/media/audio/ringtones/sample.ogg");
					*/
					
					ntf.defaults = Notification.DEFAULT_ALL;
					ntf.flags = Notification.FLAG_AUTO_CANCEL;
					
					PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), 0, new Intent(getApplicationContext(), MainActivity.class),0);
					ntf.setLatestEventInfo(getApplicationContext(), "노티피 테스트트", "내용", pIntent);
					
					NotificationManager ntm = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
					ntm.notify(1, ntf);
					break;
				default:
					break;
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
