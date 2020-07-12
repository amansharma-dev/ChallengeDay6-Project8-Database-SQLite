package com.example.challengeday6_project8_database_sqlite.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.challengeday6_project8_database_sqlite.R;
import com.example.challengeday6_project8_database_sqlite.model.Contact;
import com.example.challengeday6_project8_database_sqlite.util.Util;

import java.util.ArrayList;
import java.util.List;

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


    //CRUD:: create/add item/contact
    public void addContact(Contact contact){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //dont have to add id, it will add and increment automatically
        contentValues.put(Util.KEY_NAME,contact.getName());
        contentValues.put(Util.KEY_CONTACT_NUMBER,contact.getContactNumber());

        sqLiteDatabase.insert(Util.DB_TABLE_NAME,null,contentValues);

        //close db table connection
        sqLiteDatabase.close();
    }


    //CRUD:: read  single item/contact
    public Contact readContact(int id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(Util.DB_TABLE_NAME,
                new String[]{
                        Util.KEY_ID,
                        Util.KEY_NAME,
                        Util.KEY_CONTACT_NUMBER
                },
                Util.KEY_ID+"=?",new String[]{String.valueOf(id)},
                null,null,null);

        if(cursor != null)
            cursor.moveToFirst();
            Contact contact = new Contact();
            contact.setId(Integer.parseInt(cursor.getString(0)));
            contact.setName(cursor.getString(1));
            contact.setContactNumber(cursor.getString(2));

            return contact;
    }


    // CRUD:: read all items/contact
    public List<Contact> readAllContacts(){

        List<Contact> contactList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String SELECT_ALL = " SELECT * FROM "+Util.DB_TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL,null);

        //loop through our data
        if(cursor.moveToFirst()){
            do {
                Contact contact= new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setContactNumber(cursor.getString(2));

                //add contact object to our list
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        return contactList;
    }


    //CRUD:: update item/contact
    public int updateContact(Contact contact){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_NAME,contact.getName());
        contentValues.put(Util.KEY_CONTACT_NUMBER,contact.getContactNumber());


        return sqLiteDatabase.update(Util.DB_TABLE_NAME,contentValues,
                Util.KEY_ID+"=?",new String[]{String.valueOf(contact.getId())});

    }


}
