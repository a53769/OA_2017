package com.example.shixi_a.myapplication.home.work.workAdministration.normalReimburseDetail;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.JsonResponseHandler;
import com.example.shixi_a.myapplication.bean.Reimbursement;
import com.example.shixi_a.myapplication.model.reimbursement.ReimbursementRepository;
import com.example.shixi_a.myapplication.util.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by a5376 on 2017/7/27.
 */

public class NormalReimburseDetailPresenter implements NormalReimburseDetailContract.Presenter {

    private String reimburseId;
    private ReimbursementRepository mRepository;
    private NormalReimburseDetailFragment mReimburseView;
    private Context context;
    private Reimbursement reimbursement;

    public NormalReimburseDetailPresenter(String id, ReimbursementRepository repository, NormalReimburseDetailFragment detailFragment, Context context) {
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
                reimbursement = response;
                proccess(reimbursement);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {

            }
        });
    }

    private void proccess(Reimbursement reimbursement) {
        mReimburseView.InitView(
                reimbursement.getStatus_show(), reimbursement.getAdmin_name(),
                reimbursement.getApplicant_name(), reimbursement.getType_show(),
                reimbursement.getDttime(), reimbursement.getMemo(),
                reimbursement.getFee_total(), reimbursement.getBill_num(),
                reimbursement.getSum_month());//缺少客户用户名参数

        switch (reimbursement.getOpt()){
//            case "edit"://修改
//                mReimburseView.showEdit();
//                break;
            case "audit"://一般审批
                mReimburseView.showAudit();
                break;
            case "finance_audit"://财务审批
                mReimburseView.showFinanceAudit();
                break;
            case "cashier_audit"://出纳审批
                mReimburseView.showCashierAudit();
            default:
                mReimburseView.hideButton();
                break;
        }
    }

    @Override
    public void showReimburseLog() {
        if(reimbursement == null) {
            ToastUtils.showShort(context,"kong");
        }else{
            mReimburseView.showReimburseLog(reimbursement);
        }
    }

    @Override
    public void cancel() {
        mRepository.cancleReimburse(context,reimburseId, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) throws JSONException {
                if(response.getBoolean("rt")){
                    ToastUtils.showShort(context,"取消成功");
                    mReimburseView.showReimbursement();
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.showShort(context,"取消失败");
            }
        });
    }

    @Override
    public void edit() {
        mReimburseView.showNormalEdit(reimbursement);
    }

    @Override
    public void Audit(String s) {
        mRepository.auditReimburse(context,reimburseId,s, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) throws JSONException {
                if(response.getBoolean("rt")){
                    ToastUtils.showShort(context,"审批成功");
                    mReimburseView.showReimbursement();
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.showShort(context,"审批失败");
            }
        });
    }

    @Override
    public void FinanceAudit(String s) {
        if(s.equals("0")){
            Audit(s);
        }else{
            mReimburseView.showFinance(reimburseId);
        }
    }

    @Override
    public void CashierAudit(String s) {
        if (s.equals("0")){
            Audit(s);
        }else {
            mReimburseView.showCashier(reimburseId);
        }

    }
}
