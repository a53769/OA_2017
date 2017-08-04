package com.example.shixi_a.myapplication.work.workAdministration.financeAudit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.myokhttp.response.JsonResponseHandler;
import com.example.shixi_a.myapplication.work.workAdministration.financeDetail.FinanceDetailActivity;
import com.example.shixi_a.myapplication.work.workAdministration.financeType.FinanceTypeActivity;
import com.example.shixi_a.myapplication.model.reimbursement.ReimbursementRepository;
import com.example.shixi_a.myapplication.util.StringUtils;
import com.example.shixi_a.myapplication.util.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by a5376 on 2017/8/1.
 */

public class FinanceAuditPresenter implements FinanceAuditContract.Presenter{

    private ReimbursementRepository mRepository;
    private FinanceAuditFragment mFinanceView;
    private Context context;

    private String reimburseId;
    private String typeId;
    private String detailId;

    public void result(int requestCode, int resultCode, Intent data) {
        if (FinanceTypeActivity.REQUEST_TYPE_CODE == requestCode && Activity.RESULT_OK == resultCode) {
            String typeName = data.getStringExtra("name");
            typeId = data.getStringExtra("id");
            mFinanceView.setTypeName(typeName);
            String flag = data.getStringExtra("flag");
            if(flag != null && typeName.equals("固定资产")){
                if (flag.equals("0")){
                    mFinanceView.showAssetName();
                }
                if (flag.equals("1")){
                    mFinanceView.showAssetId();
                }
            }else{
                mFinanceView.hideAsset();
            }
        }
        if(FinanceDetailActivity.REQUEST_TYPE_CODE == requestCode && Activity.RESULT_OK == resultCode){
            String typeName = data.getStringExtra("name");
            detailId = data.getStringExtra("id");
            mFinanceView.setDetailName(typeName);
        }
    }

    public FinanceAuditPresenter(String reimburseId, ReimbursementRepository repository, FinanceAuditFragment financeAuditFragment, Context context) {
        mRepository = repository;
        mFinanceView = financeAuditFragment;
        mFinanceView.setPresenter(this);
        this.context = context;
        this.reimburseId = reimburseId;
    }

    @Override
    public void start() {

    }

    @Override
    public void showDetailSort() {
        if (typeId == null){
            ToastUtils.showShort(context,"请先选择类别");
            return;
        }
        mFinanceView.showdetailsort(typeId);
    }

    @Override
    public void auditPass(String asset_name, String asset_address, String asset_id, String memo) {
        if (StringUtils.isEmpty(typeId)){
            ToastUtils.showShort(context,"选择财务分类");
            return;
        }
        if (StringUtils.isEmpty(detailId)){
            ToastUtils.showShort(context,"选择详细分类");
        }

        mRepository.passFinanceAudit(context,reimburseId,typeId,detailId,asset_name,asset_address,asset_id,memo, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) throws JSONException {
                if(response.getBoolean("rt")){
                    ToastUtils.showShort(context,"审批成功");
                    mFinanceView.showReimburse();
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


}
