package kr.co.usefl.ntts;

import java.util.List;
import java.util.Locale;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.Engine;
import android.speech.tts.TextToSpeech.EngineInfo;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private TextToSpeech _tts;
	private boolean _isInit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.v("btn","onclick");
				testTTS();
			}
		});
	}
	
	@SuppressLint("NewApi")
	private void testTTS(){
		_tts = new TextToSpeech(getApplicationContext(), initListener);
		_tts.setLanguage(Locale.KOREA);
		_tts.speak("TTS", TextToSpeech.QUEUE_FLUSH, null);
	}
	
	TextToSpeech.OnInitListener initListener = new TextToSpeech.OnInitListener() {
		@Override
		public void onInit(int status) {
			Log.v("btn", String.valueOf(status) + " , " + String.valueOf(TextToSpeech.SUCCESS));
			_isInit = status == TextToSpeech.SUCCESS;
			String msg = _isInit ? "success" : "failed";
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
			_tts.speak("만 호출 할 수있는 이야기 () )의 onInit 후에는 (불렸다. 그래서 TTS는의 onInit에하지 OnCreate 코드를 말한다 ()으로 이동", TextToSpeech.QUEUE_FLUSH, null);
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
