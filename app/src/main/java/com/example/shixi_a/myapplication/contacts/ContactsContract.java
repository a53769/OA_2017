package com.example.shixi_a.myapplication.contacts;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;
import com.example.shixi_a.myapplication.bean.Contact;

import java.util.List;

/**
 * Created by a5376 on 2017/6/15.
 */

public interface ContactsContract {
    interface View extends BaseView<Presenter> {

        void showContacts(List<Contact> contacts);

        void showLoadingError();

    }
    interface Presenter extends BasePresenter {

        void loadContacts();
    }
}
