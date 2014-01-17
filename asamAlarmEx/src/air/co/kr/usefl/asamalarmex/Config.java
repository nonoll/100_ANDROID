package air.co.kr.usefl.asamalarmex;

public class Config {
	public static final String MSG_TITLE = "설정하신 아삼 알람시간입니다.";
	
	public static final String REGEN_ID_1 = "regen_1";
	public static final String REGEN_TIME_1 = "09:30";
	public static final String REGEN_MSG_1 = "몹 리셋 타임입니다.";
	public static final int REGEN_CODE_1 = 11;
	
	public static final String REGEN_ID_2 = "regen_2";
	public static final String REGEN_TIME_2 = "12:30";
	public static final String REGEN_MSG_2 = "몹 리셋 타임입니다.";
	public static final int REGEN_CODE_2 = 12;
	
	public static final String REGEN_ID_3 = "regen_3";
	public static final String REGEN_TIME_3 = "19:00";
	public static final String REGEN_MSG_3 = "몹 리셋 타임입니다.";
	public static final int REGEN_CODE_3 = 13;
	
	public static final String REGEN_ID_4 = "regen_3";
	public static final String REGEN_TIME_4 = "21:00";
	public static final String REGEN_MSG_4 = "몹 리셋 타임입니다.";
	public static final int REGEN_CODE_4 = 14;
	
	
	
	public static final String TNM_ID_1 = "tnm_1";
	public static final String TNM_TIME_1 = "10:00";
	public static final String TNM_MSG_1 = "토너먼트 타임입니다.";
	public static final int TNM_CODE_1 = 21;
	
	public static final String TNM_ID_2 = "tnm_2";
	public static final String TNM_TIME_2 = "15:00";
	public static final String TNM_MSG_2 = "토너먼트 타임입니다.";
	public static final int TNM_CODE_2 = 22;
	
	public static final String TNM_ID_3 = "tnm_3";
	public static final String TNM_TIME_3 = "21:00";
	public static final String TNM_MSG_3 = "토너먼트 타임입니다.";
	public static final int TNM_CODE_3 = 23;
	
	
	
	public static final String PIR_ID_1 = "pir_1";
	public static final String PIR_TIME_1 = "16:00";
	public static final String PIR_MSG_1 = "해적 리셋 타임입니다.";
	public static final int PIR_CODE_1 = 31;
	
	public static final String PIR_ID_2 = "pir_2";
	public static final String PIR_TIME_2 = "21:30";
	public static final String PIR_MSG_2 = "해적 리셋 타임입니다.";
	public static final int PIR_CODE_2 = 32;
	
	public static final String USER_DEFAULT_TEXT = "시간입력";
	public static final String USER_ID_1 = "user_1";
	public static final int USER_CODE_1 = 41;
	
	public static final String USER_ID_2 = "user_2";
	public static final int USER_CODE_2 = 42;
	
	public static final String USER_ID_3 = "user_3";
	public static final int USER_CODE_3 = 43;
	
	
	public Config() {
	}
	
	public static String getMessage(int code){
		String message = "";
		switch (code) {
			case REGEN_CODE_1:
				message = REGEN_MSG_1;
				break;
			case REGEN_CODE_2:
				message = REGEN_MSG_2;
				break;
			case REGEN_CODE_3:
				message = REGEN_MSG_3;
				break;
				
			case TNM_CODE_1:
				message = TNM_MSG_1;
				break;
			case TNM_CODE_2:
				message = TNM_MSG_2;
				break;
			case TNM_CODE_3:
				message = TNM_MSG_3;
				break;
				
			case PIR_CODE_1:
				message = PIR_MSG_1;
				break;
			case PIR_CODE_2:
				message = PIR_MSG_2;
				break;
				
			default:
				message = "설정하신 시간입니다.";
				break;
		}
		
		return message;
	}
}
