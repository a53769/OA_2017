package com.example.shixi_a.myapplication.reimburseType;

import android.content.Context;

import com.example.myokhttp.response.JsonResponseHandler;
import com.example.shixi_a.myapplication.model.reimbursement.ReimbursementRepository;
import com.example.shixi_a.myapplication.util.LogUtils;
import com.example.shixi_a.myapplication.util.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by a5376 on 2017/7/24.
 */

public class ReimburseTypePresenter implements ReimburseTypeContract.Presenter{
    private ReimbursementRepository mRepository;
    private ReimburseTypeActivity mReimburseTypepView;
    private Context context;

    private Map<String, String> valueMap;

    public ReimburseTypePresenter(ReimbursementRepository repository, ReimburseTypeActivity reimburseTypeFragment, Context context) {
        mReimburseTypepView = reimburseTypeFragment;
        mReimburseTypepView.setPresenter(this);
        mRepository = repository;
        this.context = context;
    }

    @Override
    public void start() {
        loadType();
    }

    private void loadType() {
        mRepository.getReimburseType(context, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) throws JSONException {
                JSONObject jsonObject;
                jsonObject = new JSONObject(response.getString("type_arr"));
                Iterator<String> keyIter= jsonObject.keys();
                String key;
                String value ;
                valueMap = new LinkedHashMap<String, String>();
                while (keyIter.hasNext()) {
                    key = keyIter.next();
                    value = (String) jsonObject.get(key);
                    valueMap.put(key, value);
                }
                process(valueMap);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                LogUtils.v("获取失败");
                ToastUtils.showShort(context,"获取失败");
            }
        });
    }

    private void process(Map<String, String> valueMap) {
        mReimburseTypepView.showTypes(valueMap);
    }
}
