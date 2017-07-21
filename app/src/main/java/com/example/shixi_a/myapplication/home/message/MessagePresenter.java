package com.example.shixi_a.myapplication.home.message;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.shixi_a.myapplication.bean.Message;
import com.example.shixi_a.myapplication.bean.RowsNoPage;
import com.example.shixi_a.myapplication.model.message.MessageRepository;
import com.example.shixi_a.myapplication.util.ToastUtils;

import java.util.List;

/**
 * Created by a5376 on 2017/7/11.
 */

public class MessagePresenter implements MessageContract.Presenter {

    private MessageContract.View mMessageView;
    private MessageRepository messageRepository;
    private Context context;


    public MessagePresenter(MessageRepository mRepository, Fragment_message fragment_message, Context context) {

        mMessageView = fragment_message;
        fragment_message.setPresenter(this);
        messageRepository = mRepository;
        this.context = context;
    }

    @Override
    public void start() {
        loadMessage();
        mMessageView.setLoadingIndicator(false);
    }

    private void loadMessage() {
        messageRepository.getMessage(context, new GsonResponseHandler<RowsNoPage<Message>>() {
            @Override
            public void onSuccess(int statusCode, RowsNoPage<Message> response) {
                List<Message> messages;
                messages = response.rows;
                processMessage(messages);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.showShort(context,"加载消息列表失败");
            }
        });
    }
    private void processMessage(List<Message> messages) {
        mMessageView.showMessage(messages);
    }
}
