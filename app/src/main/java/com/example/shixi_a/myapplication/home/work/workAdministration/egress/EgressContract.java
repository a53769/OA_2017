package com.example.shixi_a.myapplication.home.work.workAdministration.egress;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;
import com.example.shixi_a.myapplication.bean.Egress;

import java.util.List;

/**
 * Created by a5376 on 2017/7/11.
 */

public interface EgressContract {
    interface Presenter extends BasePresenter {
        void loadEgress();

        void setFilter(String group);

        void selectedEgress(Egress clickedEgress);

        void editEgress(Egress editEgress);

        void deleteEgress(Egress egress);
    }
    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(final boolean active);

        void showEgress(List<Egress> egresses);

        void showEditEgress(Egress editEgress);

        void showDetailEgress(Egress clickedEgress);
    }
}
