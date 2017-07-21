package com.example.myokhttp.util;

import okhttp3.FormBody;


/**
 * Created by Shixi-A on 2017/6/2.
 */

public class RequestParams {

    private FormBody.Builder builder;

    public RequestParams()
    {
        builder = new FormBody.Builder();
    }

    public void put(String key, String value)
    {
        builder.add(key, value);

    }


    public FormBody toParams()
    {
        return builder.build();
    }

    public String getParams(){

        return builder.toString();
    }
    
}
