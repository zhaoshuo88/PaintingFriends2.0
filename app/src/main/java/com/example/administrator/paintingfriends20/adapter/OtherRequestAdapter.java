package com.example.administrator.paintingfriends20.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.paintingfriends20.R;
import com.example.administrator.paintingfriends20.domain.MyRequest;
import com.example.administrator.paintingfriends20.domain.OtherRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Loktar on 2017/6/1.
 */
public class OtherRequestAdapter extends BaseAdapter {
    private Context context;
    private List<OtherRequest> lRequest = new ArrayList<>();

    public OtherRequestAdapter(Context context, List<OtherRequest> lRequest) {
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
            v = LayoutInflater.from(context).inflate(R.layout.others_request_item,null);

        }else {
            v = convertView;
        }
        TextView mTvOtherrequestitemContent = (TextView) v.findViewById(R.id.TvOtherrequestitemContent);
        mTvOtherrequestitemContent.setText(lRequest.get(position).getRequest());
        TextView mTvOtherrequestitemTime = (TextView) v.findViewById(R.id.TvOtherrequestitemTime);
        mTvOtherrequestitemTime.setText(lRequest.get(position).getTime());

        return v;

    }

}
