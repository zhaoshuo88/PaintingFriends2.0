package com.example.administrator.paintingfriends20.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.paintingfriends20.R;
import com.example.administrator.paintingfriends20.domain.Find;

import java.util.List;

/**
 * Created by Wng on 2017/5/23.
 */

public class FindAdapter extends RecyclerView.Adapter<FindAdapter.ViewHolder>{

    private List<Find> mFindLists;


    static class ViewHolder extends RecyclerView.ViewHolder{

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
        }
    }

    /**
     * 用于把要展示的数据源传进来，并赋值给一个全区变量m
     * @param mFindLists
     */
    public FindAdapter(List<Find> mFindLists) {
        this.mFindLists = mFindLists;
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
        ViewHolder holder = new ViewHolder(view);
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
        Find find = mFindLists.get(position);
        holder.mFindPictureImage.setImageResource(find.getImage());
        holder.mFindHeadPortrait.setImageResource(find.getHeadPortrait());
        holder.mFindName.setText(find.getName());
    }

    @Override
    public int getItemCount() {
        return mFindLists.size();
    }


}

