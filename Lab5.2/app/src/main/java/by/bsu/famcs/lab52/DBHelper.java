package by.bsu.famcs.lab52;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Raman on 30-Oct-17.
 */
class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "myDB", null, MainActivity.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + MainActivity.TABLE_NAME + " ("
                + "id integer primary key autoincrement,"
                + "name text,"
                + "record_time integer);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "DROP ТАВLЕ IF EXISTS " + MainActivity.TABLE_NAME );
        db.execSQL("create table " + MainActivity.TABLE_NAME + " ("
                + "id integer primary key autoincrement,"
                + "last_name text,"
                + "first_name text,"
                + "patronymic text,"
                + "record_time integer);");
        ContentValues cv = new ContentValues();
        cv.put("last_name", "Ivanov");
        cv.put("first_name", "Ivan");
        cv.put("patronymic", "Ivanovich");
        cv.put("record_time", System.currentTimeMillis());
        db.insert(MainActivity.TABLE_NAME, null, cv);
    }
}
