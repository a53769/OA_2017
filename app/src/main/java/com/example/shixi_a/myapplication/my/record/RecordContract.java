package com.example.shixi_a.myapplication.my.record;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;
import com.example.shixi_a.myapplication.bean.Attendance;

import java.util.List;

/**
 * Created by a5376 on 2017/7/7.
 */

public interface RecordContract {

    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(final boolean active);

        void showRecords(List<Attendance> attendances);
    }
    interface Presenter extends BasePresenter {

    }
}
