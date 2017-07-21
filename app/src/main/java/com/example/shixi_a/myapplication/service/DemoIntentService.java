package com.example.shixi_a.myapplication.service;

import android.content.Context;
import android.util.Log;

import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTTransmitMessage;

/**
 * Created by a5376 on 2017/7/4.
 */


/**
 * 继承 GTIntentService 接收来自个推的消息, 所有消息在线程中回调, 如果注册了该服务, 则务必要在 AndroidManifest中声明, 否则无法接受消息
 * onReceiveMessageData 处理透传消息
 * onReceiveClientId 接收 cid
 * onReceiveOnlineState cid 离线上线通知
 * onReceiveCommandResult 各种事件处理回执
 */

public class DemoIntentService extends GTIntentService {
    @Override
    public void onReceiveServicePid(Context context, int pid) {

    }

    @Override
    public void onReceiveClientId(Context context, String clientid) {
        Log.e(TAG, "onReceiveClientId -> " + "clientid = " + clientid);
    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage gtTransmitMessage) {

    }

    @Override
    public void onReceiveOnlineState(Context context, boolean online) {

    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage gtCmdMessage) {

    }
}
