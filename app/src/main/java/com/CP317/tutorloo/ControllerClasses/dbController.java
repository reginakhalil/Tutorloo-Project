package com.CP317.tutorloo.ControllerClasses;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.SQLException;
//import android.database.sqlite.SQLiteDatabase;
//import android.util.Log;
//
//import java.io.IOException;
//import java.sql.Date;
//
//public class dbController {
//    protected static final String TAG = "DataAdapter";
//
//    private final Context mContext;
//    private SQLiteDatabase mDb;
//    private DatabaseHelper mDbHelper;
//
//    public dbController(Context context) {
//        this.mContext = context;
//        mDbHelper = new DatabaseHelper(mContext);
//    }
//
//    public dbController createDatabase() throws SQLException {
//        try {
//            mDbHelper.createDataBase();
//        } catch (IOException mIOException) {
//            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
//            throw new Error("UnableToCreateDatabase");
//        }
//        return this;
//    }
//
//    public dbController open() throws SQLException {
//        try {
//            mDbHelper.openDataBase();
//            mDbHelper.close();
//            mDb = mDbHelper.getReadableDatabase();
//        } catch (SQLException mSQLException) {
//            Log.e(TAG, "open >>"+ mSQLException.toString());
//            throw mSQLException;
//        }
//        return this;
//    }
//
//    public void close() {
//        mDbHelper.close();
//    }
//
//
//    public boolean insertStudent(String firstName, String lastName, String email, String password, Date dateOfBirth){
//        mDb = mDbHelper.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("first_name", firstName);
//        contentValues.put("last_name", lastName);
//        contentValues.put("email", email);
//        contentValues.put("Date_of_Birth", String.valueOf(dateOfBirth));
//
//
//        //Password encryption goes here? also student id gen
//        contentValues.put("encrypt_pass", password);
//        long result = mDb.insert("student",null, contentValues);
//        if (result == 1){
//            return false;
//        }
//        else{
//            return true;
//        }
//
//    }
//    public Cursor getTestData() {
//        try {
//            String sql ="SELECT * FROM myTable";
//            Cursor mCur = mDb.rawQuery(sql, null);
//            if (mCur != null) {
//                mCur.moveToNext();
//            }
//            return mCur;
//        } catch (SQLException mSQLException) {
//            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
//            throw mSQLException;
//        }
//    }
//}

