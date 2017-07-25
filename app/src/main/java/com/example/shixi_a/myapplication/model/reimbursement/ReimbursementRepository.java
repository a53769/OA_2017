package com.example.shixi_a.myapplication.model.reimbursement;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.JsonResponseHandler;
import com.example.shixi_a.myapplication.bean.Reimbursement;
import com.example.shixi_a.myapplication.bean.RowsNoPage;
import com.example.shixi_a.myapplication.model.BaseModel;

/**
 * Created by a5376 on 2017/7/24.
 */

public class ReimbursementRepository extends BaseModel implements ReimbursementDataSource {
    @Override
    public void getReimburses(Context context, GsonResponseHandler<RowsNoPage<Reimbursement>> gsonResponseHandler) {
        InitRequest(context,"rows");

        params.put("pn","50");
        params.put("np","1");
        params.put("admin_id","");
        params.put("applicant_id","");
        params.put("type","");
        params.put("status","");

        sendPostRequest(context,BASE + GETREIMBURSE, params, gsonResponseHandler);
    }

    @Override
    public void getReimburseType(Context context, JsonResponseHandler jsonResponseHandler) {
        InitRequest(context,"type_show");

        sendPostRequest(context,BASE + GETREIMBURSETYPE, params, jsonResponseHandler);
    }

    @Override
    public void appluReimbursement(Context context, JsonResponseHandler callback) {

    }
}
