package com.example.shixi_a.myapplication.work.workAdministration.trafficTool;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;

import java.util.Map;

/**
 * Created by a5376 on 2017/7/28.
 */

public interface TrafficToolContract {
    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<Presenter> {

        void showTypes(Map<String, String> valueMap);
    }
}
