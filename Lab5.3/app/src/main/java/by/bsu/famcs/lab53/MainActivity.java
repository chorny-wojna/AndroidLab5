package by.bsu.famcs.lab53;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Scroller;

public class MainActivity extends Activity implements OnClickListener {

    Button btnAll, btnCount, btnSum, btnMax, btnMin, btnPopulation, btnRegion, btnRegions,
        btnSortByName, btnSortByPopulation, btnSortByRegion;
    EditText etResult, etPopulation, etRegion, etRegions;
    DBHelper dbHelper;
    static final String TABLE_NAME = "BELARUS_DISTRICTS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAll = (Button) findViewById(R.id.btnAll);
        btnAll.setOnClickListener(this);
        btnCount = (Button) findViewById(R.id.btnCount);
        btnCount.setOnClickListener(this);
        btnSum = (Button) findViewById(R.id.btnSum);
        btnSum.setOnClickListener(this);
        btnMax = (Button) findViewById(R.id.btnMax);
        btnMax.setOnClickListener(this);
        btnMin = (Button) findViewById(R.id.btnMin);
        btnMin.setOnClickListener(this);
        btnPopulation = (Button) findViewById(R.id.btnPopulation);
        btnPopulation.setOnClickListener(this);
        btnRegion = (Button) findViewById(R.id.btnRegion);
        btnRegion.setOnClickListener(this);
        btnRegions = (Button) findViewById(R.id.btnRegions);
        btnRegions.setOnClickListener(this);

        btnSortByName = (Button) findViewById(R.id.btnSortByName);
        btnSortByName.setOnClickListener(this);
        btnSortByPopulation = (Button) findViewById(R.id.btnSortByPopulation);
        btnSortByPopulation.setOnClickListener(this);
        btnSortByRegion = (Button) findViewById(R.id.btnSortByRegion);
        btnSortByRegion.setOnClickListener(this);

        etResult = (EditText) findViewById(R.id.etResult);
        etPopulation = (EditText) findViewById(R.id.etPopulation);
        etRegion = (EditText) findViewById(R.id.etRegion);
        etRegions = (EditText) findViewById(R.id.etRegions);
        dbHelper = new DBHelper(this);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);

        ContentValues cv = new ContentValues();
        cv.put("district", "baranavicy");
        cv.put("population", 170000);
        cv.put("region_code", 1);
        db.insert(TABLE_NAME, null, cv);
        cv.put("district", "biarosa");
        cv.put("population", 63700);
        cv.put("region_code", 1);
        db.insert(TABLE_NAME, null, cv);
        cv.put("district", "ivacevicy");
        cv.put("population", 55000);
        cv.put("region_code", 1);
        db.insert(TABLE_NAME, null, cv);

        cv.put("district", "braslau");
        cv.put("population", 26000);
        cv.put("region_code", 2);
        db.insert(TABLE_NAME, null, cv);
        cv.put("district", "polack");
        cv.put("population", 108000);
        cv.put("region_code", 2);
        db.insert(TABLE_NAME, null, cv);
        cv.put("district", "vicebsk");
        cv.put("population", 400000);
        cv.put("region_code", 2);
        db.insert(TABLE_NAME, null, cv);
        cv.put("district", "vorsha");
        cv.put("population", 156000);
        cv.put("region_code", 2);
        db.insert(TABLE_NAME, null, cv);

        cv.put("district", "homel");
        cv.put("population", 600000 );
        cv.put("region_code", 3);
        db.insert(TABLE_NAME, null, cv);
        cv.put("district", "mazyr");
        cv.put("population", 132000);
        cv.put("region_code", 3);
        db.insert(TABLE_NAME, null, cv);
        cv.put("district", "zlobin");
        cv.put("population", 102000);
        cv.put("region_code", 3);
        db.insert(TABLE_NAME, null, cv);

        cv.put("district", "hrodna");
        cv.put("population", 420000);
        cv.put("region_code", 4);
        db.insert(TABLE_NAME, null, cv);
        cv.put("district", "svislac");
        cv.put("population", 16000);
        cv.put("region_code", 4);
        db.insert(TABLE_NAME, null, cv);
        cv.put("district", "slonim");
        cv.put("population", 65000);
        cv.put("region_code", 4);
        db.insert(TABLE_NAME, null, cv);

        cv.put("district", "cerven");
        cv.put("population", 32000);
        cv.put("region_code", 5);
        db.insert(TABLE_NAME, null, cv);
        cv.put("district", "salihorsk");
        cv.put("population", 134000);
        cv.put("region_code", 5);
        db.insert(TABLE_NAME, null, cv);
        cv.put("district", "niasviz");
        cv.put("population", 39000);
        cv.put("region_code", 5);
        db.insert(TABLE_NAME, null, cv);

        cv.put("district", "mahiliou");
        cv.put("population", 420000);
        cv.put("region_code", 6);
        db.insert(TABLE_NAME, null, cv);
        cv.put("district", "asipovicy");
        cv.put("population", 48000);
        cv.put("region_code", 6);
        db.insert(TABLE_NAME, null, cv);
        cv.put("district", "byhau");
        cv.put("population", 30000);
        cv.put("region_code", 6);
        db.insert(TABLE_NAME, null, cv);

        cv.put("district", "savecki");
        cv.put("population", 161000);
        cv.put("region_code", 7);
        db.insert(TABLE_NAME, null, cv);
        cv.put("district", "cantralny");
        cv.put("population", 108000);
        cv.put("region_code", 7);
        db.insert(TABLE_NAME, null, cv);
        cv.put("district", "partyzanski");
        cv.put("population", 98000);
        cv.put("region_code", 7);
        db.insert(TABLE_NAME, null, cv);

        dbHelper.close();
    }

    @Override
    public void onClick(View v) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        etResult.setText("");
        long result;

        switch (v.getId()) {
            case R.id.btnAll:
                Cursor c = db.query(MainActivity.TABLE_NAME, null, null, null, null, null, null);
                if (c.moveToFirst()) {
                    int idColIndex = c.getColumnIndex("id");
                    int districtColIndex = c.getColumnIndex("district");
                    int populationColIndex = c.getColumnIndex("population");
                    int regionCodeColIndex = c.getColumnIndex("region_code");
                    do {
                        etResult.setText(etResult.getText().toString()
                                + "ID = " + c.getInt(idColIndex) +
                                ", district = " + c.getString(districtColIndex) +
                                ", population = " + c.getInt(populationColIndex) +
                                ", region_code = " + c.getInt(regionCodeColIndex) + "\n");
                    } while (c.moveToNext());
                } else
                    etResult.setText("0 rows");
                c.close();
                break;
            case R.id.btnCount:
                result = DatabaseUtils.queryNumEntries(db, TABLE_NAME);
                etResult.setText("Number of rows = " + result);
                break;
            case R.id.btnSum:
                c = db.rawQuery("select sum(population) from "+ TABLE_NAME + ";", null);
                if(c.moveToFirst())
                    result = c.getInt(0);
                else
                    result = 0;
                etResult.setText("Population sum = " + result);
                c.close();
                break;
            case R.id.btnMax:
                c = db.rawQuery("select max(population) from "+ TABLE_NAME + ";", null);
                if(c.moveToFirst())
                    result = c.getInt(0);
                else
                    result = 0;
                etResult.setText("Population max = " + result);
                c.close();
                break;
            case R.id.btnMin:
                c = db.rawQuery("select min(population) from "+ TABLE_NAME + ";", null);
                if(c.moveToFirst())
                    result = c.getInt(0);
                else
                    result = 0;
                etResult.setText("Population min = " + result);
                c.close();
                break;
            case R.id.btnPopulation:
                if (etPopulation.getText().length() == 0) {
                    break;
                }
                c = db.rawQuery("select * from "+ TABLE_NAME + " where population > "
                        + etPopulation.getText() + ";", null);
                if (c.moveToFirst()) {
                    int idColIndex = c.getColumnIndex("id");
                    int districtColIndex = c.getColumnIndex("district");
                    int populationColIndex = c.getColumnIndex("population");
                    int regionCodeColIndex = c.getColumnIndex("region_code");
                    do {
                        etResult.setText(etResult.getText().toString()
                                + "ID = " + c.getInt(idColIndex) +
                                ", district = " + c.getString(districtColIndex) +
                                ", population = " + c.getInt(populationColIndex) +
                                ", region_code = " + c.getInt(regionCodeColIndex) + "\n");
                    } while (c.moveToNext());
                } else
                    etResult.setText("0 rows");
                c.close();
                break;
            case R.id.btnRegion:
                if (etRegion.getText().length() != 1) {
                    break;
                }
                c = db.rawQuery("select * from "+ TABLE_NAME + " where region_code = "
                        + etRegion.getText() + ";", null);
                if (c.moveToFirst()) {
                    int idColIndex = c.getColumnIndex("id");
                    int districtColIndex = c.getColumnIndex("district");
                    int populationColIndex = c.getColumnIndex("population");
                    int regionCodeColIndex = c.getColumnIndex("region_code");
                    do {
                        etResult.setText(etResult.getText().toString()
                                + "ID = " + c.getInt(idColIndex) +
                                ", district = " + c.getString(districtColIndex) +
                                ", population = " + c.getInt(populationColIndex) +
                                ", region_code = " + c.getInt(regionCodeColIndex) + "\n");
                    } while (c.moveToNext());
                } else
                    etResult.setText("0 rows");
                c.close();
                break;
            case R.id.btnRegions:
                if (etRegions.getText().length() == 0) {
                    break;
                }
                etResult.setText("Regions: ");
                for (int i = 1; i <= 7; i++) {
                    c = db.rawQuery("select sum(population) from " + TABLE_NAME
                            + " where region_code = " + i + ";", null);
                    if (c.moveToFirst())
                        result = c.getInt(0);
                    else
                        result = 0;
                    if (result > Integer.parseInt(etRegions.getText().toString())) {
                        etResult.setText(etResult.getText().toString() + i + " ");
                    }
                    c.close();
                }
                break;
            case R.id.btnSortByName:
                c = db.query(TABLE_NAME, null, null, null, null, null, "district DESC");
                if (c.moveToFirst()) {
                    int idColIndex = c.getColumnIndex("id");
                    int districtColIndex = c.getColumnIndex("district");
                    int populationColIndex = c.getColumnIndex("population");
                    int regionCodeColIndex = c.getColumnIndex("region_code");
                    do {
                        etResult.setText(etResult.getText().toString()
                                + "ID = " + c.getInt(idColIndex) +
                                ", district = " + c.getString(districtColIndex) +
                                ", population = " + c.getInt(populationColIndex) +
                                ", region_code = " + c.getInt(regionCodeColIndex) + "\n");
                    } while (c.moveToNext());
                } else
                    etResult.setText("0 rows");
                c.close();
                break;
            case R.id.btnSortByPopulation:
                c = db.query(TABLE_NAME, null, null, null, null, null, "population DESC");
                if (c.moveToFirst()) {
                    int idColIndex = c.getColumnIndex("id");
                    int districtColIndex = c.getColumnIndex("district");
                    int populationColIndex = c.getColumnIndex("population");
                    int regionCodeColIndex = c.getColumnIndex("region_code");
                    do {
                        etResult.setText(etResult.getText().toString()
                                + "ID = " + c.getInt(idColIndex) +
                                ", district = " + c.getString(districtColIndex) +
                                ", population = " + c.getInt(populationColIndex) +
                                ", region_code = " + c.getInt(regionCodeColIndex) + "\n");
                    } while (c.moveToNext());
                } else
                    etResult.setText("0 rows");
                c.close();
                break;
            case R.id.btnSortByRegion:
                c = db.query(TABLE_NAME, null, null, null, null, null, "region_code DESC");
                if (c.moveToFirst()) {
                    int idColIndex = c.getColumnIndex("id");
                    int districtColIndex = c.getColumnIndex("district");
                    int populationColIndex = c.getColumnIndex("population");
                    int regionCodeColIndex = c.getColumnIndex("region_code");
                    do {
                        etResult.setText(etResult.getText().toString()
                                + "ID = " + c.getInt(idColIndex) +
                                ", district = " + c.getString(districtColIndex) +
                                ", population = " + c.getInt(populationColIndex) +
                                ", region_code = " + c.getInt(regionCodeColIndex) + "\n");
                    } while (c.moveToNext());
                } else
                    etResult.setText("0 rows");
                c.close();
                break;
        }
        dbHelper.close();
    }
}
