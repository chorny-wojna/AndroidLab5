package by.bsu.famcs.lab52;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
    static final int DB_VERSION = 2;
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

        dbHelper = new DBHelper(getApplication());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);

        ContentValues cv = new ContentValues();
        if (DB_VERSION == 1) {
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
            cv.put("name", "Vladimir");
            cv.put("record_time", System.currentTimeMillis());
            lastId = db.insert(TABLE_NAME, null, cv);
        } else {
            cv.put("last_name", "Ivanov");
            cv.put("first_name", "Ivan");
            cv.put("patronymic", "Ivanovich");
            cv.put("record_time", System.currentTimeMillis());
            lastId = db.insert(MainActivity.TABLE_NAME, null, cv);
        }
        dbHelper.close();
    }

    @Override
    public void onClick(View v) {
        ContentValues cv = new ContentValues();

        String name = etName.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch (v.getId()) {
            case R.id.btnAdd:
                if (DB_VERSION == 1) {
                    cv.put("name", name);
                    cv.put("record_time", System.currentTimeMillis());
                    lastId = db.insert(TABLE_NAME, null, cv);
                }
                break;
            case R.id.btnRead:
                Intent intent = new Intent(this, InfoActivity.class);
                startActivity(intent);
                break;
            case R.id.btnReplace:
                if (DB_VERSION == 1) {
                    db.execSQL("update " + TABLE_NAME + " set name='Иванов Иван Иванович' "
                    + "where id = " + lastId);
                } else {
                    db.execSQL("update " + TABLE_NAME + " set last_name='Иванов' "
                            + "where id = " + lastId);
                }

                break;
        }
        dbHelper.close();
    }
}
