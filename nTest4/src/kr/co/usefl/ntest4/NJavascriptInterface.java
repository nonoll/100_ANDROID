package kr.co.usefl.ntest4;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class NJavascriptInterface {
	Context _context;
	
	public NJavascriptInterface(Context context){
		_context = context;
	}
	
	public void toast(String msg, String sDuration){
		Log.v("btn", "toast");
		int duration = (sDuration.equals("long")) ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;
		Toast.makeText(_context, msg, duration).show();
	}
	
	public void dialog(String title, String msg){
		new AlertDialog.Builder(_context)
						.setIcon(android.R.drawable.ic_dialog_alert)
						.setTitle(title)
						.setMessage(msg)
						.setPositiveButton(android.R.string.ok, null)
						.create()
						.show();
	}
}
