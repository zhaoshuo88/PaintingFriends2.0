package com.example.administrator.paintingfriends20.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.paintingfriends20.R;
import com.example.administrator.paintingfriends20.adapter.MyWorkAdapter;
import com.example.administrator.paintingfriends20.domain.MyWork;
import com.example.administrator.paintingfriends20.utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MyWorksActivity extends Activity {

    private List<MyWork> mMyWorksLists = new ArrayList<>();
    private MyWorkAdapter mMyWorkAdapter;
    private GridView mGvMyworkView;

    private ImageView mwpic;
    private TextView mwname;
    private TextView TvMyworkitemName;
    private ImageView mIvMyworkitemImage;
    private String userNmae;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mywork);

        Bundle extras = getIntent().getExtras();
        userNmae = extras.getString("name");     //用户姓名

        mGvMyworkView = (GridView) findViewById(R.id.GvMyworkView);
        mIvMyworkitemImage = (ImageView) findViewById(R.id.IvMyworkitemImage);
        TvMyworkitemName = (TextView) findViewById(R.id.TvMyworkitemName);

        getmwLikeDate();

        mMyWorkAdapter = new MyWorkAdapter(this, mMyWorksLists);
        mGvMyworkView.setAdapter(mMyWorkAdapter);
    }

    private void getmwLikeDate() {

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    String urlMyWorksPath = Utils.URL + "draw/?obj=14&uname=" + URLEncoder.encode(userNmae, "UTF-8");

                    URL url = new URL(urlMyWorksPath);

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
                        System.out.println(responseData);
                        JSONArray j = null;
                        j = new JSONArray((String) responseData);
                        for (int i = 0; i < j.length(); i++) {
                            JSONObject item = j.getJSONObject(i);

                            int did = item.getInt("did");
                            String durl = item.getString("durl");   //作品名字
                            int dlike = item.getInt("dlike");

                            mMyWorksLists.add(new MyWork(did, Utils.URL + "upload/" + durl, dlike));
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
