package com.example.shixi_a.myapplication.model.contact;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.shixi_a.myapplication.model.BaseModel;

/**
 * Created by a5376 on 2017/6/15.
 */

public class ContactRepository extends BaseModel implements ContactDataSource {
    @Override
    public void getContacts(Context context, GsonResponseHandler callback) {

        initRequest(context, "users");


        sendPostRequest(context, BASEURL + GETUPARTANDUSERLIST, params, callback);
    }
}
