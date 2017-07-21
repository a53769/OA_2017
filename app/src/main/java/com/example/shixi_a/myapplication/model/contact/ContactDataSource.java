package com.example.shixi_a.myapplication.model.contact;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;

/**
 * Created by a5376 on 2017/6/15.
 */

public interface ContactDataSource {
    void getContacts(Context context, GsonResponseHandler callback);
}
