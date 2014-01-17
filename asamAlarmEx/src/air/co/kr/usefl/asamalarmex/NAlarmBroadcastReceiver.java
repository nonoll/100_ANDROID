package air.co.kr.usefl.asamalarmex;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class NAlarmBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String id = intent.getExtras().get("id").toString();
		int code = Integer.parseInt(intent.getExtras().get("code").toString());
		String message = Config.getMessage(code);
		Log.v("btn", "id : " + id + " , code : " + code + " , message : " + message );
		new NToast().onToast(context, Config.MSG_TITLE + "\n" + message, Toast.LENGTH_LONG);
		new NNotification().onNotification(context, Config.MSG_TITLE, message, 0);
	}
}
