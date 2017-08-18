package com.example.shixi_a.myapplication.home.work.workAdministration.financeType;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.shixi_a.myapplication.bean.FinanceType;
import com.example.shixi_a.myapplication.model.reimbursement.ReimbursementRepository;
import com.example.shixi_a.myapplication.util.LogUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by a5376 on 2017/8/1.
 */

public class FinanceTypePresenter implements FinanceTypeContract.Presenter {
    private ReimbursementRepository mRepository;
    private FinanceTypeFragment mTypeView;
    private Context context;

    private Map<String, String> valueMap;

    public FinanceTypePresenter(ReimbursementRepository repository, FinanceTypeFragment financeTypeFragment, Context context) {
        mRepository = repository;
        mTypeView = financeTypeFragment;
        mTypeView.setPresenter(this);
        this.context = context;
    }

    @Override
    public void start() {
        loadList();
    }

    @Override
    public void loadList() {
        mRepository.getFinanceTypeList(context, new GsonResponseHandler<FinanceType>() {
            @Override
            public void onSuccess(int statusCode, FinanceType response) {
                List<FinanceType.Sort> sort_arr = response.getSort_arr();
                process(sort_arr);
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                LogUtils.v("获取失败");
            }
        });
    }

    private void process(List<FinanceType.Sort> sort_arr) {
        mTypeView.showTypes(sort_arr);
    }
}
