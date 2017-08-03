package com.example.shixi_a.myapplication.financeAudit;

import android.content.Intent;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;

/**
 * Created by a5376 on 2017/8/1.
 */

public interface FinanceAuditContract {
    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode, Intent data);

        void showDetailSort();

        void auditPass(String asset_name, String asset_address, String asset_id, String memo);
    }
    interface View extends BaseView<Presenter> {
        void showdetailsort(String typeId);

        void showAssetName();

        void showAssetId();

        void hideAsset();

        void showReimburse();
    }
}
