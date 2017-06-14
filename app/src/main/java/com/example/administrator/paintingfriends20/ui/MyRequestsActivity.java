package com.example.administrator.paintingfriends20.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.widget.Button;
import android.widget.ListView;

import com.example.administrator.paintingfriends20.R;
import com.example.administrator.paintingfriends20.adapter.MyRequestAdapter;
import com.example.administrator.paintingfriends20.adapter.RequestAdapter;
import com.example.administrator.paintingfriends20.domain.MyRequest;
import com.example.administrator.paintingfriends20.domain.Request;
import com.example.administrator.paintingfriends20.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class MyRequestsActivity extends Activity {

    private ListView mLvReqAdpview;
    private List<MyRequest> requestLists = new ArrayList<>();
    private Button btn;
    private int uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrequest);
        Bundle extras = getIntent().getExtras();
        uid = extras.getInt("uid");     //用户ID

        mLvReqAdpview = (ListView) findViewById(R.id.LvMyrequestLists);
        getrequestListsDate();
        MyRequestAdapter adapter = new MyRequestAdapter(getApplicationContext(), requestLists);
        mLvReqAdpview.setAdapter(adapter);


//        btn = (Button)findViewById(R.id.btn_request);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(MyRequestsActivity.this, MainActivity.class);
//                //intent.putExtra("page",4);
//                startActivity(intent);
//            }
//        });
    }

    private void getrequestListsDate() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    String urlMyRequestsPath = Utils.URL + "request/?obj=15&uid=" + uid;

                    URL url = new URL(urlMyRequestsPath);

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    if (conn.getResponseCode() == 200){
                        //获得服务器响应数据

                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));

                        //数据
                        String retData = null;
                        String responseData = "";
                        while ((retData = in.readLine()) != null){
                            responseData += retData;
                        }
                        System.out.println(responseData);
                        JSONArray j = null;
                        j = new JSONArray((String)responseData);
                        for (int i = 0 ; i < j.length();i++){
                            JSONObject item = j.getJSONObject(i);
//                                int rid=item.getInt("rid");
                            String rdate = item.getString("rdate");
                            String redetail = URLDecoder.decode(item.getString("rdetail"),"utf-8");

//                                int ruid = item.getInt("ruid");
//                                String name = item.getString("uname");
//                                String uimage = item.getString("uimage");   //用户头像
//                                lRequest.add(new Request((long)rid,Utils.URL + "upload/" + uimage,name,redetail,rdate));
                            requestLists .add(new MyRequest("\u3000\u3000" + redetail,rdate));

                        }



                        in.close();


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();


    }
}
