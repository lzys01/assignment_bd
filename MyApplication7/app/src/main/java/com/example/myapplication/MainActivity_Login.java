package com.example.myapplication;

import android.util.Log;
import android.view.View;

import com.example.myapplication.activity.BaseActivity;
import com.example.myapplication.activity.HomeActivity;
import com.example.myapplication.activity.LoginActivity;
import com.example.myapplication.activity.RegisterActivity;
import com.example.myapplication.activity.BaseActivity;

public class MainActivity_Login extends BaseActivity {
    @Override
    protected int initLayout() {
        return R.layout.activity_main_login;
    }

    @Override
    protected void initView() {

    }
    @Override
    protected void initData() {

    }
    public void loginOnclick(View view){
        navigaTo(LoginActivity.class);
    }
    public void registerOnclick(View view){
        navigaTo(RegisterActivity.class);
    }
    public void skipOnclick(View view){
        Log.d("debug","游客登陆");
        navigaTo(HomeActivity.class);
    }
}