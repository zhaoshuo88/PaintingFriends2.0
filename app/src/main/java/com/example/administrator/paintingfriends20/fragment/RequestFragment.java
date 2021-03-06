package com.example.administrator.paintingfriends20.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.paintingfriends20.R;
import com.example.administrator.paintingfriends20.adapter.RequestAdapter;
import com.example.administrator.paintingfriends20.domain.Request;
import com.example.administrator.paintingfriends20.ui.MyRequestsActivity;
import com.example.administrator.paintingfriends20.ui.PutRequestActivity;
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

/**
 * Created by 15530 on 2017/5/21.
 */

public class RequestFragment extends Fragment {

    private View view;
    private ImageButton mHeadphoto;
    private TextView mName;
    private TextView mRequest;
    private TextView mTime;
    private List<Request> lRequest = new ArrayList<>();
    private RequestAdapter requestAdapter;
    private ListView mRequestList;
    private ImageView mIvRequestAdd;
    private String urlRequestfragmentPath;
    private RelativeLayout mRlayRequestitemView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_request, container, false);

        findView();
        getData();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mIvRequestAdd.setOnClickListener(new OnClick());
        mRequestList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
//                intent.setClass(getActivity(), );
            }
        });
    }

    class  MenuItemClick implements PopupMenu.OnMenuItemClickListener{

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                case R.id.add_request_item:
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), PutRequestActivity.class);
//                    startActivity(intent);
                    startActivityForResult(intent,1);
                    break;

                case R.id.remove_request_item:
                    Intent removeRequestIntent = new Intent();
                    removeRequestIntent.setClass(getActivity(), MyRequestsActivity.class);
                    startActivity(removeRequestIntent);
                    break;
            }
            return false;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2){
           getData();
        }
    }

    class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.IvRequestAdd:

                    //1.创建弹出式菜单对象
                    PopupMenu popup = new PopupMenu(getActivity(), v);
                    //2.获取菜单填充器
                    MenuInflater inflater = popup.getMenuInflater();
                    //3.填充菜单
                    inflater.inflate(R.menu.request_popupmenu,popup.getMenu());
                    //4.绑定菜单项的点击事件
                    popup.setOnMenuItemClickListener(new MenuItemClick());
                    //5.显示  --最重要的一部
                    popup.show();
                    break;


            }
        }
    }
    private void findView() {
        mRequestList = (ListView)view.findViewById(R.id.LvReqAdpview);
        mIvRequestAdd = (ImageView) view.findViewById(R.id.IvRequestAdd);
        mRlayRequestitemView = (RelativeLayout)view.findViewById(R.id.RlayRequestitemView);
        mHeadphoto = (ImageButton)view.findViewById(R.id.IbReqitemHeadphoto);
        mName = (TextView)view.findViewById(R.id.TvReqitemName);
        mRequest = (TextView)view.findViewById(R.id.TvReqitemRequest);
        mTime = (TextView)view.findViewById(R.id.TvReqitemTime);
    }
    private void getData(){

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    urlRequestfragmentPath = Utils.URL + "request/?obj=5";

                    URL url = new URL(urlRequestfragmentPath);

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
                        //通知主线程更新UI
                        Message message = new Message();
                        message.what = 1;
                        message.obj = responseData;
                        handler.sendMessage(message);

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
            if (msg.what == 1){
                JSONArray j = null;
                try {
                    j = new JSONArray((String)msg.obj);
                    for (int i = 0 ; i < j.length();i++){
                        JSONObject item = j.getJSONObject(i);
                        int rid=item.getInt("rid");
                        String rdate = item.getString("rdate");
                        String redetail = URLDecoder.decode(item.getString("rdetail"),"utf-8");

                        int ruid = item.getInt("ruid");
                        String name = item.getString("uname");
                        String uimage = item.getString("uimage");   //用户头像
                        lRequest.add(new Request((long)rid,Utils.URL + "upload/" + uimage,name,redetail,rdate));

                    }

                    requestAdapter = new RequestAdapter(getActivity(),lRequest);
                    mRequestList.setAdapter(requestAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


            }
        }
    };
}
