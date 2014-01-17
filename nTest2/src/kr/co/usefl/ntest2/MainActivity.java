package kr.co.usefl.ntest2;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	public WebView webview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btn1 = (Button) findViewById(R.id.button1);
		Button btn2 = (Button) findViewById(R.id.button2);
		Button btn3 = (Button) findViewById(R.id.button3);
		
		btn1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.v("btn1", "click");
				Toast.makeText(getApplicationContext(), "아삼알람 실행", Toast.LENGTH_LONG).show();
				Intent intent = getApplicationContext().getPackageManager().getLaunchIntentForPackage("air.co.kr.usefl.asamAlarm");
				getApplicationContext().startActivity(intent);
			}
		});
		
		btn2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.v("btn2", "click");
				Toast.makeText(getApplicationContext(), "아이러브 삼국지 실행", Toast.LENGTH_LONG).show();
				Intent intent2 = getApplicationContext().getPackageManager().getLaunchIntentForPackage("com.uqee.lying.maingameHanguo");
				getApplicationContext().startActivity(intent2);
			}
		});
		
		btn3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.v("btn3", "click");
				webview = (WebView) findViewById(R.id.webView1);
				webview.getSettings().setJavaScriptEnabled(true);
				webview.loadUrl("http://usefl.co.kr");
				webview.setWebViewClient(new WebViewClient());
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
