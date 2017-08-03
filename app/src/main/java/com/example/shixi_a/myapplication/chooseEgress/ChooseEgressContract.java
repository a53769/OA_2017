package com.example.shixi_a.myapplication.chooseEgress;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;
import com.example.shixi_a.myapplication.bean.Egress;

import java.util.List;

/**
 * Created by a5376 on 2017/7/28.
 */

public interface ChooseEgressContract {
    interface Presenter extends BasePresenter {

        void selectedEgress(String id, String address, String s);
    }
    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(final boolean active);
        void showEgresses(List<Egress> egresses);

        void showTrafficReimmburse(String id, String addr, String s);

        void showEntertainmentReimburse(String id, String address, String s);
    }
}
