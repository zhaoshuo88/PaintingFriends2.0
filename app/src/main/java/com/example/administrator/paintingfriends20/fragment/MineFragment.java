package com.example.administrator.paintingfriends20.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.administrator.paintingfriends20.R;
import com.example.administrator.paintingfriends20.ui.MyRequestsActivity;
import com.example.administrator.paintingfriends20.ui.MyWorksActivity;

/**
 * Created by 15530 on 2017/5/21.
 */

public class MineFragment extends Fragment {

    private View view;
    private RelativeLayout mRlayMineWorks;
    private RelativeLayout mRlayMineRequests;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.activity_mine,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        findId();

        mRlayMineWorks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),MyWorksActivity.class);
                startActivity(intent);
            }
        });
        mRlayMineRequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MyRequestsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void findId() {
        mRlayMineWorks = (RelativeLayout) view.findViewById(R.id.RlayMineWorks);
        mRlayMineRequests = (RelativeLayout) view.findViewById(R.id.RlayMineRequests);
    }
}
