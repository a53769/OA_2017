package com.example.shixi_a.myapplication.model.egress;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.JsonResponseHandler;
import com.example.shixi_a.myapplication.bean.Egress;
import com.example.shixi_a.myapplication.bean.RowsNoPage;
import com.example.shixi_a.myapplication.model.BaseModel;

/**
 * Created by a5376 on 2017/7/11.
 */

public class EgressRepository extends BaseModel implements EgressDataSource {
    @Override
    public void getEgress(Context context, String adminId, String groupId, GsonResponseHandler<RowsNoPage<Egress>> callback) {

        InitRequest(context,"rows");

        params.put("np","1");
        params.put("pn","50");
        params.put("admin_id",adminId);
        params.put("group_id",groupId);

        sendPostRequest(context,BASE + GETEGRESSLIST , params ,callback);
    }

    @Override
    public void applyOut(Context context, String time, String person, String address, String reason, JsonResponseHandler jsonResponseHandler) {
        InitRequest(context,"add");

        params.put("out_time",time);
        params.put("addr",address);
        params.put("matter",reason);
        params.put("user_name",person);

        sendPostRequest(context,BASE + ADDOUT ,params,jsonResponseHandler);
    }

    @Override
    public void getEgressDetail(Context context, String outId, GsonResponseHandler<Egress> gsonResponseHandler) {
        InitRequest(context,"row");

        params.put("out_id",outId);

        sendPostRequest(context,BASE + GETEGRESS, params, gsonResponseHandler);
    }

    @Override
    public void deletEgress(Context context, String id, JsonResponseHandler callback) {

        InitRequest(context,"delete");

        params.put("out_id",id);

        sendPostRequest(context,BASE + DELEGRESS,params,callback);
    }

    @Override
    public void editOut(Context context, String egressId, String time, String person, String address, String reason, JsonResponseHandler jsonResponseHandler) {
        InitRequest(context,"edit");

        params.put("out_id",egressId);
        params.put("out_time",time);
        params.put("adminname",person);
        params.put("addr",address);
        params.put("matter",reason);

        sendPostRequest(context,BASE + EDITEGRESS,params,jsonResponseHandler);
    }

    @Override
    public void checkOut(Context context, String memo, String longitude, String latitude, String altitude, String address, String tabname, String outId, JsonResponseHandler callback) {
        InitRequest(context,"add");

        params.put("longitude",longitude);
        params.put("latitude",latitude);
        params.put("altitude",altitude);
        params.put("content",memo);
        params.put("addr",address);
        params.put("storage_urls","");
        params.put("tab_name",tabname);
        params.put("tab_id",outId);

        sendPostRequest(context,BASE + CHECKEGRESS,params,callback);
    }
}
