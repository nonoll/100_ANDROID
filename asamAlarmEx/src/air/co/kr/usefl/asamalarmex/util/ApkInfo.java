package air.co.kr.usefl.asamalarmex.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

public class ApkInfo {

	public ApkInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public String getApkVersionName(Context context, String pkgName) throws NameNotFoundException{
		PackageInfo pkgInfo = context.getApplicationContext().getPackageManager().getPackageInfo(pkgName, 0);
		return pkgInfo.versionName;
	}
	
	public int getApkVersionCode(Context context, String pkgName) throws NameNotFoundException{
		PackageInfo pkgInfo = context.getApplicationContext().getPackageManager().getPackageInfo(pkgName, 0);
		return pkgInfo.versionCode;
	}
}
