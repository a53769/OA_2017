package com.example.shixi_a.myapplication.reimburseDetail;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.shixi_a.myapplication.bean.Reimbursement;
import com.example.shixi_a.myapplication.model.reimbursement.ReimbursementRepository;

/**
 * Created by a5376 on 2017/7/27.
 */

public class ReimburseDetailPresenter implements ReimburseDetailContract.Presenter {

    private String reimburseId;
    private ReimbursementRepository mRepository;
    private ReimburseDetailFragment mReimburseView;
    private Context context;

    public ReimburseDetailPresenter(String id, ReimbursementRepository repository, ReimburseDetailFragment detailFragment, Context context) {
        reimburseId = id;
        mReimburseView = detailFragment;
        mReimburseView.setPresenter(this);
        mRepository = repository;
        this.context = context;
    }

    @Override
    public void start() {
        loadReimburse();
    }

    private void loadReimburse() {
        mRepository.getReimburse(context,reimburseId, new GsonResponseHandler<Reimbursement>() {
            @Override
            public void onSuccess(int statusCode, Reimbursement response) {
                Reimbursement reimbursement = response;
                proccess(reimbursement);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {

            }
        });
    }

    private void proccess(Reimbursement reimbursement) {
        mReimburseView.InitView(
                reimbursement.getStatus_show(),
                reimbursement.getAdmin_name(),
                reimbursement.getApplicant_name(),
                reimbursement.getType_show(),
                reimbursement.getDttime(),
                reimbursement.getMemo(),
                reimbursement.getFee_total(),
                reimbursement.getBill_num(),
                reimbursement.getSum_month()
                );//缺少客户用户名参数
    }
}
