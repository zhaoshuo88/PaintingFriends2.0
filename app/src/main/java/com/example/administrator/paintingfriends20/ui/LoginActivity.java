package com.example.administrator.paintingfriends20.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.paintingfriends20.MainActivity;
import com.example.administrator.paintingfriends20.R;
import com.example.administrator.paintingfriends20.utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by 15530 on 2017/5/21.
 */

public class LoginActivity extends Activity {

    private EditText mEtLoginAccount;
    private EditText mEtLoginPwd;
    private Button mBtnLoginLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findId();
        
        mBtnLoginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                
                new Thread() {
                    public void run ()
                    {
                        try {

                            String mLoginAccount = mEtLoginAccount.getText().toString();
                            String mLoginPwd = mEtLoginPwd.getText().toString();
                            String urlPath2 = (Utils.URL) + "user/?obj=1&upwd=" + mLoginPwd
                                    + "&uaccount=" + URLEncoder.encode(mLoginAccount, "UTF-8");
                            URL url = new URL(urlPath2);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                            if (conn.getResponseCode() == 200) {

                                // 获得服务器响应的数据
                                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                                // 数据
                                String retData = null;
                                String responseData = "";
                                while ((retData = in.readLine()) != null) {
                                    responseData += retData;
                                }
                                if(responseData.equals("false")){
                                    handler2.sendEmptyMessage(0x122);
                                }
                                String json = responseData;
                                JSONArray j=new JSONArray(json);
                                JSONObject item=j.getJSONObject(0);
                                int id=item.getInt("uid");
                                String account = item.getString("uaccount");
                                String name = item.getString("uname");
                                String headportrait = item.getString("uimage");
                                handler2.sendEmptyMessage(0x123);
                                SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putInt("id", id);
                                editor.putString("name", name);
                                editor.putString("account", account);
                                editor.putString("headportrait",headportrait);
                                editor.commit();
                                in.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }.start();
            

            }
        });
    }

    private void findId() {
        mEtLoginAccount = (EditText) findViewById(R.id.EtLoginAccount);
        mEtLoginPwd = (EditText) findViewById(R.id.EtLoginPwd);
        mBtnLoginLogin = (Button) findViewById(R.id.BtnLoginLogin);
    }


    Handler handler2 = new Handler()
    {
        public void handleMessage(Message msg)
        {
            if (msg.what == 0x123) {
                Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(LoginActivity.this, "用户名或者密码错误", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
