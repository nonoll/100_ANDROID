package kr.co.usefl.nhybridapp.bcrecevier;

import kr.co.usefl.nhybridapp.net.NetworkInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

/**
 * 네트워크 상태 체크 리시버
 * @author nonoll
 * @example
	<receiver android:name ="kr.co.usefl.nhybridapp.bcrecevier.NetworkRecevier">
	    <intent-filter>
	        <action android:name="android.net.conn.CONNECTIVITY_CHANGE" />
	    </intent-filter>
	</receiver>
 */
public class NetworkRecevier extends BroadcastReceiver {
	private NetworkInfo _networkInfo;
	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if(action.equals(ConnectivityManager.CONNECTIVITY_ACTION)){
			_networkInfo = new NetworkInfo(context);
			
			if(!_networkInfo.isOnline()){
				Toast.makeText(context, _networkInfo.NETWORK_OFF_LINE_MSG, Toast.LENGTH_LONG).show();
			}
			
		}
	}
}
