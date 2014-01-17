package air.co.kr.usefl.asamalarmex;


import air.co.kr.usefl.asamalarmex.util.CurrencyFormat;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;

public class NDialog{
	private static CurrencyFormat _cfm = new CurrencyFormat();
	private static NAlarmManager _alarmManager;
	private TextView _textView;
	private Context _context;
	
	public NDialog(){}
	
	public void DialogTimePicker(Context context, TextView tv){
		_context = context;
		_alarmManager = new NAlarmManager(_context);
		_textView = tv;
		TimePickerDialog alert = new TimePickerDialog(context, timePickerListener, 0, 0, false);
		alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
			@Override
			public void onDismiss(DialogInterface dialog) {
				CheckBox cb = (CheckBox) _textView;
				if(_textView.getText().equals(Config.USER_DEFAULT_TEXT)){
					if(cb.isChecked()){
						cb.setChecked(false);
					}
				}else{
					Log.v("btn", "_textView.getId() : " + _textView.getId() + " , " + (_textView.getId() == R.id.tf_user1) );
					if(cb.isChecked()){
						switch (_textView.getId()) {
							case R.id.tf_user1:
								_alarmManager.setAlarm((String) _textView.getText(), Config.USER_ID_1, Config.USER_CODE_1);
								break;
							case R.id.tf_user2:
								_alarmManager.setAlarm((String) _textView.getText(), Config.USER_ID_2, Config.USER_CODE_2);
								break;
							case R.id.tf_user3:
								_alarmManager.setAlarm((String) _textView.getText(), Config.USER_ID_3, Config.USER_CODE_3);
								break;
							default:
								break;
						}
					}
				}
			}
		});
		alert.show();
	}
	
	public TimePickerDialog.OnTimeSetListener getTimePickerListener() {
		return timePickerListener;
	}

	public void setTimePickerListener(TimePickerDialog.OnTimeSetListener timePickerListener) {
		this.timePickerListener = timePickerListener;
	}

	private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hour, int minute) {
			if(_textView != null){
				_textView.setText(_cfm.addZero(hour) + ":" + _cfm.addZero(minute));
			}
		}
	};
}