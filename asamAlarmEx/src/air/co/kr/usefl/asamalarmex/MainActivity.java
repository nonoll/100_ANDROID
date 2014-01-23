package air.co.kr.usefl.asamalarmex;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import com.google.ads.*;

import air.co.kr.usefl.asamalarmex.app.NAlarmManager;
import air.co.kr.usefl.asamalarmex.app.NDialog;
import air.co.kr.usefl.asamalarmex.app.NNotification;
import air.co.kr.usefl.asamalarmex.kakao.KakaoLink;
import air.co.kr.usefl.asamalarmex.util.CurrencyFormat;
import air.co.kr.usefl.asamalarmex.widget.NToast;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources.Theme;
import android.database.sqlite.SQLiteDatabase;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DigitalClock;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

@SuppressLint("NewApi")
@SuppressWarnings("unused")
public class MainActivity extends Activity implements OnClickListener {
	private static String _encoding = "UTF-8";
	private static NNotification _noti = new NNotification();
	private static NToast _toast = new NToast();
	private static LinearLayout _clockArea;
	private static CurrencyFormat _cfm = new CurrencyFormat();
	private static NDialog _dialog = new NDialog();
	private static NAlarmManager _alarmManager;
	
	
	private int hour;
	private int minute;
	
	SQLiteDatabase db;
	
	static final int[] BTNS = {
		R.id.btn_share,
		R.id.toggleButton1,
		R.id.toggleButton2,
		R.id.toggleButton3,
		R.id.toggleButton4,
		R.id.toggleButton5,
		R.id.toggleButton6,
		R.id.toggleButton7,
		R.id.toggleButton8,
		R.id.toggleButton9,
		R.id.tf_user1,
		R.id.tf_user2,
		R.id.tf_user3
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}
	
	private void init() {
		Config.MAIN_ACTIVITY = this;
		_alarmManager = new NAlarmManager(getApplicationContext());
		
		drawDisplay();
		setAdmob();
		setBtnsEvents();
	}

	private void setAdmob() {
		String admobKey = "a1528ae7740b03a";
		AdView adView = new AdView( this, AdSize.BANNER, admobKey);
		LinearLayout adArea = (LinearLayout) findViewById(R.id.admob_area);
		adArea.addView(adView);
		adArea.setBottom(0);
		adView.loadAd(new AdRequest());
	}

	@SuppressWarnings("deprecation")
	private void drawDisplay() {
		// 디지털 시계
		_clockArea = (LinearLayout) findViewById(R.id.clock_area);
		air.co.kr.usefl.asamalarmex.util.DigitalClock dg_clock = new air.co.kr.usefl.asamalarmex.util.DigitalClock(getApplicationContext());
		dg_clock.setId(0x94667686);
		_clockArea.addView(dg_clock, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
	}
	
	private void setBtnsEvents() {
		for(int btnId: BTNS){
			findViewById(btnId).setOnClickListener(this);
		}
		
		CheckBox tf_user1 = (CheckBox) findViewById(R.id.tf_user1);
		tf_user1.setOnCheckedChangeListener(UserListener);
		
		CheckBox tf_user2 = (CheckBox) findViewById(R.id.tf_user2);
		tf_user2.setOnCheckedChangeListener(UserListener);
		
		CheckBox tf_user3 = (CheckBox) findViewById(R.id.tf_user3);
		tf_user3.setOnCheckedChangeListener(UserListener);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		Log.v("btn", "MainActivity onClick");
		switch (id) {
			case R.id.btn_share:
				try {
					sendAppData(v);
				} catch (NameNotFoundException e) {
					e.printStackTrace();
				}
				break;
			case R.id.toggleButton1:
				_alarmManager.setAlarm(Config.REGEN_TIME_1, Config.REGEN_ID_1, Config.REGEN_CODE_1);
				break;
			case R.id.toggleButton2:
				_alarmManager.setAlarm(Config.REGEN_TIME_2, Config.REGEN_ID_2, Config.REGEN_CODE_2);
				break;
			case R.id.toggleButton3:
				_alarmManager.setAlarm(Config.REGEN_TIME_3, Config.REGEN_ID_3, Config.REGEN_CODE_3);
				break;
			case R.id.toggleButton4:
				_alarmManager.setAlarm(Config.REGEN_TIME_4, Config.REGEN_ID_4, Config.REGEN_CODE_4);
				break;
				
			case R.id.toggleButton5:
				_alarmManager.setAlarm(Config.TNM_TIME_1, Config.TNM_ID_1, Config.TNM_CODE_1);
				break;
			case R.id.toggleButton6:
				_alarmManager.setAlarm(Config.TNM_TIME_2, Config.TNM_ID_2, Config.TNM_CODE_2);
				break;
			case R.id.toggleButton7:
				_alarmManager.setAlarm(Config.TNM_TIME_3, Config.TNM_ID_3, Config.TNM_CODE_3);
				break;
				
			case R.id.toggleButton8:
				_alarmManager.setAlarm(Config.PIR_TIME_1, Config.PIR_ID_1, Config.PIR_CODE_1);
				break;
			case R.id.toggleButton9:
				_alarmManager.setAlarm(Config.PIR_TIME_2, Config.PIR_ID_2, Config.PIR_CODE_2);
				break;
			case R.id.tf_user1:
				_dialog.DialogTimePicker(this, (CheckBox) findViewById(R.id.tf_user1));
				break;
			case R.id.tf_user2:
				_dialog.DialogTimePicker(this, (CheckBox) findViewById(R.id.tf_user2));
				break;
			case R.id.tf_user3:
				_dialog.DialogTimePicker(this, (CheckBox) findViewById(R.id.tf_user3));
				break;
			default:
				break;
		}
	}
	CompoundButton.OnCheckedChangeListener UserListener = new CompoundButton.OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			/*
			Log.v("btn", "bbbbb");
			if(isChecked){
				switch (buttonView.getId()) {
					case R.id.tf_user1:
						Log.v("btn", "aaaaa");
						break;
	
					default:
						Log.v("btn", "cccccc");
						break;
					}
			}
			*/
		}
	};

	/**
	 * Send App data
	 */
	public void sendAppData(View v) throws NameNotFoundException {
		ArrayList<Map<String, String>> metaInfoArray = new ArrayList<Map<String, String>>();

		Map<String, String> metaInfoAndroid = new Hashtable<String, String>(1);
		metaInfoAndroid.put("os", "android");
		metaInfoAndroid.put("devicetype", "phone");
		metaInfoAndroid.put("installurl", "market://details?id=air.co.kr.usefl.asamAlarm");
		metaInfoAndroid.put("executeurl", "kakaoLinkTest://starActivity");
		
		Map<String, String> metaInfoIOS = new Hashtable<String, String>(1);
		metaInfoIOS.put("os", "ios");
		metaInfoIOS.put("devicetype", "phone");
		metaInfoIOS.put("installurl", "your iOS app install url");
		metaInfoIOS.put("executeurl", "kakaoLinkTest://starActivity");
		
		metaInfoArray.add(metaInfoAndroid);
		metaInfoArray.add(metaInfoIOS);
		
		KakaoLink kakaoLink = KakaoLink.getLink(getApplicationContext());
		
		// check, intent is available.
		if(!kakaoLink.isAvailableIntent()) {
			alert("Not installed KakaoTalk.");
			return;
		}
		
		kakaoLink.openKakaoAppLink(
				this, 
				"http://usefl.co.kr", 
				"아이러브 삼국지\n알람어플 다운(부끄)",
				getPackageName(), 
				getPackageManager().getPackageInfo(getPackageName(), 0).versionName,
				"아삼알람",
				_encoding, 
				metaInfoArray);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void alert(String message) {
		new AlertDialog.Builder(this)
			.setIcon(android.R.drawable.ic_dialog_alert)
			.setTitle(R.string.app_name)
			.setMessage(message)
			.setPositiveButton(android.R.string.ok, null)
			.create().show();
	}
}
