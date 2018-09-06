package com.itplus.ad0618e.morningofowl.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.itplus.ad0618e.morningofowl.models.Movie;
import com.itplus.ad0618e.morningofowl.models.User;
import com.itplus.ad0618e.morningofowl.models.UserMovie;

public class DatabaseManager extends SQLiteOpenHelper {
    private Context context;

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "MovieOnlineManager.db";

    /* ----------------- Constructor ----------------- */
    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    /* ----------------- Table ----------------- */
    private static final String TABLE_MOVIE = "movie";
    private static final String TABLE_USER = "user";
    private static final String TABLE_USER_MOVIE = "userMovie";

    /* ----------------- Column ----------------- */
    // Movie Table Column
    private static final String COLUMN_MOVIE_ID = "movieID";
    private static final String COLUMN_MOVIE_NAME = "movieName";
    private static final String COLUMN_MOVIE_SUMMARY = "movieSummary";
    private static final String COLUMN_MOVIE_LINK = "movieLink";
    private static final String COLUMN_MOVIE_POSTER = "moviePoster";
    private static final String COLUMN_MOVIE_ACTORS = "movieActors";
    private static final String COLUMN_MOVIE_LATEST_UPDATE = "movieLatestUpdate";
    private static final String COLUMN_MOVIE_VIEWS = "movieViews";
    private static final String COLUMN_MOVIE_RATING = "movieRating";

    // User Table Column
    private static final String COLUMN_USER_ID = "userID";
    private static final String COLUMN_USER_PASS = "userPass";

    // UserMovie Table Column
    private static final String COLUMN_USER_MOVIE_ID = "userMovieID";
    private static final String COLUMN_VIEW_LATER = "viewLater";

    /* ----------------- Create, Drop table query ----------------- */
    // Create Movie
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

    // Create User
    private static final String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " TEXT PRIMARY KEY,"
            + COLUMN_USER_PASS + " TEXT" + ")";

    // Create UserMovie
    private static final String CREATE_USER_MOVIE_TABLE = "CREATE TABLE " + TABLE_USER_MOVIE + "("
            + COLUMN_USER_MOVIE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_MOVIE_ID + " INTEGER,"
            + COLUMN_USER_ID + " TEXT,"
            + COLUMN_VIEW_LATER + " BOOLEAN" + ")";

    // Drop Movie
    private static final String DROP_MOVIE_TABLE = "DROP TABLE IF EXISTS " + TABLE_MOVIE;

    // Drop User
    private static final String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    // Drop UserMovie
    private static final String DROP_USER_MOVIE_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER_MOVIE;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MOVIE_TABLE);
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_USER_MOVIE_TABLE);

        Toast.makeText(context, "Create table successfully!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop MOVIE Table if exist
        db.execSQL(DROP_MOVIE_TABLE);
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_USER_MOVIE_TABLE);

        Toast.makeText(context, "Drop table successfully!", Toast.LENGTH_SHORT).show();

        // Create table again
        onCreate(db);
    }

    /* ----------------- Movie Table Method ----------------- */
    // SELECT ONE
    public Movie selectMovie(int movieID) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_MOVIE + " WHERE " + COLUMN_MOVIE_ID + " = " + movieID;

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Movie mv = new Movie();
        mv.setMovieID(c.getInt(c.getColumnIndex(COLUMN_MOVIE_ID)));
        mv.setMovieName(c.getString(c.getColumnIndex(COLUMN_MOVIE_NAME)));
        mv.setMovieSummary(c.getString(c.getColumnIndex(COLUMN_MOVIE_SUMMARY)));
        mv.setMovieLink(c.getString(c.getColumnIndex(COLUMN_MOVIE_LINK)));
        mv.setMoviePoster(c.getString(c.getColumnIndex(COLUMN_MOVIE_POSTER)));
        mv.setMovieActors(c.getString(c.getColumnIndex(COLUMN_MOVIE_ACTORS)));
        mv.setMovieLatestUpdate(c.getString(c.getColumnIndex(COLUMN_MOVIE_LATEST_UPDATE)));
        mv.setMovieViews(c.getInt(c.getColumnIndex(COLUMN_MOVIE_VIEWS)));
        mv.setMovieRating(c.getFloat(c.getColumnIndex(COLUMN_MOVIE_RATING)));

        return mv;
    }

    // INSERT
    public void insertMovie(String movieName, String movieSummary, String movieLink, String moviePoster, String movieActors, String movieLatestUpdate, int movieViews, float movieRating) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_MOVIE_NAME, movieName);
        values.put(COLUMN_MOVIE_SUMMARY, movieSummary);
        values.put(COLUMN_MOVIE_LINK, movieLink);
        values.put(COLUMN_MOVIE_POSTER, moviePoster);
        values.put(COLUMN_MOVIE_ACTORS, movieActors);
        values.put(COLUMN_MOVIE_LATEST_UPDATE, movieLatestUpdate);
        values.put(COLUMN_MOVIE_VIEWS, movieViews);
        values.put(COLUMN_MOVIE_RATING, movieRating);

        long id = db.insertWithOnConflict(TABLE_USER, null, values, SQLiteDatabase.CONFLICT_IGNORE);
    }

    /* ----------------- User Table Method ----------------- */
    // SELECT ONE
    public User selectUser(String userID) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_USER + " WHERE " + COLUMN_USER_ID + " = " + userID;

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        User us = new User();
        us.setUserID(c.getString(c.getColumnIndex(COLUMN_USER_ID)));
        us.setUserPass(c.getString(c.getColumnIndex(COLUMN_USER_PASS)));

        return us;
    }
    // INSERT
    public void insertUser(String userID, String userPass) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, userID);
        values.put(COLUMN_USER_PASS, userPass);

        db.insert(TABLE_USER, null, values);
    }

    /* ----------------- UserMovie Table Method ----------------- */
    // SELECT ALL
    public UserMovie selectMovie(String userID) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_USER_MOVIE + " WHERE " + COLUMN_MOVIE_ID + " = " + userID;

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        UserMovie um = new UserMovie();
        um.setUserMovieID(c.getInt(c.getColumnIndex(COLUMN_USER_MOVIE_ID)));
        um.setUserID(c.getString(c.getColumnIndex(COLUMN_USER_ID)));
        um.setMovieID(c.getInt(c.getColumnIndex(COLUMN_MOVIE_ID)));

        return um;
    }
    // INSERT
    public void insertUserMovie(String userID, String movieID, boolean viewLater) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_USER_ID, userID);
        values.put(COLUMN_MOVIE_ID, movieID);
        values.put(COLUMN_VIEW_LATER, viewLater);

        long id = db.insertWithOnConflict(TABLE_USER_MOVIE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
    }
}
