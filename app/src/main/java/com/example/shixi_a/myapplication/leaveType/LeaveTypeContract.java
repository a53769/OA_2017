package com.example.shixi_a.myapplication.leaveType;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;

import java.util.Map;

/**
 * Created by a5376 on 2017/7/19.
 */

public interface LeaveTypeContract {
    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<Presenter> {

        void showTypes(Map<String, String> valueMap);
    }
}
