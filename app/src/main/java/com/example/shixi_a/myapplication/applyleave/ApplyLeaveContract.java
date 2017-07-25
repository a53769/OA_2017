package com.example.shixi_a.myapplication.applyleave;

import android.content.Intent;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;

/**
 * Created by a5376 on 2017/7/18.
 */

public interface ApplyLeaveContract {
    interface View extends BaseView<Presenter> {

        void showLeaveDetail();

        void InitView(String sort_show, String off_start, String off_end, String need_modify);

        void coverClick();

        void initView(String sort_show, String handover_name);

        void showOffset();

        void hideoffset();

        void initOffset(String off_start, String offset_end, String offset_memo);
    }
    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode, Intent data);

        void submitLeave(String stime, String etime, String is_handle, String reason);
    }
}
