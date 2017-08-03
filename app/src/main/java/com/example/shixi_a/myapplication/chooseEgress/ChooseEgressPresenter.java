package com.example.shixi_a.myapplication.chooseEgress;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.shixi_a.myapplication.bean.Egress;
import com.example.shixi_a.myapplication.bean.RowsNoPage;
import com.example.shixi_a.myapplication.model.egress.EgressRepository;
import com.example.shixi_a.myapplication.util.ToastUtils;

import java.util.List;

/**
 * Created by a5376 on 2017/7/28.
 */

public class ChooseEgressPresenter implements ChooseEgressContract.Presenter{

    private EgressRepository mRepository;
    private ChooseEgressFragment mChooseEgressView;
    private Context context;
    private String type;


    public ChooseEgressPresenter(String type, EgressRepository rrepository, ChooseEgressFragment chooseEgressFragment, Context context) {
        mRepository = rrepository;
        mChooseEgressView = chooseEgressFragment;
        mChooseEgressView.setPresenter(this);
        this.context = context;
        this.type = type;
    }

    @Override
    public void start() {
        loadEgress();
        mChooseEgressView.setLoadingIndicator(false);
    }

    private void loadEgress() {
        mRepository.getEgress(context, "-1", "0", new GsonResponseHandler<RowsNoPage<Egress>>() {
            @Override
            public void onSuccess(int statusCode, RowsNoPage<Egress> response) {
                List<Egress> egresses = response.rows;
                proccess(egresses);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.showShort(context,"加载失败请检查网络连接");
            }
        });
    }

    private void proccess(List<Egress> egresses) {
        mChooseEgressView.showEgresses(egresses);
    }

    @Override
    public void selectedEgress(String id, String address, String s) {
        if(type.equals("2")) {
            mChooseEgressView.showTrafficReimmburse(id, address, s);
        }else if(type.equals("3")){
            mChooseEgressView.showEntertainmentReimburse(id,address,s);
        }
    }


}
