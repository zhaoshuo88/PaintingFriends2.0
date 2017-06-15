package com.example.administrator.paintingfriends20.fragment;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.administrator.paintingfriends20.R;
import com.example.administrator.paintingfriends20.adapter.HomeLikeAdapter;
import com.example.administrator.paintingfriends20.domain.HomeLike;
import com.example.administrator.paintingfriends20.ui.FindDetailsActivity;
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
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class HomeFragment extends Fragment {
    private ViewFlipper flipper;
    private int[] resId = {R.drawable.yuepai,R.drawable.lala,R.drawable.gg,R.drawable.ss,R.drawable.hh};//轮播图图片
    //最新需求
    private TextView num;
    private TextView title;
    private TextView request;
    private TextView phone;
    private TextView time;

    private ListView mRequestList;
    private String urlRequestfragmentPath;
    private URL url;
    //点赞榜
    private ImageView pic;
    private ImageView number;
    private TextView name;
    private List<HomeLike> lLike = new ArrayList<>();
    private HomeLikeAdapter likeAdapter;
    private GridView mLikeList;
    private int uid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home, container, false);

        flipper = (ViewFlipper)view.findViewById(R.id.flipper);//轮播图
        //最新需求

        //点赞榜
        mLikeList = (GridView)view.findViewById(R.id.grid);
        pic = (ImageView)view.findViewById(R.id.IvHomelikeImage );
/*        number = (ImageView) view.findViewById(R.id.number );*/
        name = (TextView) view.findViewById(R.id.TvHomelikeName );

        SharedPreferences preferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        uid = preferences.getInt("uid", 1);     //用户ID
//        name = preferences.getString("name", "name");   //用户名
//        headportrait = preferences.getString("headportrait","headportrait"); //用户头像
        getLikeDate();//获取点赞榜数据
        likeAdapter = new HomeLikeAdapter(getActivity(),lLike);
        mLikeList.setAdapter(likeAdapter);


        //首页轮播图代码
        /*
        * 动态导入的方式为ViewFlipper加入子View
        * */
        for (int i = 0; i < resId.length; i++) {
            flipper.addView(getImageView(resId[i]));
        }

        /*
        * 为ViewFlipper去添加动画效果
        * */
        TranslateAnimation rightInAnim = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        rightInAnim.setDuration(1000);
        TranslateAnimation leftOutAnim = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        leftOutAnim.setDuration(1000);
        flipper.setInAnimation(rightInAnim);
        flipper.setOutAnimation(leftOutAnim);
        flipper.setFlipInterval(2000);
        flipper.startFlipping();


        /**
         * 设置监听
         */
//        mLikeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent();
//                intent.setClass(view.getContext(), FindDetailsActivity.class);
//                startActivity(intent);
//            }
//        });
        return view;
        //首页轮播图代码



    }
    private ImageView getImageView(int resId) {
        ImageView image = new ImageView(getActivity());
        /*    ImageView image = new HomeFragment().getImageView(resId);*/
        image.setBackgroundResource(resId);
        return image;
    }


    private void getLikeDate() {

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    String urlMyWorksPath = Utils.URL + "draw/?obj=10";

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

                            int did = item.getInt("did");
                            String durl = item.getString("durl");   //作品名字
                            String uname = item.getString("uname");

                            lLike .add(new HomeLike(did,Utils.URL + "upload/" + durl,uname));
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


