package com.example.shixi_a.myapplication.model.egress;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.JsonResponseHandler;
import com.example.shixi_a.myapplication.bean.Egress;
import com.example.shixi_a.myapplication.bean.RowsNoPage;

/**
 * Created by a5376 on 2017/7/11.
 */

public interface EgressDataSource {
    void getEgress(Context context, String adminId, String groupId, GsonResponseHandler<RowsNoPage<Egress>> callback);

    void applyOut(Context context, String time, String person, String address, String reason, JsonResponseHandler jsonResponseHandler);

    void getEgressDetail(Context context, String outId, GsonResponseHandler<Egress> gsonResponseHandler);

    void deletEgress(Context context, String id, JsonResponseHandler callback);

    void editOut(Context context, String egressId, String time, String person, String address, String reason, JsonResponseHandler jsonResponseHandler);

    void checkOut(Context context, String memo1, String of, String valueOf, String s, String address, String tabname, String memo, JsonResponseHandler jsonResponseHandler);
}
