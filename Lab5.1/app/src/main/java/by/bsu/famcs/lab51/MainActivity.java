package by.bsu.famcs.lab51;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

    Button btnAdd, btnRead, btnReplace;
    EditText etName;
    DBHelper dbHelper;
    static final String TABLE_NAME = "CLASSMATES";
    long lastId = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        btnRead = (Button) findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);
        btnReplace = (Button) findViewById(R.id.btnReplace);
        btnReplace.setOnClickListener(this);
        etName = (EditText) findViewById(R.id.etName);

        dbHelper = new DBHelper(this);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);

        ContentValues cv = new ContentValues();
        cv.put("name", "Ivan");
        cv.put("record_time", System.currentTimeMillis());
        lastId = db.insert(TABLE_NAME, null, cv);
        cv.put("name", "Jan");
        cv.put("record_time", System.currentTimeMillis());
        lastId = db.insert(TABLE_NAME, null, cv);
        cv.put("name", "Roman");
        cv.put("record_time", System.currentTimeMillis());
        lastId = db.insert(TABLE_NAME, null, cv);
        cv.put("name", "Alex");
        cv.put("record_time", System.currentTimeMillis());
        lastId = db.insert(TABLE_NAME, null, cv);
        cv.put("name", "Validimir");
        cv.put("record_time", System.currentTimeMillis());
        lastId = db.insert(TABLE_NAME, null, cv);
        dbHelper.close();
    }

    @Override
    public void onClick(View v) {
        ContentValues cv = new ContentValues();

        String name = etName.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch (v.getId()) {
            case R.id.btnAdd:
                cv.put("name", name);
                cv.put("record_time", System.currentTimeMillis());
                lastId = db.insert(TABLE_NAME, null, cv);
                break;
            case R.id.btnRead:
                Intent intent = new Intent(this, InfoActivity.class);
                startActivity(intent);
                break;
            case R.id.btnReplace:
                db.execSQL("update " + TABLE_NAME + " set name='Иванов Иван Иванович' "
                + "where id = " + lastId);
                break;
        }
        dbHelper.close();
    }
}
