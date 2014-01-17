package kr.co.usefl.nhybridapp.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;

public class NetworkInfo {
	public final String NETWORK_WIFI = "wifi";
	public final String NETWORK_MOBILE = "mobile";
	public final String NETWORK_OFF_LINE = "off";
	public final String NETWORK_OFF_LINE_MSG = "커스텀 메시지 Wifi 혹은 3G망이 연결되지 않았거나 원활하지 않습니다.네트워크 확인후 다시 접속해 주세요!";
	private Context _context;
	
	public NetworkInfo(Context context) {
		_context = context;
	}
	
	public boolean isOnline(){
		return (getOnlineType().equals(NETWORK_OFF_LINE)) ? false : true;
	}
	
	public boolean isWifi(){
		return (getOnlineType().equals(NETWORK_WIFI)) ? false : true;
	}
	
	public boolean isMobile(){
		return (getOnlineType().equals(NETWORK_MOBILE)) ? false : true;
	}
	
	public String getOnlineType(){
		try {
			ConnectivityManager conManager = (ConnectivityManager) _context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
			
			State wifi = conManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
			if(wifi == State.CONNECTED || wifi == State.CONNECTING) {
				return NETWORK_WIFI;
			}
			
			State mobile = conManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
			if(mobile == State.CONNECTED || mobile == State.CONNECTING ) {
				return NETWORK_MOBILE;
			}
		}catch(NullPointerException e) {
			return NETWORK_OFF_LINE;
		}
		return NETWORK_OFF_LINE;
	}
}
