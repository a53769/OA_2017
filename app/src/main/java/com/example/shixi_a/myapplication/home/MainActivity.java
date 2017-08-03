package com.example.shixi_a.myapplication.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.myokhttp.response.RawResponseHandler;
import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.home.message.Fragment_message;
import com.example.shixi_a.myapplication.model.assist.AssistRepository;
import com.example.shixi_a.myapplication.util.LogUtils;


public class MainActivity extends AppCompatActivity {

    private Fragment_message fg1;
    private Fragment_work fg2;
    private Fragment_contacts fg3;
    private Fragment_my fg4;

    private FragmentManager fragmentManager;
    private AssistRepository mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setMode(MODE_FIXD);

        fragmentManager = getSupportFragmentManager();

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        navigation.findViewById(R.id.navigation_work).performClick();

        mRepository = new AssistRepository();

        mRepository.uploadToken(getApplicationContext(), new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.v("上传Token成功");
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                LogUtils.v("上传Token失败");
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_message:
                    setChoiceItem(0);
                    return true;
                case R.id.navigation_work:
                    setChoiceItem(1);
                    return true;
                case R.id.navigation_contacts:
                    setChoiceItem(2);
                    return true;
                case R.id.navigation_my:
                    setChoiceItem(3);
                    return true;
            }
            return false;
        }

    };

    private void setChoiceItem(int index) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (index) {
            case 0:
                if (fg1 == null) {
                    fg1 = new Fragment_message();
                    //fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.content, fg1);
                } else {
                    // 如果不为空，则直接将它显示出来
                    //fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.content, fg1);
                }
                break;
            case 1:
                if (fg2 == null) {
                    fg2 = new Fragment_work();
                    //fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.content, fg2);
                } else {
                    //fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.content, fg2);
                }
                break;
            case 2:
                if (fg3 == null) {
                    fg3 = new Fragment_contacts();
                    //fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.content, fg3);
                } else {
                    //fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.content, fg3);
                }
                break;
            case 3:
                if (fg4 == null) {
                    fg4 = new Fragment_my();
                    //fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.content, fg4);
                } else {
                    //fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.content, fg4);
                }
                break;
        }
        fragmentTransaction.commit(); // 提交
    }



}
