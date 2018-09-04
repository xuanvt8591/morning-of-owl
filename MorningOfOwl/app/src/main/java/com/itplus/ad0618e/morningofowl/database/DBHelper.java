package com.itplus.ad0618e.morningofowl.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "MovieOnlineManager.db";

    // Constructor
    public DBHelper(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("DBHelper", "DBHelper: ");
        this.context = context;
    }

    // Table
    private static final String TABLE_MOVIE = "movie";
    private static final String TABLE_USER = "user";

    // MOVIE Table Column
    //private static final int COLUMN_MOVIE_ID = 1;
    private static final String COLUMN_MOVIE_ID = "movieID";
    private static final String COLUMN_MOVIE_NAME = "movieName";
    private static final String COLUMN_MOVIE_SUMMARY = "movieSummary";
    private static final String COLUMN_MOVIE_LINK = "movieLink";
    private static final String COLUMN_MOVIE_POSTER = "moviePoster";
    private static final String COLUMN_MOVIE_ACTORS = "movieActors";
    private static final String COLUMN_MOVIE_LATEST_UPDATE = "movieLatestUpdate";
    private static final String COLUMN_MOVIE_VIEWS = "movieViews";
    private static final String COLUMN_MOVIE_RATING = "movieRating";

    // USER Table Column
    private static final String COLUMN_USER_NAME = "userName";
    private static final String COLUMN_USER_PASS = "userPass";
    private static final String COLUMN_USER_VIEW_LATER = "userViewLater";

    // Create MOVIE table query
    private static final String CREATE_MOVIE_TABLE = "CREATE TABLE " + TABLE_MOVIE + "("
            + COLUMN_MOVIE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_MOVIE_NAME + " TEXT,"
            + COLUMN_MOVIE_SUMMARY + " TEXT,"
            + COLUMN_MOVIE_LINK + " TEXT,"
            + COLUMN_MOVIE_POSTER + " TEXT,"
            + COLUMN_MOVIE_ACTORS + " TEXT,"
            + COLUMN_MOVIE_LATEST_UPDATE + " TEXT,"
            + COLUMN_MOVIE_VIEWS + " INTEGER,"
            + COLUMN_MOVIE_RATING + " FLOAT" + ")";

    // Create USER table query
    private static final String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_NAME + " TEXT PRIMARY KEY,"
            + COLUMN_USER_PASS + " TEXT,"
            + COLUMN_USER_VIEW_LATER + " BOOLEAN" + ")";

    // Drop MOVIE table query
    private static final String DROP_MOVIE_TABLE = "DROP TABLE IF EXISTS " + TABLE_MOVIE;

    // Drop USER table query
    private static final String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MOVIE_TABLE);
        db.execSQL(CREATE_USER_TABLE);
        Toast.makeText(context, "Create table successfully!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop MOVIE Table if exist
        db.execSQL(DROP_MOVIE_TABLE);
        db.execSQL(DROP_USER_TABLE);
        Toast.makeText(context, "Drop table successfully!", Toast.LENGTH_SHORT).show();

        // Create table again
        onCreate(db);
    }


}
