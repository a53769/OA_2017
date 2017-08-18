package com.example.shixi_a.myapplication.home.work.workAdministration.financeDetail;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.shixi_a.myapplication.bean.FinanceType;
import com.example.shixi_a.myapplication.model.reimbursement.ReimbursementRepository;
import com.example.shixi_a.myapplication.util.LogUtils;

import java.util.List;

/**
 * Created by a5376 on 2017/8/1.
 */

public class FinanceDetailPresenter implements FinanceDetailContract.Presenter {
    private ReimbursementRepository mRepository;
    private FinanceDetailFragment mTypeView;
    private Context context;

    private String typeId;

    public FinanceDetailPresenter(String typeId, ReimbursementRepository repository, FinanceDetailFragment financeDetailFragment, Context context) {
        mRepository = repository;
        mTypeView = financeDetailFragment;
        mTypeView.setPresenter(this);
        this.context = context;
        this.typeId = typeId;
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
                List<FinanceType.Sort.Child> children = sort_arr.get(getPosition(sort_arr,typeId)).getChildren();
                process(children);
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                LogUtils.v("获取失败");
            }
        });
    }

    private int getPosition(List<FinanceType.Sort> sort_arr, String typeId) {
        for (int i = 0;i<sort_arr.size();i++){
            if(sort_arr.get(i).getInfo().getId().equals(typeId))
                return i;
        }
        return 0;
    }

    private void process(List<FinanceType.Sort.Child> children) {
        mTypeView.showTypes(children);
    }
}
