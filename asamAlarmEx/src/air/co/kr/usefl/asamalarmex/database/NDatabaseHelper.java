package air.co.kr.usefl.asamalarmex.database;

import air.co.kr.usefl.asamalarmex.Config;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NDatabaseHelper extends SQLiteOpenHelper {
	
	public NDatabaseHelper(Context context) {
		super(context, Config.DATA_BASE_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String query = "";
		query += "CREATE TABLE";
		query += Config.DATA_BASE_NAME;
		query += " (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, data TEXT)";
		//db.execSQL("INSERT INTO Android VALUES (null, 'Cupcake'         , 500       );");
		db.execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
