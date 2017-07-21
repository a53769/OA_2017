package com.example.shixi_a.myapplication.model.message;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.shixi_a.myapplication.bean.Message;
import com.example.shixi_a.myapplication.bean.RowsNoPage;
import com.example.shixi_a.myapplication.model.BaseModel;

/**
 * Created by a5376 on 2017/7/11.
 */

public class MessageRepository extends BaseModel implements MessageDataSource {

    @Override
    public void getMessage(Context context, GsonResponseHandler<RowsNoPage<Message>> callback) {
        InitRequest(context,"rows");

        params.put("admin_id","-1");
        params.put("is_deal","0");
        params.put("remind_time_s","");
        params.put("remind_time_e","");
        params.put("pn","25");
        params.put("np","1");
        params.put("sort","1");

        sendPostRequest(context,BASE + GETMESSAGELIST,params,callback );
    }
}
