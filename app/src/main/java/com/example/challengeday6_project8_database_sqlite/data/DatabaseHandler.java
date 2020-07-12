package com.example.challengeday6_project8_database_sqlite.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.challengeday6_project8_database_sqlite.R;
import com.example.challengeday6_project8_database_sqlite.util.Util;

public class DatabaseHandler extends SQLiteOpenHelper {


    public DatabaseHandler(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //create table sql command
        String CREATE_TABLE = "CREATE TABLE "+Util.DB_TABLE_NAME+"("
                +Util.KEY_ID + " INTEGER PRIMARY KEY,"
                +Util.KEY_NAME + " TEXT,"
                +Util.KEY_CONTACT_NUMBER  +" TEXT"+")";

        //create table
        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop table command
        String DROP_TABLE = String.valueOf(R.string.drop_table);

        //drop table
        sqLiteDatabase.execSQL(DROP_TABLE,new String[]{Util.DB_TABLE_NAME});

        //recreate table
        onCreate(sqLiteDatabase);
    }
}
