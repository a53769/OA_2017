package com.example.shixi_a.myapplication.home.work.workAdministration.financeType;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;
import com.example.shixi_a.myapplication.bean.FinanceType;

import java.util.List;

/**
 * Created by a5376 on 2017/8/1.
 */

public interface FinanceTypeContract {
    interface Presenter extends BasePresenter {
        void loadList();
    }

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(final boolean active);

        void showTypes(List<FinanceType.Sort> sort_arr);
    }
}
