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
                        public void run() {
                            try {
                                String name = mEtRegisterName.getText().toString().trim();      //昵称
                                String account = mEtRegisterAccount.getText().toString().trim();    //邮箱

                                String urlPath = (Utils.URL) + "user/?obj=0&upwd=" + mEtRegisterPwd.getText().toString().trim()
                                        + "&uaccount=" + URLEncoder.encode(account, "UTF-8")
                                        + "&uname=" + URLEncoder.encode(name, "UTF-8");

                                URL url = new URL(urlPath);
                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                                if (conn.getResponseCode() == 200) {

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                                        }
                                    });

                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }.start();
                } else {
                    Toast.makeText(RegisterActivity.this, "密码和确认密码不等", Toast.LENGTH_LONG).show();
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
