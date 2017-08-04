package com.example.shixi_a.myapplication.work.workAdministration.egressDetail;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;

/**
 * Created by a5376 on 2017/7/18.
 */

public interface EgressDetailContract {
    interface View extends BaseView<Presenter> {

        void InitView(String adminname, String status_show, String out_time, String username, String addr, String matter);

        void InitCheckView(String event_addr, String event_time, String event_content);

        void setLoadingIndicator(final boolean active);
    }
    interface Presenter extends BasePresenter {

    }
}
