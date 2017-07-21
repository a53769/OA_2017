package com.example.shixi_a.myapplication.classify;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;
import com.example.shixi_a.myapplication.bean.Classify;

import java.util.List;

/**
 * Created by Shixi-A on 2017/6/9.
 */

public interface ClassifysContract {

    interface View extends BaseView<Presenter> {

        void showClassifys(List<Classify> projects);

        void showAddClassify();

        void showNoClassifys();

        void showLoadingClassifysError();

        void setLoadingIndicator(final boolean active);

        void showTaskAdd(String name, String id);

        void showEditClassify(Classify editClassify);
    }
    interface Presenter extends BasePresenter {

        void loadClassifys(boolean forceUpdate);

        void addNewClassify();

        void selectedClassify(Classify clickedClassify);

        void EditClassify(Classify editClassify);

        void DelClassify(String id);
    }
}
