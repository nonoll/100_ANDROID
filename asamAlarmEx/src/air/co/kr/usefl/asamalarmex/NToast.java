package air.co.kr.usefl.asamalarmex;

import android.content.Context;
import android.widget.Toast;

public class NToast {
	public NToast(){
		
	}
	
	public void onToast(Context context, String msg, int duration){
		Toast.makeText(context, msg, duration).show();
	}
}
