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
import com.example.administrator.paintingfriends20.ui.Mine;
import com.example.administrator.paintingfriends20.ui.MyRequestsActivity;
import com.example.administrator.paintingfriends20.ui.MyWorksActivity;
import com.example.administrator.paintingfriends20.utils.Utils;
import com.squareup.picasso.Picasso;

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
    private RelativeLayout personal;
    private String headportrait;
    private int uid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.activity_mine,container,false);
        findId();
        SharedPreferences preferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        uid = preferences.getInt("uid", 1);     //用户ID
        name = preferences.getString("name", "name");   //用户名
        headportrait = preferences.getString("headportrait","headportrait"); //用户头像
//        personal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(getActivity(),Mine.class);
//                startActivity(intent);
//            }
//        });
//        mRlayMineRequest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(getActivity(), MyRequestsActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        mRlayMineWorks.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(getActivity(), MyWorksActivity.class);
//                startActivity(intent);
//            }
//        });


        return view;
    }

    private void findId() {
        personal = (RelativeLayout) view.findViewById(R.id.Personal);
        mTvMineName = (TextView) view.findViewById(R.id.TvMineName);
        mRlayMineRequest = (RelativeLayout) view.findViewById(R.id.RlayMineRequest);
        mRlayMineWorks = (RelativeLayout) view.findViewById(R.id.RlayMineWorks);
        mIvMineHeadportrait = (ImageView)view.findViewById(R.id.IvMineHeadportrait);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        mTvMineName.setText(name);

        Picasso.with(getActivity()).load(Utils.URL + "upload/" + headportrait).into(mIvMineHeadportrait);
//        HeadThread headThread = new HeadThread();
//        headThread.start();

        //设置监听
        personal.setOnClickListener(new OnClick());
        mRlayMineWorks.setOnClickListener(new OnClick());
        mRlayMineRequest.setOnClickListener(new OnClick());

    }

    class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.RlayMineRequest:
                    Intent requestIntent = new Intent();
                    requestIntent.putExtra("uid",uid);
                    requestIntent.setClass(getActivity(), MyRequestsActivity.class);
                    startActivity(requestIntent);
                    break;
                case R.id.RlayMineWorks:
                    Intent worksIntent = new Intent();
                    worksIntent.putExtra("name",name);
                    worksIntent.setClass(getActivity(), MyWorksActivity.class);
                    startActivity(worksIntent);
                    break;
                case R.id.Personal:
                    Intent personalIntent = new Intent();
                    personalIntent.putExtra("uid",uid);
                    personalIntent.setClass(getActivity(), MyWorksActivity.class);
                    startActivity(personalIntent);
                    break;
            }

        }
    }


}
