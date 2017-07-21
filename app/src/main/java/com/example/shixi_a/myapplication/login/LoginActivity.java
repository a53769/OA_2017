package com.example.shixi_a.myapplication.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shixi_a.myapplication.GlobalApp;
import com.example.shixi_a.myapplication.home.MainActivity;
import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.util.ToastUtils;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private LoginPresent mLoginPresent;
    private EditText etName;
    private EditText etPassword;
    private Button btLogin;
    private Context context;

    private SharedPreferences sp;//缓存用户名与密码实现自动登录
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //在加载布局文件前判断是否登陆过
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sp.edit();

        if(sp.getBoolean("main",false)){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            LoginActivity.this.finish();
        }

        setContentView(R.layout.activity_login);

        context = getApplicationContext();

        init(savedInstanceState);

    }

    private void init(@Nullable Bundle savedInstanceState) {

        mLoginPresent = new LoginPresent(this);

        etName = (EditText) findViewById(R.id.name);
        etPassword = (EditText) findViewById(R.id.password);
        btLogin = (Button) findViewById(R.id.sign_in_button);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginPresent.login(context, getName(), getPassword());
            }
        });

    }

    //显示错误吐司
    @Override
    public void showError(String msg) {
        ToastUtils.showShort(this, msg);
    }

    @Override
    public void setToken(String token){
        GlobalApp app = (GlobalApp) getApplicationContext();
        app.setUser_token(token);
        editor.putString("token",token);
        editor.commit();
    }


    //登录成功！跳转
    @Override
    public void showSucceed() {
        editor.putBoolean("main",true);
        editor.commit();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        LoginActivity.this.finish();
    }

    @Override
    public void setToken2(String token) {
        GlobalApp app = (GlobalApp) getApplicationContext();
        app.setVathome_token(token);
        editor.putString("token2",token);
        editor.commit();
    }

    @Override
    public void setUsername() {
        GlobalApp app = (GlobalApp) getApplicationContext();
        app.setUserName(getName());
        editor.putString("name",getName());
        editor.commit();
    }

    public String getName() {
        return etName.getText().toString().trim();//去除空格
    }

    public String getPassword() {
        return etPassword.getText().toString().trim();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mLoginPresent = (LoginPresent) presenter;
    }
}

