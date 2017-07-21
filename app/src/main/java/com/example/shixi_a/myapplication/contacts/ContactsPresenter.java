package com.example.shixi_a.myapplication.contacts;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.shixi_a.myapplication.bean.Contact;
import com.example.shixi_a.myapplication.bean.Rows;
import com.example.shixi_a.myapplication.model.contact.ContactRepository;

import java.util.List;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;

/**
 * Created by a5376 on 2017/6/15.
 */

public class ContactsPresenter implements ContactsContract.Presenter {

    private ContactsContract.View mContactsView;

    private ContactRepository mRepository;

    private Context context;

    public ContactsPresenter(@NonNull ContactRepository repository,@NonNull  ContactsContract.View contactsView, Context context){
        mRepository = checkNotNull(repository, "can not be null");
        mContactsView = checkNotNull(contactsView,"can not be null");
        mContactsView.setPresenter(this);
        this.context = context;

    }

    @Override
    public void start() {
        loadContacts();
    }

    @Override
    public void loadContacts() {
        mRepository = new ContactRepository();
        mRepository.getContacts(context, new GsonResponseHandler<Rows<Contact>>() {
            @Override
            public void onSuccess(int statusCode, Rows<Contact> response) {
                List<Contact> contacts;
                contacts = response.rows;
                processContacts(contacts);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mContactsView.showLoadingError();
            }
        });
    }

    private void processContacts(List<Contact> contacts) {
        mContactsView.showContacts(contacts);
    }

}
