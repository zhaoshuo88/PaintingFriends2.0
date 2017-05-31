package com.example.administrator.paintingfriends20.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.paintingfriends20.R;
import com.example.administrator.paintingfriends20.adapter.RequestAdapter;
import com.example.administrator.paintingfriends20.domain.Request;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_request, container, false);

        findView();
        requestAdapter = new RequestAdapter(getActivity(),lRequest);

        if (mRequestList == null) {
            System.out.println("mRequestList为空~~~~~~~~~~~");
        }else if(requestAdapter == null){
            System.out.println("requestAdapter为空###################");
        }
        mRequestList.setAdapter(requestAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getData();

        mIvRequestAdd.setOnClickListener(new OnClick());
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
                    //5.显示  --最重要的一部
                    popup.show();
                    break;

            }
        }
    }
    private void findView() {
        mRequestList = (ListView)view.findViewById(R.id.LvReqAdpview);
        mIvRequestAdd = (ImageView) view.findViewById(R.id.IvRequestAdd);
        mHeadphoto = (ImageButton)view.findViewById(R.id.IbReqitemHeadphoto);
        mName = (TextView)view.findViewById(R.id.TvReqitemName);
        mRequest = (TextView)view.findViewById(R.id.TvReqitemRequest);
        mTime = (TextView)view.findViewById(R.id.TvReqitemTime);
    }
    private void getData(){
        for (int i = 0;i < 10; i++){
            int rid = i;
            String rdate = "2017-5-23";
            String redetail = "这个是详细需求需求";
            int ruid = 345678;
            String name = "画友第"+ (i+1) +"号用户";
            lRequest.add(new Request((long)rid,R.drawable.touxiang3,name,redetail,rdate));
            System.out.println(rid + rdate + redetail + ruid);

        }
    }
}
