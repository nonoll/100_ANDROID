package kr.co.usefl.nhybridapp;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class HybridAppUrlScheme extends WebViewClient {
	private Context _context;
	public HybridAppUrlScheme(Context context) {
		_context = context;
	}
	
	public boolean shouldOverrideUrlLoading(WebView webview, String url){
		Log.v("btn", "shouldOverrideUrlLoading : " + url);
		try {
			url = URLDecoder.decode(url, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Log.v("btn", "URLDecoder : " + url);
		if(url.startsWith("nHybridApp")){
			String str = String.valueOf(url.split("nHybridApp")[1]);
			String toastMsg = String.valueOf(str.split("=")[1]);
			Log.v("btn", "toastMsg : " + toastMsg);
			Toast.makeText(_context, toastMsg, Toast.LENGTH_LONG).show();
			return true;
		}
		return false;
	}
	
	public void onPageStarted(WebView webview, String url, android.graphics.Bitmap favicon) {
		super.onPageStarted(webview, url, favicon);
		Config.PROGRESS_BAR.setVisibility(View.VISIBLE);
	};

	public void onPageFinished(WebView webview, String url) {
		super.onPageFinished(webview, url);
		Config.PROGRESS_BAR.setVisibility(View.INVISIBLE);
	};

	public void onReceivedError(WebView webview, int errorCode, String description, String failingUrl) {
		super.onReceivedError(webview, errorCode, description, failingUrl);
		Toast.makeText(_context, "로딩오류" + description, Toast.LENGTH_SHORT).show();
	};
}