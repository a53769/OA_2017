package com.example.shixi_a.myapplication.model.assist;

import android.content.Context;

import com.example.myokhttp.response.JsonResponseHandler;
import com.example.myokhttp.response.RawResponseHandler;
import com.example.myokhttp.util.RequestParams;
import com.example.shixi_a.myapplication.GlobalApp;
import com.example.shixi_a.myapplication.model.BaseModel;
import com.igexin.sdk.PushManager;

/**
 * Created by a5376 on 2017/7/6.
 */

public class AssistRepository extends BaseModel implements AssistDataSource {

    @Override
    public void uploadToken(Context context, RawResponseHandler callback) {
        String token = GlobalApp.getInstance().getVathome_token();

        params = new RequestParams();

        params.put("MYIDCODE", token);
        params.put("token", PushManager.getInstance().getClientid(context));

        sendPostRequest(context,BASE + UPLOADTOKEN ,params,callback);
    }

    @Override
    public void getLocation(Context context, String latitude, String longitude, RawResponseHandler jsonResponseHandler) {
        params = new RequestParams();
        String url = "http://api.map.baidu.com/geocoder/v2/?output=json&ak=iYgosUpwQYpyYU8odmR3Z4U00DdbOCgE&pois=0&location="+latitude+","+longitude;

//        String url =  "http://api.map.baidu.com/geocoder/v2/?ak=iYgosUpwQYpyYU8odmR3Z4U00DdbOCgE&callback=renderReverse&location="
//                +latitude+","+longitude+"&output=json&pois=0";

        sendPostRequest(context,url,params,jsonResponseHandler);
    }

    @Override
    public void sendQRCode(Context context, String result, JsonResponseHandler jsonResponseHandler) {
        params = new RequestParams();

        String token = GlobalApp.getInstance().getVathome_token();
        params.put("opt","qrcode");
        params.put("token",token);
        params.put("qrcode",result);

        sendPostRequest(context,BASE + QRCODE,params,jsonResponseHandler);
    }
}
