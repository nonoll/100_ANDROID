package kr.co.usefl.nhybridapp;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
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
		if(url.startsWith("nHybridApp://")){
			String str = String.valueOf(url.split("nHybridApp://")[1]);
			String type = String.valueOf(str.split("=")[0]);
			String param = String.valueOf(str.split("=")[1]);
			Log.v("btn", "type : " + type + " , param : " + param);
			if(type.equals("pdf")){
				Log.v("btn", "pdf open : " + param);
				webview.loadUrl("https://docs.google.com/viewer?url=" + param);
			}
			
			if(type.equals("down_pdf")){
				Log.v("btn", "down_pdf : " + param);
				Config.main.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(param)));
			}
			
			if(type.equals("app_pdf")){
				Log.v("btn", "app_pdf : " + param);
				//Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
				File fileUri = new File( Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator + "test_pdf.pdf" );
				Log.v("btn", "fileUri : " + fileUri.toString());
				param = fileUri.toString();
				Uri path = Uri.fromFile(fileUri);
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setDataAndType(path, "application/pdf");
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				Config.main.startActivity(intent);
			}
			Toast.makeText(_context, type + " : " + param, Toast.LENGTH_LONG).show();
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