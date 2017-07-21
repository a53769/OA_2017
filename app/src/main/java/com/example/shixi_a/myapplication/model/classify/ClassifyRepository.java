package com.example.shixi_a.myapplication.model.classify;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.RawResponseHandler;
import com.example.shixi_a.myapplication.model.BaseModel;

/**
 * Created by Shixi-A on 2017/6/9.
 */

public class ClassifyRepository extends BaseModel implements ClassifyDataSource {
    @Override
    public void getClassifys(Context context, GsonResponseHandler callback) {

        initRequest(context, "rows");

        /**
         * 选择传参
         */
        params.put("page_num","1");
        params.put("per_page","25");

        sendPostRequest(context, BASEURL + GETTARGETTYPELIST , params, callback);
    }

    @Override
    public void addClassify(Context context, String title, String description, RawResponseHandler callback) {
        initRequest(context,"add");

        params.put("target_name",title);
        params.put("memo",description);

        sendPostRequest(context, BASEURL + ADDTARGETTYPE ,params, callback);
    }

    @Override
    public void editClassify(Context context, String id, String description, RawResponseHandler callback) {
        initRequest(context,"edit");

        params.put("target_type_id",id);
        params.put("memo",description);

        sendPostRequest(context, BASEURL + EDITTARGETTYPE, params, callback);
    }

    public void deleteCla(Context context, String id, RawResponseHandler callback) {
        initRequest(context,"del");

        params.put("target_type_id",id);
        sendPostRequest(context, BASEURL + DELETETARGETTYPE , params, callback);
    }
}
