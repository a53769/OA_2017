package com.example.shixi_a.myapplication.model.reimbursement;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.JsonResponseHandler;
import com.example.shixi_a.myapplication.bean.Reimbursement;
import com.example.shixi_a.myapplication.bean.RowsNoPage;

/**
 * Created by a5376 on 2017/7/24.
 */

public interface ReimbursementDataSource {
    void getReimburses(Context context, GsonResponseHandler<RowsNoPage<Reimbursement>> gsonResponseHandler);

    void getReimburseType(Context context, JsonResponseHandler jsonResponseHandler);
}
