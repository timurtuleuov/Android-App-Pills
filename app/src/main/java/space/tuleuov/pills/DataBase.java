package space.tuleuov.pills;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DataBase extends SQLiteOpenHelper {
    private static final String DB_NAME = "drugs.db";
    private static final int DB_VERSION = 1;

    public DataBase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS drugs(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, dose TEXT, hour INTEGER, minute INTEGER)";
        db.execSQL(sql);
    }

    public int deleteDrug(int drugId) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("drugs", "id = ?", new String[]{String.valueOf(drugId)});
    }
    public int updateDrug(Drug drug, String newName, String newDose, int newHour, int newMinute) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", newName);
        values.put("dose", newDose);
        values.put("hour", newHour);
        values.put("minute", newMinute);

        return db.update("drugs", values, "id=?", new String[]{String.valueOf(drug.getId())});
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS drugs;");
        onCreate(db);
    }
}
