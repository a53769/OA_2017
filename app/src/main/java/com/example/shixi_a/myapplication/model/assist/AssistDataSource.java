package com.example.shixi_a.myapplication.model.assist;

import android.content.Context;

import com.example.myokhttp.response.JsonResponseHandler;
import com.example.myokhttp.response.RawResponseHandler;

/**
 * Created by a5376 on 2017/7/6.
 */

public interface AssistDataSource {
    void uploadToken(Context applicationContext, RawResponseHandler callback);

    void getLocation(Context context, String latitude, String altitude, RawResponseHandler jsonResponseHandler);

    void sendQRCode(Context context, String result, JsonResponseHandler jsonResponseHandler);
}
