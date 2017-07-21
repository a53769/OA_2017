package com.example.shixi_a.myapplication.model.message;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.shixi_a.myapplication.bean.Message;
import com.example.shixi_a.myapplication.bean.RowsNoPage;

/**
 * Created by a5376 on 2017/7/11.
 */

public interface MessageDataSource {
    void getMessage(Context context, GsonResponseHandler<RowsNoPage<Message>> gsonResponseHandler);
}
