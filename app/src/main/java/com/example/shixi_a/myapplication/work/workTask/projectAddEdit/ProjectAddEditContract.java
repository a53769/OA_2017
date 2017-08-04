package com.example.shixi_a.myapplication.work.workTask.projectAddEdit;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;

/**
 * Created by a5376 on 2017/6/23.
 */

public interface ProjectAddEditContract {

    interface View extends BaseView<Presenter> {

        void setTitle(String project_name);

        void setContent(String description);

        String getDescription();

        String getTitle();

        void setEdit();

        void showProjects();

        void setText();
    }
    interface Presenter extends BasePresenter {


        void submit(String description);
    }
}
