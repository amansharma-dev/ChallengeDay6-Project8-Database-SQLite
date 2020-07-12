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

        //add user1
        Contact contactUser_1 = new Contact();
        contactUser_1.setName("User 1");
        contactUser_1.setContactNumber("0001");
        //databaseHandler.addContact(contactUser_1);

        //add user2
        Contact contactUser_2 = new Contact();
        contactUser_2.setName("User 2");
        contactUser_2.setContactNumber("0002");
        //databaseHandler.addContact(contactUser_2);

        // add user3
        Contact contactUser_3 = new Contact();
        contactUser_3.setName("User 3");
        contactUser_3.setContactNumber("0003");
        //databaseHandler.addContact(contactUser_3);

        //Read/Get All Contact/Item/Entries
        List<Contact> contactList =  databaseHandler.readAllContacts();
        for (Contact contact : contactList){
            Log.d(TAG, "onCreate: Read All Contact, Id::"+contact.getId()+", Name::"+contact.getName()+", ContactNumber::"+contact.getContactNumber());
        }

        //to get a single entry/item/contact
       // Log.d(TAG, "onCreate: Read Single Contact "+databaseHandler.readContact(1));

    }
}
