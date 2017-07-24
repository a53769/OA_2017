package com.example.shixi_a.myapplication.reimbursement;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.shixi_a.myapplication.bean.Reimbursement;
import com.example.shixi_a.myapplication.bean.RowsNoPage;
import com.example.shixi_a.myapplication.model.reimbursement.ReimbursementRepository;

import java.util.List;

/**
 * Created by a5376 on 2017/7/24.
 */

public class ReimbursementPresenter implements ReimbursementContract.Presenter{

    private ReimbursementRepository mRepository;
    private ReimbursementFragment mReimburseView;
    private Context context;


    public ReimbursementPresenter(ReimbursementRepository repository, ReimbursementFragment reimbursementFragment, Context context) {
        mRepository = repository;
        mReimburseView = reimbursementFragment;
        mReimburseView.setPresenter(this);
        this.context = context;

    }

    @Override
    public void start() {
        mReimburseView.setLoadingIndicator(true);
        loadReimburse();
        mReimburseView.setLoadingIndicator(false);
    }

    private void loadReimburse() {
        mRepository.getReimburses(context, new GsonResponseHandler<RowsNoPage<Reimbursement>>() {
            @Override
            public void onSuccess(int statusCode, RowsNoPage<Reimbursement> response) {
                List<Reimbursement> list;
                list = response.rows;
                process(list);

            }

            @Override
            public void onFailure(int statusCode, String error_msg) {

            }
        });
    }

    private void process(List<Reimbursement> list) {
        mReimburseView.showReimburses(list);
    }
}
