package com.example.administrator.paintingfriends20.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.administrator.paintingfriends20.R;
import com.example.administrator.paintingfriends20.domain.Request;
import com.example.administrator.paintingfriends20.ui.OtherRequestsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Loktar on 2017/5/23.
 */
public class RequestAdapter extends BaseAdapter {

    private Context context;
    private List<Request> lRequest = new ArrayList<>();

    public RequestAdapter(Context context, List<Request> lRequest) {
        this.context = context;
        this.lRequest = lRequest;
    }

    @Override
    public int getCount() {
        return lRequest.size();
    }

    @Override
    public Object getItem(int i) {
        return lRequest.get(i);
    }

    @Override
    public long getItemId(int i) {
        return lRequest.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v;
        if(null == view){
            v = LayoutInflater.from(context).inflate(R.layout.layout_request_item,null);

        }else {
            v = view;
        }
        final Request request = lRequest.get(i);
        ImageButton headPhoto = (ImageButton) v.findViewById(R.id.IbReqitemHeadphoto);
        headPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long rid = request.getId();
                Intent intent = new Intent();
                intent.setClass(context, OtherRequestsActivity.class);
                intent.putExtra("rid",rid);
                context.startActivity(intent);
            }
        });
        Picasso.with(context).load(request.getHeadPhoto()).into(headPhoto);
        TextView TvName = (TextView) v.findViewById(R.id.TvReqitemName);
        TvName.setText(request.getName());
        TextView TvRequest = (TextView) v.findViewById(R.id.TvReqitemRequest);
        TvRequest.setText(request.getRequest());
        TextView TvTime = (TextView) v.findViewById(R.id.TvReqitemTime);
        TvTime.setText(request.getTime());

        return v;
    }
}
