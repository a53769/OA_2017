package com.example.shixi_a.myapplication.work.workAdministration.financeAudit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.work.workAdministration.financeDetail.FinanceDetailActivity;
import com.example.shixi_a.myapplication.work.workAdministration.financeType.FinanceTypeActivity;
import com.example.shixi_a.myapplication.util.StringUtils;
import com.example.shixi_a.myapplication.util.ToastUtils;

/**
 * Created by a5376 on 2017/8/1.
 */

public class FinanceAuditFragment extends Fragment implements FinanceAuditContract.View{

    private FinanceAuditContract.Presenter mPresenter;

    private TextView typeName;
    private TextView detailName;

    private EditText memo;

    private LinearLayout asset1;
    private LinearLayout asset2;

    public FinanceAuditFragment(){}

    public static FinanceAuditFragment newInstance() {
        return new FinanceAuditFragment();
    }

    @Override
    public void setPresenter(FinanceAuditContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode, data);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.finance_audit_content, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        LinearLayout lv_type = (LinearLayout) root.findViewById(R.id.lv_finance_type);
        lv_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFinanceType();
            }
        });

        LinearLayout lv_detail = (LinearLayout) root.findViewById(R.id.lv_detail_type);
        lv_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.showDetailSort();
            }
        });

        typeName = (TextView) root.findViewById(R.id.type_name);
        detailName = (TextView) root.findViewById(R.id.detail_sort);

        asset1 = (LinearLayout) root.findViewById(R.id.asset_1);
        asset2 = (LinearLayout) root.findViewById(R.id.asset_2);

        memo = (EditText) root.findViewById(R.id.finance_memo);

        Button submit = (Button) root.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                financeAuditPass();
            }
        });

        return root;
    }

    private void financeAuditPass() {
        String asset_name = "";
        String asset_address = "";
        String asset_id = "";

        if (asset1.getVisibility() == View.VISIBLE){
            EditText assetname = (EditText) asset1.findViewById(R.id.asset_name);
            EditText assetaddress = (EditText) asset1.findViewById(R.id.asset_address);
            asset_name = assetname.getText().toString().trim();
            asset_address = assetaddress.getText().toString().trim();

            if(StringUtils.isEmpty(asset_name)||StringUtils.isEmpty(asset_address)){
                ToastUtils.showShort(getContext(),"请完善内容");
            }else{
                mPresenter.auditPass(asset_name,asset_address,asset_id,getMemo());
            }
        }else if(asset2.getVisibility() == View.VISIBLE){
            EditText assetid = (EditText) asset2.findViewById(R.id.asset_id);
            asset_id = assetid.getText().toString().trim();
            if(StringUtils.isEmpty(asset_id)){
                ToastUtils.showShort(getContext(),"请完善内容");
            }else{
                mPresenter.auditPass(asset_name,asset_address,asset_id,getMemo());
            }
        }

    }

    @Override
    public void showdetailsort(String typeId) {
        Intent intent = new Intent(getActivity(), FinanceDetailActivity.class);
        intent.putExtra("type_id",typeId);
        startActivityForResult(intent,FinanceDetailActivity.REQUEST_TYPE_CODE);
    }

    @Override
    public void showAssetName() {
        asset1.setVisibility(View.VISIBLE);
        asset2.setVisibility(View.GONE);
    }

    @Override
    public void showAssetId() {
        asset2.setVisibility(View.VISIBLE);
        asset1.setVisibility(View.GONE);
    }

    @Override
    public void hideAsset() {
        asset1.setVisibility(View.GONE);
        asset2.setVisibility(View.GONE);
    }

    @Override
    public void showReimburse() {
        getActivity().finish();
    }

    private void showFinanceType() {
        Intent intent = new Intent(getActivity(), FinanceTypeActivity.class);
        startActivityForResult(intent,FinanceTypeActivity.REQUEST_TYPE_CODE);
    }

    public void setTypeName(String typeName) {
        this.typeName.setText(typeName);
    }

    public void setDetailName(String detailName) {
        this.detailName.setText(detailName);
    }

    public String getMemo() {
        return memo.getText().toString().trim();
    }
}
