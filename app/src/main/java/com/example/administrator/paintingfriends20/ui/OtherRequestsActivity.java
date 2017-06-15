package com.example.administrator.paintingfriends20.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.paintingfriends20.R;
import com.example.administrator.paintingfriends20.adapter.OtherRequestAdapter;
import com.example.administrator.paintingfriends20.domain.OtherRequest;
import com.example.administrator.paintingfriends20.utils.Utils;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 15530 on 2017/6/14.
 */

public class OtherRequestsActivity extends AppCompatActivity {

    private long rid;
    private List<OtherRequest> requestLists = new ArrayList<>();
    private ImageView mIvOhterrequestHeadportrait;
    private TextView mTvOtherrequestName;
    private TextView mTvOtherBarname;
    private ListView mLvOthersrequestRequest;
    private TextView mTvOtherrequestSendmessage;
    private String uname;  //网络获取的需求页面人名字
    private String uimage;  //网络获取的需求页面人头像名
    private String userName;
    private String userPwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_requests);

        rid = getIntent().getLongExtra("rid", 71);

        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        userName = preferences.getString("name", "name");   //用户名
        userPwd = preferences.getString("upwd", "upwd");   //用户名
        findId();
        getOtherMessage();

        OtherRequestAdapter adapter = new OtherRequestAdapter(getApplicationContext(), requestLists);
        mLvOthersrequestRequest.setAdapter(adapter);


        mTvOtherrequestSendmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EMClient.getInstance().login(userName.trim(), userPwd.trim(), new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        String chatId = uname.trim();

                        if (!TextUtils.isEmpty(chatId)) {
                            //获取当前登录用户的username
                            String currUsername = EMClient.getInstance().getCurrentUser();
                            if (chatId.equals(currUsername)){
                                Toast.makeText(OtherRequestsActivity.this,"不能和自己聊天",
                                        Toast.LENGTH_LONG).show();
                                return;
                            }

                            //跳转到聊天界面，开始聊天
                            Intent intent = new Intent(OtherRequestsActivity.this,ChatActivity.class);

                            //EaseUI封装的聊天界面需要这两个参数
                            intent.putExtra(EaseConstant.EXTRA_USER_ID,chatId);
//                    intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.Chat);

                            intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE,EaseConstant.CHATTYPE_SINGLE);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onError(int i, String s) {

                    }

                    @Override
                    public void onProgress(int i, String s) {

                    }
                });



            }
        });

    }

    private void findId() {
        mTvOtherBarname = (TextView) findViewById(R.id.TvOtherBarname);
        mIvOhterrequestHeadportrait = (ImageView) findViewById(R.id.IvOhterrequestHeadportrait);
        mTvOtherrequestName = (TextView) findViewById(R.id.TvOtherrequestName);
        mTvOtherrequestSendmessage = (TextView)findViewById(R.id.TvOtherrequestSendmessage);
        mLvOthersrequestRequest = (ListView)findViewById(R.id.LvOthersrequestRequest);

    }

    private void getOtherMessage() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    String urlMyRequestsPath = Utils.URL + "request/?obj=16&rid=" + rid;

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
                            uname = item.getString("uname");     //他人姓名
                            uimage = item.getString("uimage");   //他人头像名字
                            String rdate = item.getString("rdate");     //需求日期
                            String redetail = URLDecoder.decode(item.getString("rdetail"),"utf-8");     //需求内容

//                                int ruid = item.getInt("ruid");
//                                String name = item.getString("uname");
//                                String uimage = item.getString("uimage");   //用户头像
//                                lRequest.add(new Request((long)rid,Utils.URL + "upload/" + uimage,name,redetail,rdate));
                            requestLists .add(new OtherRequest("\u3000\u3000" + redetail,rdate));

                        }
                        System.out.println(uimage + ",  " + uname);
//                        Picasso.with(getActivity()).load(Utils.URL + "upload/" + headportrait).into(mIvMineHeadportrait);
//                        Picasso.with(getApplicationContext()).load(Utils.URL + "upload/" + uimage).into(mIvOhterrequestHeadportrait);

                        Message msg = Message.obtain();
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("uname",uname);
                        hashMap.put("uimage",uimage);
                        msg.obj = hashMap;
                        handler.sendMessage(msg);

                        in.close();


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();

    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            HashMap<String, String> obj = (HashMap<String, String>)  msg.obj;
            String uname = obj.get("uname");
            String uimage = obj.get("uimage");

            mTvOtherBarname.setText(uname);
            mTvOtherrequestName.setText(uname);
            Picasso.with(getApplicationContext()).load(Utils.URL + "upload/" + uimage).into(mIvOhterrequestHeadportrait);
        }
    };
}
