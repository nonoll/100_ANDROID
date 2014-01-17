package air.co.kr.usefl.asamalarmex;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class NNotification {
	public NNotification(){
		
	}
	@SuppressWarnings("deprecation")
	public void onNotification(Context context, String title, String msg, int id){
		Notification ntf = new Notification(R.drawable.ic_launcher, title, System.currentTimeMillis());
		
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
		
		//Intent asamIntent = new Intent(context.getPackageManager().getLaunchIntentForPackage("air.co.kr.usefl.asamAlarm"));
		//PendingIntent pIntent = PendingIntent.getActivity(context.getApplicationContext(), 0, asamIntent, 0);
		PendingIntent pIntent = PendingIntent.getActivity(context.getApplicationContext(), 0, new Intent(context.getApplicationContext(), MainActivity.class),0);
		ntf.setLatestEventInfo(context.getApplicationContext(), title, msg, pIntent);
		
		NotificationManager ntm = (NotificationManager) context.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
		ntm.notify(id, ntf);
	}
}
