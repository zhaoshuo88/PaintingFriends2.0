package com.example.administrator.paintingfriends20.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.paintingfriends20.R;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_request, container, false);

        findView();
        getData();

        requestAdapter = new RequestAdapter(getActivity(),lRequest);

        return view;
    }
    private void findView() {
        mRequestList = (ListView)view.findViewById(R.id.LvReqAdpview);
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
            mRequestList.setAdapter(requestAdapter);
        }
    }
}
