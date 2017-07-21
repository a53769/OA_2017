package com.example.shixi_a.myapplication.linkMan;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.shixi_a.myapplication.bean.RowsNoPage;
import com.example.shixi_a.myapplication.bean.User;
import com.example.shixi_a.myapplication.model.user.UserRepository;
import com.example.shixi_a.myapplication.util.LogUtils;
import com.example.shixi_a.myapplication.util.ToastUtils;

import java.util.List;

/**
 * Created by a5376 on 2017/7/21.
 */

public class LinkManPresenter implements LinkManContract.Presenter{
    private UserRepository mRepository;
    private LinkManFragment mLinkManView;
    private Context context;

    public LinkManPresenter(UserRepository repository, LinkManFragment linkManFragment, Context context) {
        mRepository = repository;
        mLinkManView = linkManFragment;
        mLinkManView.setPresenter(this);
        this.context = context;
    }

    @Override
    public void start() {
        loadUsers();
        mLinkManView.setLoadingIndicator(false);
    }

    private void loadUsers() {
        mRepository.getUsers(context, new GsonResponseHandler<RowsNoPage<User>>() {
            @Override
            public void onSuccess(int statusCode, RowsNoPage<User> response) {
                List<User> users = response.rows;
                processUsers(users);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                LogUtils.v("加载失败");
                ToastUtils.showShort(context,"加载列表失败");
            }
        });
    }

    private void processUsers(List<User> users) {
        mLinkManView.showUsers(users);
    }
}
