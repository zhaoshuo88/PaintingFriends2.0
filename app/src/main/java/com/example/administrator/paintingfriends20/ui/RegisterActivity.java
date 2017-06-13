package com.example.administrator.paintingfriends20.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class RegisterActivity extends Activity {


    private EditText mEtRegisterName;
    private EditText mEtRegisterAccount;
    private EditText mEtRegisterPwd;
    private EditText mEtRegisterConfirmpwd;
    private Button mBtnRegisterRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findId();

        mBtnRegisterRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pwd = mEtRegisterPwd.getText().toString().trim();    //密码
                String confirmPwd = mEtRegisterConfirmpwd.getText().toString().trim();  //确认密码
                if (pwd.equals(confirmPwd)) {

                    new Thread() {
                        public void run ()
                        {
                            try {
                                String name = mEtRegisterName.getText().toString().trim();      //昵称
                                String account = mEtRegisterAccount.getText().toString().trim();    //邮箱

                                String urlPath = (Utils.URL) + "user/?obj=0&upwd=" + mEtRegisterPwd.getText().toString().trim()
                                        + "&uaccount=" + URLEncoder.encode(account, "UTF-8")
                                        + "&uname=" + URLEncoder.encode(name, "UTF-8");

                                System.out.println("##" + urlPath);
                                URL url = new URL(urlPath);
                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                                if (conn.getResponseCode() == 200) {

                                    System.out.println("200~~~~~~~~~~~~~~~~~~~");
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_LONG).show();
                                        }
                                    });

                                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                    startActivity(intent);
//                                    // 获得服务器响应的数据
//                                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
//                                    // 数据
//                                    String retData = null;
//                                    String responseData = "";
//                                    while ((retData = in.readLine()) != null) {
//                                        responseData += retData;
//                                    }
//                                    if(responseData.equals("false")){
//                                        handler2.sendEmptyMessage(0x122);
//                                    }
//                                    String json = responseData;
//                                    JSONArray j=new JSONArray(json);
//                                    JSONObject item=j.getJSONObject(0);
//                                    int id=item.getInt("uid");      //用户ID
//                                    String account = item.getString("uaccount");    //用户账号
//                                    String name = item.getString("uname");      //用户名
//                                    String headportrait = item.getString("uimage");     //用户头像
//                                    handler2.sendEmptyMessage(0x123);
//                                    SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
//                                    SharedPreferences.Editor editor = preferences.edit();
//                                    editor.putInt("uid", id);
//                                    editor.putString("name", name);
//                                    editor.putString("account", account);
//                                    editor.putString("headportrait",headportrait);
//                                    editor.commit();
//                                    in.close();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }.start();
                }else {
                    Toast.makeText(RegisterActivity.this,"密码和确认密码不等",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void findId() {
        mEtRegisterName = (EditText) findViewById(R.id.EtRegisterName);
        mEtRegisterAccount = (EditText) findViewById(R.id.EtRegisterAccount);
        mEtRegisterPwd = (EditText) findViewById(R.id.EtRegisterPwd);
        mEtRegisterConfirmpwd = (EditText) findViewById(R.id.EtRegisterConfirmpwd);
        mBtnRegisterRegister = (Button) findViewById(R.id.BtnRegisterRegister);
    }
}
