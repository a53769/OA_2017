package com.example.shixi_a.myapplication.applyout;

import android.content.Context;

import com.example.myokhttp.response.JsonResponseHandler;
import com.example.shixi_a.myapplication.bean.Egress;
import com.example.shixi_a.myapplication.model.egress.EgressRepository;
import com.example.shixi_a.myapplication.util.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by a5376 on 2017/7/12.
 */

public class ApplyOutPresenter implements ApplyOutContract.Presenter {
    private  EgressRepository mEgressRepository;

    private ApplyOutFragment mApplyOutView;

    private Context context;

    private Egress egress;

    private String egressId;


    public ApplyOutPresenter(Egress egress, EgressRepository mRepository, ApplyOutFragment applyOutFragment, Context applicationContext) {
        mEgressRepository = mRepository;
        mApplyOutView = applyOutFragment;
        mApplyOutView.setPresenter(this);
        context = applicationContext;
        this.egress = egress;
    }

    @Override
    public void start() {
        if(egress == null){

        }else{
            mApplyOutView.initView(egress.getOut_time(),egress.getUsername(),egress.getAddr(),egress.getMatter());
            egressId = egress.getId();
        }
    }



    @Override
    public void askOut(String time, String person, String address, final String reason) {
        if(time == null ||time.equals("")){
            mApplyOutView.showMessage("请选择时间");
        }else if(address == null || address.equals("")){
            mApplyOutView.showMessage("地址不能为空");
        }else if(reason == null || reason.equals("")){
            mApplyOutView.showMessage("事项不能为空");
        }else if(egress == null){
            mEgressRepository.applyOut(context,time,person,address,reason, new JsonResponseHandler() {
                @Override
                public void onSuccess(int statusCode, JSONObject response) throws JSONException {
                    if(response.getBoolean("rt")){
                        ToastUtils.showShort(context,"添加成功");
                        mApplyOutView.showEgress();
                    }else {
                        ToastUtils.showShort(context,response.getString("error"));
                    }
                }

                @Override
                public void onFailure(int statusCode, String error_msg) {
                    ToastUtils.showShort(context,"上传失败");
                }
            });
        }else{
            mEgressRepository.editOut(context,egressId,time,person,address,reason, new JsonResponseHandler() {
                @Override
                public void onSuccess(int statusCode, JSONObject response) throws JSONException {
                    if(response.getBoolean("rt")){
                        ToastUtils.showShort(context,"修改成功");
                        mApplyOutView.showEgress();
                    }else{
                        ToastUtils.showShort(context,response.getString("error"));
                    }
                }

                @Override
                public void onFailure(int statusCode, String error_msg) {
                    ToastUtils.showShort(context,"修改失败");
                }
            });
        }

    }
}
