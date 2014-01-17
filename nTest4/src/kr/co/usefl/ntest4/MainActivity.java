package kr.co.usefl.ntest4;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

@SuppressLint({ "JavascriptInterface", "SetJavaScriptEnabled" })
public class MainActivity extends Activity {
	public String _webViewURL = "http://www.usefl.co.kr/app/test/webview.html";
	public WebView _webView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.activity_main);
		
		_webView = (WebView) findViewById(R.id.wv_conatiner);
		_webView.getSettings().setJavaScriptEnabled(true);
		_webView.getSettings().setBuiltInZoomControls(true);
		
		//_webView.addJavascriptInterface(new MyJavaScriptInterface(getApplicationContext()), "myJs");
		_webView.addJavascriptInterface(new NJavascriptInterface(getApplicationContext()), "myJs");
		//NJavascriptInterface jsInterface = new NJavascriptInterface(getApplicationContext());
		//_webView.addJavascriptInterface(jsInterface, "andFnc");

		_webView.loadUrl(_webViewURL);
		_webView.setWebChromeClient(new WebChromeClient());
		
		Button send = (Button) findViewById(R.id.button1);
		send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.v("btn", "sendClick");
				_webView.loadUrl("javascript:fromApp('test');");
			}
		});
	}
	
	public class MyJavaScriptInterface {
		private Context mContext;
		public MyJavaScriptInterface(Context context){
			Log.v("btn", "MyJavaScriptInterface");
			mContext = context;
		}
	    public void callAndroid(final String str) {
	    	Log.v("btn", "callAndroid");
	    	//Toast.makeText(getApplicationContext(), "아드디어", Toast.LENGTH_SHORT);
	    	Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
	    }
	}   
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
