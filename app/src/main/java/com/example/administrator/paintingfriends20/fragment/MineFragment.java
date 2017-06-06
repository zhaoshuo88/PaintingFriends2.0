package com.example.administrator.paintingfriends20.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.paintingfriends20.R;
import com.example.administrator.paintingfriends20.ui.MyRequestsActivity;
import com.example.administrator.paintingfriends20.ui.MyWorksActivity;
import com.example.administrator.paintingfriends20.utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by 15530 on 2017/5/21.
 */

public class MineFragment extends Fragment {

    private View view;
    private RelativeLayout mRlayMineRequest;
    private RelativeLayout mRlayMineWorks;
    private TextView mTvMineName;
    private String name;
    private ImageView mIvMineHeadportrait;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.activity_mine,container,false);
        findId();
        mRlayMineRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MyRequestsActivity.class);
                startActivity(intent);
            }
        });

        mRlayMineWorks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MyWorksActivity.class);
                startActivity(intent);
            }
        });
        
        
        return view;
    }

    private void findId() {
        mTvMineName = (TextView) view.findViewById(R.id.TvMineName);
        mRlayMineRequest = (RelativeLayout) view.findViewById(R.id.RlayMineRequest);
        mRlayMineWorks = (RelativeLayout) view.findViewById(R.id.RlayMineWorks);
        mIvMineHeadportrait = (ImageView)view.findViewById(R.id.IvMineHeadportrait);
        
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SharedPreferences preferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        name = preferences.getString("name", "name");
        String headportrait = preferences.getString("headportrait","headportrait");

        mTvMineName.setText(name);

        HeadThread headThread = new HeadThread();
        headThread.start();

    }


    //设置新线程，用来加载头像
    private class HeadThread extends Thread{
        @Override
        public void run() {
            super.run();
            System.out.println("从服务器获取头像");
            try {
                String headUrlString = Utils.URL + "user/?obj=11&uname=" + URLEncoder.encode(name,"UTF-8");

                //从网络上下载图片
                URL headUrl = new URL(headUrlString);

                HttpURLConnection conn = (HttpURLConnection) headUrl.openConnection();

                if (conn.getResponseCode() == 200){
                    //获得服务器响应数据
                    System.out.println("服务器响应成功");
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));

                    //数据拼接
                    String retData = null;
                    String responseData = "";
                    while ((retData = in.readLine()) != null){
                        responseData += retData;
                    }


                    JSONArray j = new JSONArray(responseData);
                    for (int i = 0 ; i < j.length();i++){
                        JSONObject item = j.getJSONObject(i);
                        String uimage = item.getString("uimage");   //用户头像
                        URL urldown = new URL(Utils.URL + "upload/" + uimage);
                        File file = new File(getActivity().getCacheDir(), Base64.encodeToString(urldown.toString().getBytes(), Base64.DEFAULT));
                        if (file.exists() && file.length() > 0){
                            System.out.println("使用缓存图片");
                            //使用缓存图片
                            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

                            Message msg = Message.obtain();
                            msg.obj = bitmap;
                            handler.sendMessage(msg);
                        }else {
                            //从服务器端获取图片
                            System.out.println("使用网络图片");
                            HttpURLConnection httpURLConnection = (HttpURLConnection) urldown.openConnection();

                            //1.设置请求方式
                            httpURLConnection.setRequestMethod("GET");

                            //2.设置请求时间
                            httpURLConnection.setConnectTimeout(5000);

                            //3.设置请求码
                            int code = httpURLConnection.getResponseCode();
                            if (code == 200){
                                //请求成功
                                InputStream inputStream = httpURLConnection.getInputStream();

                                //文件缓存

                                FileOutputStream fos = new FileOutputStream(file);
                                int len;
                                byte[] buffer = new byte[1024];
                                while((len = inputStream.read(buffer)) != -1){
                                    fos.write(buffer,0,len);
                                }

                                fos.close();
                                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                                Message msg = Message.obtain();
                                msg.obj = bitmap;
                                handler.sendMessage(msg);


                            }
                        }

                    }



                    in.close();

                }



            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            System.out.println("加载头像成功");
            super.handleMessage(msg);
            Bitmap bitmap = (Bitmap) msg.obj;
            mIvMineHeadportrait.setImageBitmap(bitmap);
        }
    };
}
