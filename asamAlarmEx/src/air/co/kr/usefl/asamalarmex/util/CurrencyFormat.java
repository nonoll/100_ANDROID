package air.co.kr.usefl.asamalarmex.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;

/**
 * 단위 변환 유틸 클래스
 * @author nonoll
 */
@SuppressLint("SimpleDateFormat")
public class CurrencyFormat {
	private static SimpleDateFormat TIME_FORMAT_YMD = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat TIME_FORMAT_YMDHM = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	public CurrencyFormat(){}
	
	/**
	 * 
	 * @param num
	 * @return String
	 */
	public String addZero(int num) {
		if (num >= 10){
			return String.valueOf(num);
		}else{
			return "0" + String.valueOf(num);
		}
	}
	
	/**
	 * 현재 날짜 반환	- "yyyy-MM-dd"
	 * @return String
	 */
	public String getTimeToYMD(){
		return TIME_FORMAT_YMD.format(new Date());
	}
	
	/**
	 * 파라미터 시간을 밀리세컨으로 반환
	 * @param dateStr	- "yyyy-MM-dd HH:mm"
	 * @return
	 * @throws ParseException
	 */
	public long getTimeToMillis(String dateStr) throws ParseException{
		return TIME_FORMAT_YMDHM.parse(dateStr).getTime();
	}
}



/*
Calendar cal = Calendar.getInstance();
//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//sdf2.setTimeZone(TimeZone.getTimeZone("UTC"));
String inputString = "17:42";
Date date = null;
try {
	date = sdf2.parse("2014-01-08 " + inputString);
} catch (ParseException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
String test = df.format(new Date()); 

Log.v("btn", "시간설정 테스트 : " + System.currentTimeMillis() + " , " + cal.getTime().getTime() + " , " + sdf.format(System.currentTimeMillis()) );
Log.v("btn", "시간설정 테스트2 : " + date.getTime() + " , " + new Date().getDate() + " , " + test );

CurrencyFormat _cfm = new CurrencyFormat();
String str = _cfm.getTimeToYMD() + " 10:14".toString();
long lng = 0;
try {
	lng = _cfm.getTimeToMillis(str);
} catch (ParseException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
Log.v("btn", "시간설정 테스트3 : " + lng + " , " + str );
 */