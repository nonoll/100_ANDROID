package kr.co.usefl.ntest3;

import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony.Sms;
import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.add(0, 0, Menu.NONE, "어플 종료").setIcon(android.R.drawable.ic_menu_add);
		menu.add(0, 1, Menu.CATEGORY_ALTERNATIVE, "개발자 웹사이트").setIcon(android.R.drawable.ic_menu_agenda);
		menu.add(0, 2, Menu.CATEGORY_ALTERNATIVE, "SMS 보내기").setIcon(android.R.drawable.ic_menu_agenda);
		
		for(int i = 0; i < menu.size(); i++){
			MenuItem mItem = (MenuItem) menu.getItem(i);
			mItem.setOnMenuItemClickListener(menuItemClickListener);
		}
		
		/*
		menu.getItem(1).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Log.v("btn", "메뉴 클릭");
				return false;
			}
		});
		*/
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private OnMenuItemClickListener menuItemClickListener = new MenuItem.OnMenuItemClickListener() {
		@Override
		public boolean onMenuItemClick(MenuItem item) {
			int id = item.getItemId();
			Log.v("btn", "id : " + id);
			switch (id) {
				case 0:
					finish();
					break;
				case 1:
					Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://usefl.co.kr"));
					startActivity(webIntent);
					break;
				case 2:
					/*
					Intent smsIntent = new Intent(Intent.ACTION_VIEW);
					smsIntent.putExtra("sms_body", "입력테스트");
					smsIntent.setType("vnd.android-dir/mms-sms");
					startActivity(smsIntent);
					*/
					String tel = "01094667686";
					SmsManager smsMgr = SmsManager.getDefault();
					try {
						smsMgr.sendTextMessage(tel, null, "메시지내용", null, null);
						Toast.makeText(getApplicationContext(), "발신성공", Toast.LENGTH_LONG).show();
					} catch (Exception e) {
						// TODO: handle exception
						Toast.makeText(getApplicationContext(), "발신실패", Toast.LENGTH_LONG).show();
					}
					
					break;
				default:
					break;
			}
			return false;
		}
	};
	
}
