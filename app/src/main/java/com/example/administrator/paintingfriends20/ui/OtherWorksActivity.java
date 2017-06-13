package com.example.administrator.paintingfriends20.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.paintingfriends20.R;
import com.example.administrator.paintingfriends20.adapter.HomeLikeAdapter;
import com.example.administrator.paintingfriends20.adapter.OtherWorkItemAdapter;
import com.example.administrator.paintingfriends20.domain.HomeLike;
import com.example.administrator.paintingfriends20.domain.MyWork;
import com.example.administrator.paintingfriends20.domain.OtherWork;
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

public class OtherWorksActivity extends Activity {

    private List<OtherWork> mwLike = new ArrayList<>();
    private OtherWorkItemAdapter mwlikeAdapter;
    private GridView mwLikeList;

    private ImageView mwpic;
    private TextView mwname;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_work);

        mwLikeList = (GridView)findViewById(R.id.GvOtherworkView);
        mwpic = (ImageView)findViewById(R.id.IvOtherworkitemImage );
        mwname = (TextView) findViewById(R.id.TvOtherworkitemName);

        name = getIntent().getStringExtra("name");   //接收发现页面传递的个人信息(姓名)
        getmwLikeDate();

        mwlikeAdapter = new OtherWorkItemAdapter(this,mwLike);
        mwLikeList.setAdapter(mwlikeAdapter);
    }

    private void getmwLikeDate() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
//                    String urlPath2 = (Utils.URL) + "user/?obj=1&upwd=" + mLoginPwd
//                    + "&uaccount=" + URLEncoder.encode(mLoginAccount, "UTF-8");
                    String urlMyWorksPath = Utils.URL + "draw/?obj=14&uname=" + URLEncoder.encode(name,"UTF-8");

                    URL url = new URL(urlMyWorksPath);

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
                            int did = item.getInt("did");
                            String durl = item.getString("durl");   //作品地址
                            int dlike = item.getInt("dlike");
//                            String rdate = item.getString("rdate");
//                            String redetail = URLDecoder.decode(item.getString("rdetail"),"utf-8");

//                                int ruid = item.getInt("ruid");
//                                String name = item.getString("uname");
//                                String uimage = item.getString("uimage");   //用户头像
//                                lRequest.add(new Request((long)rid,Utils.URL + "upload/" + uimage,name,redetail,rdate));
//                            requestLists .add(new MyRequest("\u3000\u3000" + redetail,rdate));
//                            mMyWorksLists .add(new MyWork(did,Utils.URL + "upload/" + durl,dlike));

                            mwLike .add(new OtherWork(did,Utils.URL + "upload/" + durl,dlike));
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
