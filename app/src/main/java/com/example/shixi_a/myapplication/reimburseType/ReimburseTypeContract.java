package com.example.shixi_a.myapplication.reimburseType;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;

import java.util.Map;

/**
 * Created by a5376 on 2017/7/24.
 */

public interface ReimburseTypeContract {

    interface View extends BaseView<Presenter> {

        void showTypes(Map<String, String> valueMap);
    }
    interface Presenter extends BasePresenter {

    }
}
