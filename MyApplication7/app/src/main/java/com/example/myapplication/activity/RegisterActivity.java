package com.example.myapplication.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.api.Api;
import com.example.myapplication.api.ApiConfig;
import com.example.myapplication.api.TtitCallback;
import com.example.myapplication.util.StringUtils;

import java.util.HashMap;

public class RegisterActivity extends BaseActivity {
    private EditText etAccount;
    private EditText etPwd;
    private Button btnRegister;

    @Override
    protected int initLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        etAccount=findViewById(R.id.et_account);
        etPwd=findViewById(R.id.et_pwd);
        btnRegister=findViewById(R.id.btn_register);
    }

    @Override
    protected void initData() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("debug","OnClickCalled");
                String account=etAccount.getText().toString().trim();//trim是去除前后的空格
                String pwd=etPwd.getText().toString().trim();//trim是去除前后的空格
                register(account,pwd);
            }
        });
    }

    //注册逻辑
    public void register(String account,String pwd) {
        //判断输入的账户名和密码是否为空
        if (StringUtils.isEmpty(account)) {
            Log.d("debug","register account empty");
            showToast("请输入账号");
            return;
        }
        if (StringUtils.isEmpty(pwd)) {
            showToast("请输入密码");
            return;
        }
        HashMap<String,Object> params=new HashMap<String, Object>();
        params.put("mobile",account);
        params.put("password",pwd);
        Api.config(ApiConfig.REGISTER,params).postRequest(new TtitCallback() {
            @Override
            public void onSuccess(String res) {
                Toast.makeText(RegisterActivity.this,"上传成功",Toast.LENGTH_SHORT).show();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast(res);
                    }
                });
            }
            @Override
            public void onFaliure(Exception e) {
                Log.e("onFailure",e.toString());
            }
        });
    }
}