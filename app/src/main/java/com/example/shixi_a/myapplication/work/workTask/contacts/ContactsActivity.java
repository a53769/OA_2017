package com.example.shixi_a.myapplication.work.workTask.contacts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.util.ActivityUtils;
import com.example.shixi_a.myapplication.model.contact.ContactRepository;

/**
 * Created by a5376 on 2017/6/15.
 */

public class ContactsActivity extends AppCompatActivity {

    public static final int REQUEST_CONTACTS_CODE = 1;

    private ContactRepository mRepository;

    private ContactsPresenter mPresnter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        ContactsFragment contactsFragment = (ContactsFragment) getSupportFragmentManager().findFragmentById(R.id.contactsFrame);
        if(contactsFragment == null){
            contactsFragment = new ContactsFragment().newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), contactsFragment, R.id.contactsFrame);
        }

        mRepository = new ContactRepository();
        mPresnter = new ContactsPresenter(mRepository, contactsFragment, getApplicationContext());

    }



}
