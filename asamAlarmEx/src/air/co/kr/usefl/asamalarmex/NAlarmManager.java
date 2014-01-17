package air.co.kr.usefl.asamalarmex;

import java.text.ParseException;

import air.co.kr.usefl.asamalarmex.util.CurrencyFormat;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class NAlarmManager {
	private static final long DAY_REAPET = 24 * 60 * 60 * 1000;
	private static NToast _toast = new NToast();
	private CurrencyFormat _cfm = new CurrencyFormat();
	private Context _context;

	public NAlarmManager(Context context){
		_context = context;
	}
	
	public void setAlarm(String time, String id, int code){
		long second = 0;
		try {
			second = _cfm.getTimeToMillis( _cfm.getTimeToYMD() + " " + time.toString() );
			_toast.onToast(_context.getApplicationContext(), "아삼 알람설정 " + time, Toast.LENGTH_SHORT);
		} catch (ParseException e) {
			e.printStackTrace();
			_toast.onToast(_context.getApplicationContext(), "아삼 알람설정에 실패했습니다.", Toast.LENGTH_SHORT);
		}
		setAlarmBroadcast(_context.getApplicationContext(), second, NAlarmBroadcastReceiver.class, id, code);
	}
	
	/**
	 * 알람 등록 - 현재 시간을 기준으로 second 파라미터만큼의 알람을 설정한다
	 * @param context
	 * @param second	알람 시간
	 * @param cls
	 */
	public void setAlarmNormal(Context context, long second, Class<?> cls){
		AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context.getApplicationContext(), cls);
		PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);
		
		// 설정하신 시간이 현재 시간보다 이전일 경우 바로 알람이 실행되므로
		// 현재시간과 비교하여 1일을 더 추가하여 알람을 설정한다
		second = ( second < System.currentTimeMillis() ) ? second + DAY_REAPET : second;
		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + second, DAY_REAPET, pIntent);
	}
	
	/**
	 * 알람 등록 - 브로드캐스트 리시브
	 * @param context
	 * @param second	알람 시간
	 * @param cls
	 * @param id		알람의 id
	 * @param code		알람의 code
	 */
	public void setAlarmBroadcast(Context context, long second, Class<?> cls, String id, int code){
		AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context.getApplicationContext(), cls);
		intent.putExtra("id", id);
		intent.putExtra("code", code);
		PendingIntent pIntent = PendingIntent.getBroadcast(context, code, intent, 0);
		
		// 설정하신 시간이 현재 시간보다 이전일 경우 바로 알람이 실행되므로
		// 현재시간과 비교하여 1일을 더 추가하여 알람을 설정한다
		second = ( second < System.currentTimeMillis() ) ? second + DAY_REAPET : second;
		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, second, DAY_REAPET, pIntent);
	}
	
	/**
	 * 알람 등록 해제 - 등록한 알람의 id 값으로 알람 설정을 해제한다.
	 * @param context
	 * @param cls
	 * @param id
	 */
	public void unregisterAlarm(Context context, Class<?> cls, int id){
		Log.v("btn", "unregisterAlarm");
		
		AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context.getApplicationContext(), cls);
		PendingIntent pIntent = PendingIntent.getBroadcast(context, id, intent, 0);
		alarmManager.cancel(pIntent);
	}
	
	public boolean isAlarm(Context context, Class<?> cls, int id){
		Log.v("btn", "isAlarm");
		Intent intent = new Intent(context.getApplicationContext(), cls);
		PendingIntent pIntent = PendingIntent.getBroadcast(context, id, intent, 0);
		return pIntent != null;
	}
		/*
		Log.v("btn", "isAlarm");
		boolean result;
		Intent intent = new Intent(context.getApplicationContext(), cls);
		PendingIntent pIntent = PendingIntent.getBroadcast(context, id, intent, 0);
		//PendingIntent pIntent = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_NO_CREATE);
		result = pIntent != null;
		Log.v("btn", "[isAlarmActivated] " + result + " - " + pIntent);
		return result;
		*/
}
