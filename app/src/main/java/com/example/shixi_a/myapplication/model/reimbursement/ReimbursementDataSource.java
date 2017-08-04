package com.example.shixi_a.myapplication.model.reimbursement;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.JsonResponseHandler;
import com.example.shixi_a.myapplication.bean.FinanceType;
import com.example.shixi_a.myapplication.bean.Reimbursement;
import com.example.shixi_a.myapplication.bean.RowsNoPage;

/**
 * Created by a5376 on 2017/7/24.
 */

public interface ReimbursementDataSource {
    void getReimburses(Context context, GsonResponseHandler<RowsNoPage<Reimbursement>> gsonResponseHandler);

    void getReimburseType(Context context, JsonResponseHandler jsonResponseHandler);

    void applyReimbursement(Context context, String realId, String typeId, String time, String cost, String bills, String detail, JsonResponseHandler callback);

    void getReimburse(Context context, String reimburseId, GsonResponseHandler<Reimbursement> gsonResponseHandler);

    void getTrafficTools(Context context, JsonResponseHandler jsonResponseHandler);

    void applyReimbursement1(Context context, String realId, String s, String outId, String toolId, String time, String startAddress, String trafficCost, String cost, String detail, String bills, String endAddress, JsonResponseHandler jsonResponseHandler);

    void applyReimbursement2(Context context, String realId, String s, String outId, String toolId, String time, String startAddress, String trafficCost, String cost, String detail, String bills, String address, String serveNum, JsonResponseHandler jsonResponseHandler);

    void getTrafficOutTools(Context context, JsonResponseHandler outcity_traffic_arr);

    void applyReimbursement3(Context context, String realId, String s, String time, String startAddress, String endAddress, String trafficCost, String outTrafficCost, String foodCost, String liveCost, String cost, String detail, String bills, String toolId, JsonResponseHandler jsonResponseHandler);

    void cancleReimburse(Context context, String reimburseId, JsonResponseHandler jsonResponseHandler);

    void auditReimburse(Context context, String reimburseId, String s, JsonResponseHandler jsonResponseHandler);

    void getFinanceTypeList(Context context, GsonResponseHandler<FinanceType> jsonResponseHandler);

    void passFinanceAudit(Context context, String reimburseId, String typeId, String detailId, String asset_name, String asset_address, String asset_id, String memo, JsonResponseHandler jsonResponseHandler);

    void passCashierAudit(Context context, String reimburseId, String typeId, String pay_time, String pay_fee, String memo, JsonResponseHandler jsonResponseHandler);

    void getPayment(Context context, JsonResponseHandler pay_sort_arr);

    void editReimbursement(Context context, String id, String typeId, String realId, String time, String cost, String detail, String bills, JsonResponseHandler jsonResponseHandler);

    void editReimbursement1(Context context, String id, String realId, String s, String toolId, String time, String startAddress, String trafficCost, String cost, String detail, String bills, JsonResponseHandler jsonResponseHandler);

    void editReimbursement3(Context context, String id, String realId, String s, String time, String startAddress, String endAddress, String trafficCost, String outTrafficCost, String foodCost, String liveCost, String cost, String detail, String bills, String toolId, JsonResponseHandler jsonResponseHandler);

    void editReimbursement2(Context context, String id, String realId, String s, String toolId, String time, String startAddress, String trafficCost, String cost, String detail, String bills, String serveNum, JsonResponseHandler jsonResponseHandler);
}
