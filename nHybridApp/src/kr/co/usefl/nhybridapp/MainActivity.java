package kr.co.usefl.nhybridapp;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

@SuppressLint("SetJavaScriptEnabled")
public class MainActivity extends Activity {
	public WebView _webView;
	public ProgressBar _progress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Config.main = this;
		
		Config.PROGRESS_BAR = (ProgressBar) findViewById(R.id.pro);
		Config.PROGRESS_BAR.bringToFront();
		
		onWebview();
	}

	private void onWebview() {
		_webView = (WebView) findViewById(R.id.wv_conatiner);
		
		//javascript의 window.open 허용
		_webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		//javascript 허용
		_webView.getSettings().setJavaScriptEnabled(true);
		//meta태그의 viewport사용 가능
		_webView.getSettings().setUseWideViewPort(true);
		//인코딩설정
		_webView.getSettings().setDefaultTextEncodingName("euc-kr");
		
		_webView.setWebViewClient(new HybridAppUrlScheme(getApplicationContext()));
		_webView.setWebChromeClient(new WebChromeClient(){
			public void onProgressChanged(WebView webview, int progress) {
				Config.PROGRESS_BAR.setProgress(progress);
			}
		});
		
		_webView.loadUrl(Config.WEB_VIEW_URL);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && _webView.canGoBack()) {
			_webView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
