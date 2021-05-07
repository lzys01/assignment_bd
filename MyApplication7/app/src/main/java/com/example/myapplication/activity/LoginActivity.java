package com.example.myapplication.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.api.Api;
import com.example.myapplication.api.ApiConfig;
import com.example.myapplication.api.TtitCallback;
import com.example.myapplication.entity.LoginResponse;
import com.example.myapplication.util.StringUtils;
import com.google.gson.Gson;

import java.util.HashMap;

public class LoginActivity extends BaseActivity {
    private EditText etAccount;
    private EditText etPwd;
    private Button btnLogin;

    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        etAccount=findViewById(R.id.et_account);
        etPwd=findViewById(R.id.et_pwd);
        btnLogin=findViewById(R.id.btn_login);
    }

    @Override
    protected void initData() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account=etAccount.getText().toString().trim();//trim是去除前后的空格
                String pwd=etPwd.getText().toString().trim();//trim是去除前后的空格
                login(account,pwd);
            }
        });
    }

    //登录逻辑
    public void login(String account,String pwd) {
        //判断输入的账户名和密码是否为空
        if (StringUtils.isEmpty(account)) {
            Log.d("Login","emptyAccount");
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
        Api.config(ApiConfig.LOGIN,params).postRequest(new TtitCallback() {
            @Override
            public void onSuccess(final String res) {
                Log.e("onSuccess",res);
                Gson gson=new Gson();
                LoginResponse loginResponse=gson.fromJson(res,LoginResponse.class);
                if (loginResponse.getCode()==0){
                    String token=loginResponse.getToken();
                    //文件存储
//                    SharedPreferences sp=getSharedPreferences("sp_ttit",MODE_PRIVATE);
//                    SharedPreferences.Editor editor=sp.edit();
//                    editor.putString("token",token);
//                    editor.commit();
                    saveStringToSp("token",token);
                    navigaTo(HomeActivity.class);
                    showToastSync("登录成功");
                }else{
                    showToastSync("登录失败");
                }
            }

            @Override
            public void onFaliure(Exception e) {

            }
        });
    }

//    public void login(String account,String pwd){
//        //判断输入的账户名和密码是否为空
//        if(StringUtils.isEmpty(account)){
//            showToast("请输入账号");
//            return;
//        }
//        if(StringUtils.isEmpty(pwd)){
//            showToast("请输入密码");
//            return;
//        }
//        //第一步创建OkHttpClient
//        OkHttpClient client=new OkHttpClient.Builder().build();
//        Map m=new HashMap();
//        m.put("mobile",account);
//        m.put("password",pwd);
//        JSONObject jsonObject=new JSONObject(m);
//        String jsonStr=jsonObject.toString();
//        RequestBody requestBodyJson=RequestBody.create(MediaType.parse(
//                "application/json;charset=utf-8"),jsonStr);
//        //第三步创建Request
//        Request request=new Request.Builder().url(AppConfig.BASE_URL+"/app/login").addHeader(
//                "contentType", "application/json;charset=UTF-8"
//        ).post(requestBodyJson).build();
//        //第四步创建call回调对象
//        final Call call=client.newCall(request);
//        //第五步发出请求
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.e("onFailure",e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String result=response.body().string();
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        showToast(result);
//                    }
//                });
//            }
//        });
//    }
}