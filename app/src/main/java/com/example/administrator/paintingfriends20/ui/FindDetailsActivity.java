package com.example.administrator.paintingfriends20.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.administrator.paintingfriends20.R;
import com.example.administrator.paintingfriends20.adapter.FindDetailCommentsListsAdapter;
import com.example.administrator.paintingfriends20.domain.FindDetailsComment;
import com.example.administrator.paintingfriends20.utils.Utils;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15530 on 2017/5/23.
 */

public class FindDetailsActivity extends AppCompatActivity {

    private EditText mEtFinddetailsComment;
    private Button mBtnFinddetailsComment;
    private ListView mLvFinddetailsComments;
    ArrayAdapter<String> adapter;
    private RecyclerView mRvFinddetailsCommentlist;
    List<FindDetailsComment> mFindDetailsCommentLists = new ArrayList<>();
    private ImageView mIvFinddetailsImage;
    private int imageId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_details);
        
        findId();


        Bundle extras = getIntent().getExtras();
        imageId = extras.getInt("imageId");
        String image = extras.getString("image");   //作品url
        String head = extras.getString("head");     //发布人头像url
        String name = extras.getString("name");     //发布人名字
        System.out.println(imageId + ", " + image + ", " + head + ", " + name);


        //加载作品
        Picasso.with(getApplicationContext()).load(image).into(mIvFinddetailsImage);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRvFinddetailsCommentlist.setLayoutManager(linearLayoutManager);

        //初始化评论数据
        initComment();

        FindDetailCommentsListsAdapter findDetailCommentsListsAdapter = new FindDetailCommentsListsAdapter(getApplicationContext(),mFindDetailsCommentLists);
        mRvFinddetailsCommentlist.setAdapter(findDetailCommentsListsAdapter);



        //给mBtnFinddetailsComment设置点击事件,获取发布的评论内容，并发送到服务器端
        mBtnFinddetailsComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        //1.获取mEtFinddetailsComment内容
                        String comment = mEtFinddetailsComment.getText().toString();


                        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                        int userId = preferences.getInt("uid", 1);
                        String userName = preferences.getString("name", "name");
                        String userAccount = preferences.getString("account", "account");
                        String userHeadportrait = preferences.getString("headportrait","headportrait");


                        try {
                            String urlCommentPath = (new Utils().URL)+"comment/?obj=12&cuid=" + URLEncoder.encode(String.valueOf(userId),"UTF-8")//&ruid="+ruId.getText().toString()
                                    +"&comment="+ URLEncoder.encode(comment,"UTF-8")
                                    +"&cdid="+URLEncoder.encode(String.valueOf(imageId),"UTF-8");
                            URL url = new URL(urlCommentPath);

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

    private void initComment() {

//        new  Thread(){
//            @Override
//            public void run() {
//                try {
//                    String urlCommentPath = Utils.URL + "comment/?obj=13&cdid=" + imageId;
//                    System.out.println("urlCommentPath:     " +urlCommentPath);
//                    //获取评论列表
//                    URL url = new URL(urlCommentPath);
//
//                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//                    if (conn.getResponseCode() == 200){
//
//                        //获得服务器响应数据
//                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
//
//                        //数据
//                        String retData = null;
//                        String responseData = "";
//                        while ((retData = in.readLine()) != null){
//                            responseData += retData;
//                        }
//
//                        System.out.println(responseData);
//
//                        JSONArray j = new JSONArray(responseData);
//                        for (int i = 0 ; i < j.length();i++){
//                            JSONObject item = j.getJSONObject(i);
//                            String uimage = item.getString("uimage");   //评论人头像
//                            String uname = item.getString("uname");     //评论人名字
//                            String comment = item.getString("comment"); //评论内容
//
//                            mFindDetailsCommentLists.add(new FindDetailsComment(Utils.URL + "upload/" + uimage,uname,"2015年9月10日", 0,comment));
//
//                        }
//                        Message msg = Message.obtain();
//                        msg.obj = mFindDetailsCommentLists;
//                        handler.sendMessage(msg);
//                        in.close();
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
        mFindDetailsCommentLists.add(new FindDetailsComment(Utils.URL + "upload/" + "18641120139138479.jpg","name","2015年9月10日", 0,"哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈"));
    }


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ArrayList<FindDetailsComment> obj = (ArrayList<FindDetailsComment>) msg.obj;

        }
    };
    private void findId() {
        mIvFinddetailsImage = (ImageView) findViewById(R.id.IvFinddetailsImage);
        mEtFinddetailsComment = (EditText) findViewById(R.id.EtFinddetailsComment);
        mBtnFinddetailsComment = (Button) findViewById(R.id.BtnFinddetailsComment);
        mRvFinddetailsCommentlist = (RecyclerView) findViewById(R.id.RvFinddetailsCommentlist);
    }
}
