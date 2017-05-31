package com.example.administrator.paintingfriends20.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.paintingfriends20.R;
import com.example.administrator.paintingfriends20.domain.Find;
import com.example.administrator.paintingfriends20.domain.FindDetailsComment;
import com.example.administrator.paintingfriends20.ui.FindDetailsActivity;

import java.util.List;

import static com.example.administrator.paintingfriends20.R.drawable.find;

/**
 * Created by Wng on 2017/5/23.
 */

public class FindDetailCommentsListsAdapter extends RecyclerView.Adapter<FindDetailCommentsListsAdapter.ViewHolder>{

    private List<FindDetailsComment> mCommentLists;


    /**
     * 自定义的内部类ViewHolder
     */
    static class ViewHolder extends RecyclerView.ViewHolder{


        private final View mCommentsListsView;
        private final ImageView mIvCommentitemUserpicture;
        private final TextView mTvCommentitemUsername;
        private final TextView mTvCommentitemDate;
        private final TextView mTvCommentitemLikenum;
        private final TextView mTvCommentitemContent;

        /**
         * @param itemView 表示RecyclerView子项的最外层布局
         */
        public ViewHolder(View itemView) {
            super(itemView);
            mCommentsListsView = itemView;
            mIvCommentitemUserpicture = (ImageView) itemView.findViewById(R.id.IvCommentitemUserpicture);
            mTvCommentitemUsername = (TextView) itemView.findViewById(R.id.TvCommentitemUsername);
            mTvCommentitemDate = (TextView) itemView.findViewById(R.id.TvCommentitemDate);
            mTvCommentitemLikenum = (TextView) itemView.findViewById(R.id.TvCommentitemLikenum);
            mTvCommentitemContent = (TextView) itemView.findViewById(R.id.TvCommentitemContent);

        }
    }

    /**
     * 用于把要展示的数据源传进来，并赋值给一个全区变量mCommentists
     * @param mCommentLists
     */
    public FindDetailCommentsListsAdapter(List<FindDetailsComment> mCommentLists) {
        this.mCommentLists = mCommentLists;
    }

    /**
     * 用于创建ViewHolder实例，我们在这个方法中将comment_item布局加载进来
     * 创建一个ViewHolder用来返回
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item,
                parent,false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    /**
     * 用于对RecyclerView子项的数据进行复制
     * 会在每个子项滚动到屏幕内的时候执行
     * 我们通过position参数得到当前的FindDetailsComment实例
     * 然后再将数据设置到ViewHolder的ImageView和TextView中
     * @param holder
     * @param position
     */

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FindDetailsComment findDetailsComment = mCommentLists.get(position);
        holder.mIvCommentitemUserpicture.setImageResource(findDetailsComment.getHeadPortrait());
        holder.mTvCommentitemUsername.setText(findDetailsComment.getName());
        holder.mTvCommentitemDate.setText(findDetailsComment.getDate());
        holder.mTvCommentitemLikenum.setText(String.valueOf(findDetailsComment.getLikeNum()));
        holder.mTvCommentitemContent.setText(findDetailsComment.getContent());

    }

    @Override
    public int getItemCount() {
        return mCommentLists.size();
    }


}

