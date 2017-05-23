package com.example.administrator.paintingfriends20.domain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.administrator.paintingfriends20.R;

import java.util.ArrayList;

/**
 * Created by Wng on 2017/5/23.
 */

public class PictureAdapter extends BaseAdapter {

    private ArrayList<Pricture> picture;
    private LayoutInflater layoutInflater;

    public PictureAdapter(Context context, ArrayList<Pricture> picture){
        this.picture = picture;
        layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return picture.size();
    }

    @Override
    public Object getItem(int position) {
        return picture.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.picture_grid_view_item_layout,null);
            holder = new ViewHolder();
            holder.icon = (ImageView)convertView.findViewById(R.id.image);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        Pricture pricture = picture.get(position);
        if(picture != null ){
            holder.icon.setImageAlpha(pricture.getImage());
        }
        return convertView;
    }

    static class ViewHolder {
        ImageView icon;
    }
}
