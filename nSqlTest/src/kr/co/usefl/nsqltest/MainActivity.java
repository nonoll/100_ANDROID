package kr.co.usefl.nsqltest;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	final String DB_NAME = "testDb01";

    Button button;
    EditText editText;
    ListView listView;
    SQLiteDatabase db;
    ArrayList<Item> items = new ArrayList<Item>();
    MyAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.Button_addList);
        editText = (EditText) findViewById(R.id.EditText);
        listView = (ListView) findViewById(R.id.ListView);
        listView.setOnItemClickListener(new OnItemClickListener() {
        	@Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Item item = items.get(position);

                db.delete(DB_NAME, "_id=" + item.id, null);
                updateDb();
            }
		});
        listView.setOnItemLongClickListener(new OnItemLongClickListener() {
        	@Override
            public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id) {
                Item item = items.get(position);
                ContentValues cv = new ContentValues();
                cv.put("title", "수정 됨");
                db.update(DB_NAME, cv, "_id=" + item.id, null);
                updateDb();
                return true;
            }
        });
        adapter = new MyAdapter(getApplicationContext());
        listView.setAdapter(adapter);

        db = new DatabaseHelper(this).getWritableDatabase();
        updateDb();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                if (text.length() == 0) {
                    return;
                }
                ContentValues cv = new ContentValues();
                cv.put("title", text);
                db.insert(DB_NAME, "null", cv);

                updateDb();
            }
        });
    }

    class Item {
        public long id;
        public String text;
    }

    class MyAdapter extends ArrayAdapter<Item> {

        public MyAdapter(Context context) {
            super(context, android.R.layout.simple_list_item_1, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv;
            if (convertView == null) {
                tv = new TextView(getApplicationContext());
            } else {
                tv = (TextView) convertView;
            }

            Item item = items.get(position);
            tv.setText(item.text);
            convertView = tv;

            return tv;
        }
    }

    public void updateDb() {
        items.clear();
        String[] columns = { "_id", "title" };
        Cursor c = db.query(DB_NAME, columns, null, null, null, null, null);
        if (c.getCount() <= 0) {
            adapter.notifyDataSetChanged();
            return;
        }

        c.moveToFirst();
        while (!c.isAfterLast()) {
            Item item = new Item();
            item.id = c.getLong(0);
            item.text = c.getString(1);
            items.add(item);
            c.moveToNext();
        }
        adapter.notifyDataSetChanged();
        c.close();
    }

    private class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, DB_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + DB_NAME
                    + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
