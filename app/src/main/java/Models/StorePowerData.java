package Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by nitesh on 28/02/17.
 */

public class StorePowerData extends SQLiteOpenHelper {
    public static class Attributes implements BaseColumns {
        public static final String TABLE_NAME = "UserAccount";
        static final String ID = "ID";
        public static final String USERNAME = "USERNAME";
        public static final String PASSWORD = "PASSWORD";
    }
    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "StorePowerData.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Attributes.TABLE_NAME + " (" +
                    Attributes.ID + " INTEGER PRIMARY KEY," +
                    Attributes.USERNAME + " TEXT," +
                    Attributes.PASSWORD + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Attributes.TABLE_NAME;

    public StorePowerData(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
