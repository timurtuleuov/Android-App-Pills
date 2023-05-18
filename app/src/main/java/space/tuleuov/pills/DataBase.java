package space.tuleuov.pills;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS drugs;");
        onCreate(db);
    }
}
