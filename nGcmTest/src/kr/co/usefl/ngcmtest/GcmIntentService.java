package kr.co.usefl.ngcmtest;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class GcmIntentService extends IntentService {
	public static final String TAG = "GCM Demo";
	public static final int NOTIFICATION_ID = 1;
	private NotificationManager _ntm;
	
	public GcmIntentService() {
		super("GcmIntentService");
	}
	
	@Override
	protected void onHandleIntent(Intent intent) {
		Bundle extras = intent.getExtras();
		GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
		String messageType = gcm.getMessageType(intent);
		
		if(!extras.isEmpty()){
			if(GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)){
				sendNotification("Send error: " + extras.toString());
			}else if(GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)){
				sendNotification("Deleted messages on sever: " + extras.toString());
			}else if(GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)){
				for(int i = 0; i < 5; i++){
					Log.v(TAG, "Working... " + ( i + 1 ) + "/5 @ " + SystemClock.elapsedRealtime());
					
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
					}
					
					Log.v(TAG, "Completed work @ " + SystemClock.elapsedRealtime());
					sendNotification("Recevied: " + extras.toString());
					Log.v(TAG, "Recevied: " + extras.toString());
				}
			}
		}
		
		GcmBroadcastReceiver.completeWakefulIntent(intent);
	}
	
	private void sendNotification(String msg){
		Log.v(TAG, "sendNotification : " + msg);
		
		_ntm = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
																		.setSmallIcon(R.drawable.ic_launcher)
																		.setContentTitle("GCM Notification")
																		.setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
																		.setContentText(msg);
		mBuilder.setContentIntent(contentIntent);
		_ntm.notify(NOTIFICATION_ID, mBuilder.build());
	}
}
