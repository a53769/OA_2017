package com.example.shixi_a.myapplication.payment;

import android.content.Context;

import com.example.myokhttp.response.JsonResponseHandler;
import com.example.shixi_a.myapplication.model.reimbursement.ReimbursementRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by a5376 on 2017/8/3.
 */

public class PaymentPresenter implements PaymentContract.Presenter{

    private ReimbursementRepository mRepository;
    private PaymentActivity mPaymentView;
    private Context context;
    private Map<String, String> valueMap;

    public PaymentPresenter(ReimbursementRepository repository, PaymentActivity paymentActivity, Context context) {
        mRepository = repository;
        mPaymentView = paymentActivity;
        mPaymentView.setPresenter(this);
        this.context = context;
    }

    @Override
    public void start() {
        loadTypes();
    }

    private void loadTypes() {
        mRepository.getPayment(context, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) throws JSONException {
                JSONObject jsonObject;
                jsonObject = new JSONObject(response.getString("pay_way_arr"));
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

            }
        });
    }

    private void process(Map<String, String> valueMap) {
        mPaymentView.showTypes(valueMap);
    }
}
