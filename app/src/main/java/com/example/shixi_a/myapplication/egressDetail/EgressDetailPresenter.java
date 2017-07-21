package com.example.shixi_a.myapplication.egressDetail;

import android.content.Context;

import com.example.shixi_a.myapplication.bean.Egress;
import com.example.shixi_a.myapplication.model.egress.EgressRepository;

/**
 * Created by a5376 on 2017/7/18.
 */

public class EgressDetailPresenter implements EgressDetailContract.Presenter {
    private EgressRepository mRepository;
    private EgressDetailFragment mEgressDetailView;
    private Context context;
    private Egress egress;


    public EgressDetailPresenter(Egress egress, EgressRepository repository, EgressDetailFragment egressDetailFragment, Context context) {
        mRepository = repository;
        mEgressDetailView = egressDetailFragment;
        mEgressDetailView.setPresenter(this);
        this.egress = egress;
        this.context = context;
    }

    @Override
    public void start() {
        //就是初始化界面啊 懒得多写一个函数了
        mEgressDetailView.InitView(egress.getAdminname(),egress.getStatus_show(),egress.getOut_time()
        ,egress.getUsername(),egress.getAddr(),egress.getMatter());
        if (egress.getStatus().equals("4")){
            mEgressDetailView.InitCheckView(egress.getEvent_addr(),egress.getEvent_time(),egress.getEvent_content());
        }
        mEgressDetailView.setLoadingIndicator(false);
    }
}
