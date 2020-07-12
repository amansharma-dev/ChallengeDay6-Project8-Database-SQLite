package com.example.challengeday6_project8_database_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.challengeday6_project8_database_sqlite.data.DatabaseHandler;
import com.example.challengeday6_project8_database_sqlite.model.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instantiate our database handler
        DatabaseHandler databaseHandler = new DatabaseHandler(this);


        Contact contact2 = databaseHandler.readContact(2);
        contact2.setName("successful update 222222222222");
        contact2.setContactNumber("2222222222");
       // databaseHandler.updateContact(contact3);
     //   int contactUser2 = databaseHandler.updateContact(contact2);
     //   Log.d(TAG, "onCreate: ROW ID IS::"+contactUser2);
        //Log.d(TAG, "onCreate: Contact 4 is ::"+contact4.getId());

        //databaseHandler.deleteContact(contact4);


        //add new entries
        Contact contactU1 = new Contact();
        contactU1.setName("U1");
        contactU1.setContactNumber("000011111111");
        //databaseHandler.addContact(contactU1);

        //read single entry
        Contact readU1 = databaseHandler.readContact(4);
        //Log.d(TAG, "onCreate: User at Row 4::::"+readU1.getContactNumber());
        //update U1
        readU1.setName("new new U1");
        readU1.setContactNumber("111000111");
//        int updatedU1 = databaseHandler.updateContact(readU1);
//        Log.d(TAG, "onCreate: UPDATED U1 "+updatedU1);


        //delete
        //databaseHandler.deleteContact(readU1);

        //Read/Get All Contact/Item/Entries
        List<Contact> contactList =  databaseHandler.readAllContacts();
        for (Contact contact : contactList){
            Log.d(TAG, "onCreate: Read All Contact, Id::"+contact.getId()+", NAME::"+contact.getName() );
        }
    }
}
