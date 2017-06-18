package com.example.administrator.paintingfriends20.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.paintingfriends20.R;
import com.example.administrator.paintingfriends20.fragment.RequestFragment;
import com.example.administrator.paintingfriends20.utils.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;

/**
 * Created by 15530 on 2017/5/27.
 */

public class PutRequestActivity extends Activity {

    private EditText mEtPutrequestContent;
    private Button mBtnPutrequestPut;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_request);

        mEtPutrequestContent = (EditText) findViewById(R.id.EtPutrequestContent);       //需求内容
        mBtnPutrequestPut = (Button) findViewById(R.id.BtnPutrequestPut);       //发布需求按钮

        mBtnPutrequestPut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.获取需求内容
                final String requestContent = mEtPutrequestContent.getText().toString();

                //2.获取发布时间
                Calendar instance = Calendar.getInstance();
                int year = instance.get(Calendar.YEAR);
                int month = instance.get(Calendar.MONTH);
                int day = instance.get(Calendar.DAY_OF_MONTH);
                int hour = instance.get(Calendar.HOUR_OF_DAY);
                int minute = instance.get(Calendar.MINUTE);
                int second = instance.get(Calendar.SECOND);
                final String calendar = year + "-" + month + "-" + day + "\t" + hour + ":" + minute + ":" + second;

                //3.获取用户名
                SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                final String name = preferences.getString("name", "name");


                new Thread() {
                    @Override
                    public void run() {
                        try {
                            String urlRequestPath = (new Utils().URL) + "request/?obj=4&runame=" + URLEncoder.encode(name, "UTF-8")//&ruid="+ruId.getText().toString()
                                    + "&rdetail=" + URLEncoder.encode(requestContent, "UTF-8")
                                    + "&rdate=" + URLEncoder.encode(calendar, "UTF-8");
                            URL url = new URL(urlRequestPath);

                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                            if (conn.getResponseCode() == 200) {
                                //获得服务器响应数据
                                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                                //数据
                                String retData = null;
                                String responseData = "";
                                while ((retData = in.readLine()) != null) {
                                    responseData += retData;
                                }
                                in.close();

                                setResult(2);
                                finish();

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

    }
}
