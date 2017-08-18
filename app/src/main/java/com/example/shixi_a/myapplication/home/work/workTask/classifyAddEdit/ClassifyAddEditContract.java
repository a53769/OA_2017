package com.example.shixi_a.myapplication.home.work.workTask.classifyAddEdit;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;

/**
 * Created by a5376 on 2017/6/26.
 */

public interface ClassifyAddEditContract {

    interface View extends BaseView<Presenter> {
        void setTitle(String classify_name);

        void setContent(String description);

        String getDescription();

        String getTitle();

        void setEdit();

        void showClassifys();

        void setText();
    }
    interface Presenter extends BasePresenter {
        void submit(String description);
    }
}
