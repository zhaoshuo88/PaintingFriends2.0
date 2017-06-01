package com.example.administrator.paintingfriends20.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.administrator.paintingfriends20.R;
import com.example.administrator.paintingfriends20.domain.MyRequestDomain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Loktar on 2017/6/1.
 */
public class MyRequestAdapter extends BaseAdapter {
    private Context context;
    private List<MyRequestDomain> lRequest = new ArrayList<>();

    public MyRequestAdapter(Context context, List<MyRequestDomain> lRequest) {
        this.context = context;
        this.lRequest = lRequest;
    }

    @Override
    public int getCount() {
        return lRequest.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v;
        if(null == convertView){
            v = LayoutInflater.from(context).inflate(R.layout.my_request_item,null);

        }else {
            v = convertView;
        }
        TextView mTvMyrequestitemContent = (TextView) v.findViewById(R.id.TvMyrequestitemContent);
        mTvMyrequestitemContent.setText(lRequest.get(position).getRequest());
        TextView mTvMyrequestitemTime = (TextView) v.findViewById(R.id.TvMyrequestitemTime);
        mTvMyrequestitemTime.setText(lRequest.get(position).getTime());

        return v;

    }

}
