package by.bsu.famcs.lab51;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

public class InfoActivity extends AppCompatActivity {

    EditText etData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        etData = (EditText) findViewById(R.id.etData);
        etData.setText("");

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query(MainActivity.TABLE_NAME, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int idColIndex = c.getColumnIndex("id");
            int nameColIndex = c.getColumnIndex("name");
            int recordTimeColIndex = c.getColumnIndex("record_time");
            do {
                etData.setText(etData.getText().toString()
                        + "ID = " + c.getInt(idColIndex) +
                        ", name = " + c.getString(nameColIndex) +
                        ", record_time = " + c.getInt(recordTimeColIndex) + "\n");
            } while (c.moveToNext());
        } else
            etData.setText("0 rows");
        c.close();
        dbHelper.close();
    }
}
