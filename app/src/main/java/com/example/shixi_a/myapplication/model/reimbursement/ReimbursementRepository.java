package com.example.shixi_a.myapplication.model.reimbursement;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.JsonResponseHandler;
import com.example.shixi_a.myapplication.bean.Reimbursement;
import com.example.shixi_a.myapplication.bean.RowsNoPage;
import com.example.shixi_a.myapplication.model.BaseModel;

import static com.example.shixi_a.myapplication.util.StringUtils.SubTime;

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
    public void applyReimbursement(Context context, String realId, String typeId, String time, String cost, String bills, String detail, JsonResponseHandler callback) {
        InitRequest(context,"add");

        time = SubTime(time);

        params.put("applicant_id",realId);
        params.put("type",typeId);
        params.put("dttime",time);
        params.put("fee_total",cost);
        params.put("bill_num",bills);
        params.put("memo",detail);

        params.put("user_name[0]","");
        params.put("out_id","");
        params.put("start_place","");
        params.put("end_place","");
        params.put("outcity_traffic_by","");
        params.put("outcity_traffic_fee","");
        params.put("boarding_fee","");
        params.put("accomdation_fee","");
        params.put("incity_traffic_by","");
        params.put("incity_traffic_fee","");
        params.put("serve_num","");

        sendPostRequest(context,BASE + APPLYREIMBURSE,params,callback);

    }

    @Override
    public void getReimburse(Context context, String reimburseId, GsonResponseHandler<Reimbursement> gsonResponseHandler) {
        InitRequest(context,"row");

        params.put("reimburse_id",reimburseId);
        params.put("show_log","1");

        sendPostRequest(context,BASE + GETREIMBURSEDETAIL,params,gsonResponseHandler);
    }

    @Override
    public void getTrafficTools(Context context, JsonResponseHandler jsonResponseHandler) {
        InitRequest(context,"incity_traffic_show");

        sendPostRequest(context,BASE + GETTRAFFICTOOL,params,jsonResponseHandler);
    }

    @Override
    public void applyReimbursement1(Context context, String realId, String s, String outId, String toolId, String time, String startAddress, String trafficCost, String cost, String detail, String bills, String endAddress, JsonResponseHandler jsonResponseHandler) {
        InitRequest(context,"add");

        time = SubTime(time);

        params.put("applicant_id",realId);
        params.put("type",s);
        params.put("dttime",time);
        params.put("fee_total",cost);
        params.put("bill_num",bills);
        params.put("memo",detail);

        params.put("user_name[0]","");
        params.put("out_id",outId);
        params.put("start_place",startAddress);
        params.put("end_place",endAddress);
        params.put("outcity_traffic_by","");
        params.put("outcity_traffic_fee","");
        params.put("boarding_fee","");
        params.put("accomdation_fee","");
        params.put("incity_traffic_by",toolId);
        params.put("incity_traffic_fee",trafficCost);
        params.put("serve_num","");

        sendPostRequest(context,BASE + APPLYREIMBURSE,params,jsonResponseHandler);
    }
}
