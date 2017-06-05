package com.example.administrator.paintingfriends20.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.paintingfriends20.R;
import com.example.administrator.paintingfriends20.ui.MyRequestsActivity;

/**
 * Created by 15530 on 2017/5/21.
 */

public class MineFragment extends Fragment {

    private View view;
    private TextView mTvMineMyrequest;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.activity_mine,container,false);
        mTvMineMyrequest = (TextView) view.findViewById(R.id.TvMineMyrequest);
        mTvMineMyrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyRequestIntent();
            }
        });
        return view;
    }
    private void MyRequestIntent(){
        Intent intent = new Intent();
        intent.setClass(getActivity(), MyRequestsActivity.class);
        startActivity(intent);

    }
}
