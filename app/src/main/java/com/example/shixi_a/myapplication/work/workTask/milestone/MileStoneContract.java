package com.example.shixi_a.myapplication.work.workTask.milestone;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;
import com.example.shixi_a.myapplication.bean.MileStone;

import java.util.List;

/**
 * Created by a5376 on 2017/7/17.
 */

public interface MileStoneContract {

    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(final boolean active);

        void showMilestones(List<MileStone> list);

        void showTaskAdd(String id, String name);
    }
    interface Presenter extends BasePresenter {

        void selectedMileStone(MileStone clickedMileStone);
    }
}
