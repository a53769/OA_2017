package com.example.shixi_a.myapplication.work.workAdministration.cashierAudit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.myokhttp.response.JsonResponseHandler;
import com.example.shixi_a.myapplication.model.reimbursement.ReimbursementRepository;
import com.example.shixi_a.myapplication.work.workAdministration.payment.PaymentActivity;
import com.example.shixi_a.myapplication.util.StringUtils;
import com.example.shixi_a.myapplication.util.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by a5376 on 2017/8/2.
 */

public class CashierAuditPresenter implements CashierAuditContract.Presenter {

    private ReimbursementRepository mRepository;
    private CashierAuditFragment mCashierView;
    private Context context;
    private String reimburseId;
    private String typeId;

    public CashierAuditPresenter(String reimburseId, ReimbursementRepository repository, CashierAuditFragment cashierAuditFragment, Context context) {
        mRepository = repository;
        mCashierView = cashierAuditFragment;
        mCashierView.setPresenter(this);
        this.context = context;
        this.reimburseId = reimburseId;

    }

    @Override
    public void result(int requestCode, int resultCode, Intent data) {
        if(PaymentActivity.REQUEST_PAYMENT_ID == requestCode && Activity.RESULT_OK == resultCode){
            String typeName = data.getStringExtra("value");
            typeId = data.getStringExtra("id");
            mCashierView.setName(typeName);
        }
    }

    @Override
    public void auditPass(String pay_time, String pay_fee, String memo) {
        if(StringUtils.isEmpty(typeId)){
            ToastUtils.showShort(context,"请选择付款方式");
            return;
        }
        mRepository.passCashierAudit(context,reimburseId,typeId,pay_time,pay_fee,memo, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) throws JSONException {
                if(response.getBoolean("rt")){
                    ToastUtils.showShort(context,"审批成功");
                    mCashierView.showReimburse();
                }else{
                    ToastUtils.showShort(context,response.getString("error"));
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.showShort(context,"请检查网络连接");
            }
        });
    }

    @Override
    public void start() {

    }
}
