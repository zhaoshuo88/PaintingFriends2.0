package com.example.administrator.paintingfriends20.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.paintingfriends20.R;
import com.example.administrator.paintingfriends20.domain.Find;
import com.example.administrator.paintingfriends20.ui.FindDetailsActivity;
import com.example.administrator.paintingfriends20.ui.MyWorksActivity;
import com.example.administrator.paintingfriends20.ui.OtherWorksActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Wng on 2017/5/23.
 */

public class FindAdapter extends RecyclerView.Adapter<FindAdapter.ViewHolder>{

    private List<Find> mFindLists;
    private Context context;


    static class ViewHolder extends RecyclerView.ViewHolder {

        View mFindPictureShowView;
        ImageView mFindPictureImage;
        ImageView mFindHeadPortrait;
        TextView mFindName;

        /**
         * @param itemView 表示RecyclerView子项的最外层布局
         */
        public ViewHolder(View itemView) {
            super(itemView);
            mFindPictureShowView = itemView;
            mFindPictureImage = (ImageView) itemView.findViewById(R.id.IvFindshowitemImage);
            mFindHeadPortrait = (ImageView) itemView.findViewById(R.id.IvFindshowitemHeadportrait);
            mFindName = (TextView) itemView.findViewById(R.id.TvFindshowitemName);

//            mFindPictureImage.setOnClickListener(this);
//            mFindHeadPortrait.setOnClickListener(this);
//            mFindName.setOnClickListener(this);
//
//            mFindPictureImage.setTag(this);
//            mFindHeadPortrait.setTag(this);
//            mFindName.setTag(this);
        }

//        @Override
//        public void onClick(View v) {
//            ViewHolder tag = (ViewHolder) v.getTag();
//            Intent intent = new Intent(v.getContext(), FindDetailsActivity.class);
//            Bundle bundle = new Bundle();
//
//            intent.putExtra("name",tag.mFindName.getText().toString());
//            intent.putExtra("headphoto",(ImageView)v.getResources());
//            intent.putExtra("name",tag.mFindName.toString());
//            intent.putExtra("")
//        }
    }

    /**
     * 用于把要展示的数据源传进来，并赋值给一个全区变量m
     * @param mFindLists
     */
//    public FindAdapter(List<Find> mFindLists) {
//        this.mFindLists = mFindLists;
//    }


    public FindAdapter(List<Find> mFindLists, Context context) {
        this.mFindLists = mFindLists;
        this.context = context;
    }

    /**
     * 用于创建ViewHolder实例，我们在这个方法中将find_show_item布局加载进来
     * 创建一个ViewHolder用来返回
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.find_show_item,
                parent,false);
        final ViewHolder holder = new ViewHolder(view);

//        holder.mFindPictureImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(v.getContext(), FindDetailsActivity.class);
//                intent.putExtra(fin)
//                v.getContext().startActivity(intent);
//            }
//        });
        return holder;
    }

    /**
     * 用于对RecyclerView子项的数据进行复制
     * 会在每个子项滚动到屏幕内的时候执行
     * 我们通过position参数得到当前的Find实例
     * 然后再将数据设置到ViewHolder的ImageView和TextView中
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Find find = mFindLists.get(position);
        Picasso.with(context).load(find.getImage()).placeholder(R.drawable.placheholder).into(holder.mFindPictureImage);
        holder.mFindPictureImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("imageId",find.getId());
                intent.putExtra("image",find.getImage());
                intent.putExtra("head",find.getHeadPortrait());
                intent.putExtra("name",find.getName());

                intent.setClass(context,FindDetailsActivity.class);
                context.startActivity(intent);
            }
        });
//        holder.mFindPictureImage.setImageBitmap(find.getImage());
        Picasso.with(context).load(find.getHeadPortrait()).into(holder.mFindHeadPortrait);
        holder.mFindHeadPortrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
//                intent.putExtra("imageId",find.getId());
//                intent.putExtra("image",find.getImage());
//                intent.putExtra("head",find.getHeadPortrait());
                intent.putExtra("name",find.getName());
                intent.setClass(context, OtherWorksActivity.class);
                context.startActivity(intent);
            }
        });
//        holder.mFindHeadPortrait.setImageResource(find.getHeadPortrait());
        holder.mFindName.setText(find.getName());
    }

    @Override
    public int getItemCount() {
        return mFindLists.size();
    }


}

