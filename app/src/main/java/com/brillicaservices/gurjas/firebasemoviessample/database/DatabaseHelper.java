package com.brillicaservices.gurjas.firebasemoviessample.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import com.brillicaservices.gurjas.firebasemoviessample.movies.MoviesModelView;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    /*
   * Database details*/
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "movies_db";

    /*
    * Student_Record table details*/
    private static final String TABLE_NAME = "movies_record";
    private static final String MOVIES_TITLE = "movies_name";
    private static final String MOVIES_ID = "movies_id";
    private static final String MOVIES_DESCRIPTION = "movies_description";
    private static final String MOVIES_RATING = "movies_phone";
    private static final String MOVIES_RELEASE = "movies_release";
    private static final String MOVIES_IMAGE = "movies_image";


    /*
    * Table structure*/
    private static final String CREATE_TABLE = " CREATE TABLE " + TABLE_NAME + " ( " + MOVIES_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + MOVIES_TITLE + " TEXT, " + MOVIES_DESCRIPTION + " TEXT, " +
            MOVIES_RATING + " INTEGER," + MOVIES_RELEASE + " INTEGER, " +
            MOVIES_IMAGE + " INTEGER ); ";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }




    public long addNew(MoviesModelView moviesModelView) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(MOVIES_TITLE, moviesModelView.getTitle());
        contentValues.put(MOVIES_DESCRIPTION, moviesModelView.getDescription());
        contentValues.put(MOVIES_RATING, moviesModelView.getRating());
        contentValues.put(MOVIES_RELEASE, moviesModelView.getReleaseYear());
        contentValues.put(MOVIES_IMAGE, moviesModelView.getImage());


        long id = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        sqLiteDatabase.close();

        return id;
    }

    public MoviesModelView getSingleStudentDetails(long id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[]{MOVIES_ID, MOVIES_TITLE, MOVIES_DESCRIPTION,
                        MOVIES_RATING, MOVIES_RELEASE, MOVIES_IMAGE}, MOVIES_ID + "=?", new String[]{String.valueOf(id)}, null, null,
                null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        MoviesModelView moviesModelView = new MoviesModelView(cursor.getInt(cursor.getColumnIndex(MOVIES_ID)),
                cursor.getString(cursor.getColumnIndex(MOVIES_TITLE)),  cursor.getString(cursor.getColumnIndex(MOVIES_DESCRIPTION)),
                cursor.getInt(cursor.getColumnIndex(MOVIES_RELEASE)),  cursor.getInt(cursor.getColumnIndex(MOVIES_RATING)),
                cursor.getInt(cursor.getColumnIndex(MOVIES_IMAGE)) );

        cursor.close();

        return moviesModelView;
    }

    public List<MoviesModelView> allStudentsDetails() {
        List<MoviesModelView> modelViewList = new ArrayList<>();

        String selectQuery = " SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                MoviesModelView moviesModelView = new MoviesModelView();
                moviesModelView.setId(cursor.getInt(cursor.getColumnIndex(MOVIES_ID)));
                moviesModelView.setTitle(cursor.getString(cursor.getColumnIndex(MOVIES_TITLE)));
                moviesModelView.setDescription(cursor.getString(cursor.getColumnIndex(MOVIES_DESCRIPTION)));
                moviesModelView.setRating(cursor.getInt(cursor.getColumnIndex(MOVIES_RATING)));
                moviesModelView.setReleaseYear(cursor.getInt(cursor.getColumnIndex(MOVIES_RELEASE)));
                moviesModelView.setImage(cursor.getInt(cursor.getColumnIndex(MOVIES_IMAGE)));

                modelViewList.add(moviesModelView);
            } while (cursor.moveToNext());
        }

        sqLiteDatabase.close();

        return modelViewList;
    }

    public int getStudentsCount() {

        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        int totalStudentsCount = cursor.getCount();
        cursor.close();

        return totalStudentsCount;
    }

    public int updateIndividualStudentDetails(MoviesModelView moviesModelView) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MOVIES_TITLE, moviesModelView.getTitle());
        values.put(MOVIES_DESCRIPTION, moviesModelView.getDescription());
        values.put(MOVIES_RATING, moviesModelView.getRating());
        values.put(MOVIES_RELEASE, moviesModelView.getReleaseYear());
        values.put(MOVIES_IMAGE, moviesModelView.getImage());

        // updating row
        return sqLiteDatabase.update(TABLE_NAME, values, MOVIES_ID + " = ?",
                new String[]{String.valueOf(moviesModelView.getId())});
    }
}


