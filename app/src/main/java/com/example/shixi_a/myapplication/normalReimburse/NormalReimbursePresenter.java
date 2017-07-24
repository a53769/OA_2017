package com.example.shixi_a.myapplication.normalReimburse;

import android.content.Context;
import android.content.Intent;

import com.example.shixi_a.myapplication.model.reimbursement.ReimbursementRepository;

/**
 * Created by a5376 on 2017/7/24.
 */

public class NormalReimbursePresenter implements NormalReimburseContract.Presenter{

    private ReimbursementRepository mRepository;
    private NormalReimburseFragment mNormalReimbursView;
    private Context context;


    public NormalReimbursePresenter(ReimbursementRepository repository, NormalReimburseFragment normalReimburseFragment, Context context) {
        mRepository = repository;
        mNormalReimbursView = normalReimburseFragment;
        mNormalReimbursView.setPresenter(this);
        this.context = context;
    }

    @Override
    public void start() {

    }

    @Override
    public void result(int requestCode, int resultCode, Intent data) {

    }
}
