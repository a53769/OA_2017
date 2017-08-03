package com.example.shixi_a.myapplication.model.reimbursement;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.JsonResponseHandler;
import com.example.shixi_a.myapplication.bean.FinanceType;
import com.example.shixi_a.myapplication.bean.Reimbursement;
import com.example.shixi_a.myapplication.bean.RowsNoPage;
import com.example.shixi_a.myapplication.model.BaseModel;
import com.example.shixi_a.myapplication.util.StringUtils;

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
    public void getTrafficOutTools(Context context, JsonResponseHandler callback) {
        InitRequest(context,"outcity_traffic_show");

        sendPostRequest(context,BASE + GETTRAFFICTOOL,params,callback);
    }

    @Override
    public void applyReimbursement1(Context context, String realId, String s, String outId, String toolId, String time, String startAddress, String trafficCost, String cost, String detail, String bills, String endAddress, JsonResponseHandler jsonResponseHandler) {
        InitRequest(context,"add");

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

    @Override
    public void applyReimbursement2(Context context, String realId, String s, String outId, String toolId, String time, String startAddress, String trafficCost, String cost, String detail, String bills, String address, String serveNum, JsonResponseHandler jsonResponseHandler) {
        InitRequest(context,"add");



        params.put("applicant_id",realId);
        params.put("type",s);
        params.put("dttime",time);
        params.put("fee_total",cost);
        params.put("bill_num",bills);
        params.put("memo",detail);

        params.put("user_name[0]","");
        params.put("out_id",outId);
        params.put("start_place",startAddress);
        params.put("end_place",address);
        params.put("outcity_traffic_by","");
        params.put("outcity_traffic_fee","");
        params.put("boarding_fee","");
        params.put("accomdation_fee","");
        params.put("incity_traffic_by",toolId);
        params.put("incity_traffic_fee",trafficCost);
        params.put("serve_num",serveNum);

        sendPostRequest(context,BASE + APPLYREIMBURSE,params,jsonResponseHandler);
    }

    @Override
    public void applyReimbursement3(Context context, String realId, String s, String time, String startAddress, String endAddress, String trafficCost, String outTrafficCost, String foodCost, String liveCost, String cost, String detail, String bills, String toolId, JsonResponseHandler jsonResponseHandler) {
        InitRequest(context,"add");


        params.put("applicant_id",realId);
        params.put("type",s);
        params.put("dttime",time);
        params.put("fee_total",cost);
        params.put("bill_num",bills);
        params.put("memo",detail);

        params.put("user_name[0]","");
        params.put("out_id","");
        params.put("start_place",startAddress);
        params.put("end_place",endAddress);
        params.put("outcity_traffic_by",toolId);
        params.put("outcity_traffic_fee",outTrafficCost);
        params.put("boarding_fee",foodCost);
        params.put("accomdation_fee",liveCost);
        params.put("incity_traffic_by","");
        params.put("incity_traffic_fee",trafficCost);
        params.put("serve_num","");

        sendPostRequest(context,BASE + APPLYREIMBURSE,params,jsonResponseHandler);
    }

    @Override
    public void cancleReimburse(Context context, String reimburseId, JsonResponseHandler jsonResponseHandler) {
        InitRequest(context,"cancel");

        params.put("reimburse_id",reimburseId);

        sendPostRequest(context,BASE + CANCELREIMBURSE,params,jsonResponseHandler);
    }

    @Override
    public void auditReimburse(Context context, String reimburseId, String s, JsonResponseHandler jsonResponseHandler) {
        InitRequest(context,"audit");

        params.put("reimburse_id",reimburseId);
        params.put("result",s);
        params.put("auditmemo","");
        params.put("pay_way","");
        params.put("pay_date","");
        params.put("pay_commission","");
        params.put("sort_id","");
        params.put("sub_sort_id","");
        params.put("asset_join","");
        params.put("asset_name","");
        params.put("asset_addr","");
        params.put("asset_id","");

        sendPostRequest(context,BASE + AUDITREIMBURSE,params,jsonResponseHandler);
    }

    @Override
    public void getFinanceTypeList(Context context, GsonResponseHandler<FinanceType> gsonResponseHandler) {
        InitRequest(context,"sort_show");

        sendPostRequest(context,BASE +GETFINANCETYPE ,params,gsonResponseHandler);
    }

    @Override
    public void passFinanceAudit(Context context, String reimburseId, String typeId, String detailId, String asset_name, String asset_address, String asset_id, String memo, JsonResponseHandler jsonResponseHandler) {
        InitRequest(context,"audit");

        String join = "" ;

        if(typeId.equals("3") && StringUtils.isEmpty(asset_name)) {
            join = "select";
        }else if(typeId.equals("3") && StringUtils.isEmpty(asset_id)){
            join = "new";
        }

        params.put("reimburse_id",reimburseId);
        params.put("result","1");
        params.put("auditmemo",memo);
        params.put("pay_way","");
        params.put("pay_date","");
        params.put("pay_commission","");
        params.put("sort_id",typeId);
        params.put("sub_sort_id",detailId);
        params.put("asset_join",join);
        params.put("asset_name",asset_name);
        params.put("asset_addr",asset_address);
        params.put("asset_id",asset_id);

        sendPostRequest(context,BASE + AUDITREIMBURSE,params,jsonResponseHandler);
    }

    @Override
    public void passCashierAudit(Context context, String reimburseId, String typeId, String pay_time, String pay_fee, String memo, JsonResponseHandler jsonResponseHandler) {
        InitRequest(context,"audit");



        params.put("reimburse_id",reimburseId);
        params.put("result","1");
        params.put("auditmemo",memo);
        params.put("pay_way",typeId);
        params.put("pay_date",pay_time);
        params.put("pay_commission",pay_fee);
        params.put("sort_id","");
        params.put("sub_sort_id","");
        params.put("asset_join","");
        params.put("asset_name","");
        params.put("asset_addr","");
        params.put("asset_id","");

        sendPostRequest(context,BASE + AUDITREIMBURSE,params,jsonResponseHandler);
    }

    @Override
    public void getPayment(Context context, JsonResponseHandler callback) {
        InitRequest(context,"pay_way_show");

        sendPostRequest(context,BASE + GETPAYMENT,params,callback);
    }

    @Override
    public void editReimbursement(Context context, String id, String typeId, String realId, String time, String cost, String detail, String bills, JsonResponseHandler jsonResponseHandler) {
        InitRequest(context,"edit");

        params.put("reimburse_id",id);
        params.put("applicant_id",realId);
        params.put("dttime",time);
        params.put("fee_total",cost);
        params.put("bill_num",bills);
        params.put("type",typeId);
        params.put("memo",detail);

        sendPostRequest(context,BASE + EDITREIMBURSE,params,jsonResponseHandler);
    }

    @Override
    public void editReimbursement1(Context context, String id, String realId, String s, String toolId, String time, String startAddress, String trafficCost, String cost, String detail, String bills, JsonResponseHandler jsonResponseHandler) {
        InitRequest(context,"edit");

        params.put("reimburse_id",id);
        params.put("applicant_id",realId);
        params.put("dttime",time);
        params.put("fee_total",cost);
        params.put("bill_num",bills);
        params.put("type",s);
        params.put("memo",detail);
        params.put("end_place",startAddress);
        params.put("incity_traffic_fee",trafficCost);
        params.put("incity_traffic_by",toolId);

        sendPostRequest(context,BASE + EDITREIMBURSE,params,jsonResponseHandler);
    }
}
