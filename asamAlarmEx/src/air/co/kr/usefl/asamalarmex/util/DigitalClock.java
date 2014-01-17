package air.co.kr.usefl.asamalarmex.util;

import java.util.Calendar;

import android.content.Context;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.widget.TextView;

@SuppressWarnings("unused")
public class DigitalClock extends TextView {
	Calendar _calendar;
	String _timeForamt;
	private static final String TIME_FORMAT_12 = "h:mm";
	private static final String TIME_FORMAT_24 = "k:mm";
	private FormatChangeObserver _fmc;
	
	private Runnable _runnable;
	private Handler _handler;
	
	private boolean _runnableStopped = false;
	
	public DigitalClock(Context context) {
		super(context);
		initClock(context);
	}
	
	private void initClock(Context context){
		Resources res = context.getResources();
		
		if(_calendar == null){
			_calendar = Calendar.getInstance();
		}
		
		_fmc = new FormatChangeObserver(new Handler());
		getContext().getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, _fmc);
		setFormat();
	}
	
	@Override
	protected void onAttachedToWindow(){
		_runnableStopped = false;
		super.onAttachedToWindow();
		_handler = new Handler();
		
		_runnable = new Runnable() {
			@Override
			public void run(){
				if(_runnableStopped) return;
				
				_calendar.setTimeInMillis(System.currentTimeMillis());
				setText(DateFormat.format(_timeForamt, _calendar));
				
				invalidate();
				
				long now = SystemClock.uptimeMillis();
				long next = now + (1000 - now % 1000);
				
				_handler.postAtTime(_runnable, next);
			}
		};
		
		_runnable.run();
	}
	
	@Override
	protected void onDetachedFromWindow(){
		super.onDetachedFromWindow();
		_runnableStopped = true;
	}
	
	private boolean get24HourMode(){
		return DateFormat.is24HourFormat(getContext());
	}
	
	private void setFormat(){
		_timeForamt = TIME_FORMAT_24;
		/*
		if(get24HourMode()){
			_timeForamt = TIME_FORMAT_24;
		}else{
			_timeForamt = TIME_FORMAT_12;
		}
		*/
	}
	
	private class FormatChangeObserver extends ContentObserver{
		public FormatChangeObserver(Handler handler) {
			super(handler);
		}
		
		public void onChange(boolean b){
			setFormat();
		}
	}
}
