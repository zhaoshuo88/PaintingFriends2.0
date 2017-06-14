package com.example.administrator.paintingfriends20.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.paintingfriends20.R;
import com.example.administrator.paintingfriends20.domain.MyWork;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyWorkAdapter extends BaseAdapter {

    private Context context;
    private List<MyWork> lLike = new ArrayList<>();

    public MyWorkAdapter(Context context, List<MyWork> lLike) {
        this.context = context;
        this.lLike = lLike;
    }
////
    @Override
    public int getCount() {
        return lLike.size();
    }

    @Override
    public Object getItem(int i) {
        return lLike.get(i);
    }

    @Override
    public long getItemId(int i) {
        return lLike.get(i).getId();
    }
//
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(null == view){
            view = LayoutInflater.from(context).inflate(R.layout.layout_my_work_item,null);
        }

        ImageView pic = (ImageView) view.findViewById(R.id.IvMyworkitemImage );
        Picasso.with(context).load(lLike.get(i).getPic()).into(pic);
/*        ImageView number = (ImageView) view.findViewById(R.id.number );
        number .setImageResource(lLike.get(i).getNumber());*/
        TextView name = (TextView) view.findViewById(R.id.TvMyworkitemName );
        name .setText(String.valueOf(lLike.get(i).getLike()));

        return view;
    }
}

