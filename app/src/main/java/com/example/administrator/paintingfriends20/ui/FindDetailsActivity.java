package com.example.administrator.paintingfriends20.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.administrator.paintingfriends20.R;
import com.example.administrator.paintingfriends20.adapter.FindAdapter;
import com.example.administrator.paintingfriends20.adapter.FindDetailCommentsListsAdapter;
import com.example.administrator.paintingfriends20.domain.FindDetailsComment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15530 on 2017/5/23.
 */

public class FindDetailsActivity extends Activity {

    private EditText mEtFinddetailsComment;
    private Button mBtnFinddetailsComment;
    private ListView mLvFinddetailsComments;
    List<String> data = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    private RecyclerView mRvFinddetailsCommentlist;
    List<FindDetailsComment> mFindDetailsCommentLists = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_details);
        
        findId();
        //初始化评论数据
        initComment();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRvFinddetailsCommentlist.setLayoutManager(linearLayoutManager);
        FindDetailCommentsListsAdapter findDetailCommentsListsAdapter = new FindDetailCommentsListsAdapter(mFindDetailsCommentLists);
        mRvFinddetailsCommentlist.setAdapter(findDetailCommentsListsAdapter);
//        //给mBtnFinddetailsComment设置点击事件
//        mBtnFinddetailsComment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new Thread(){
//                    @Override
//                    public void run() {
//                        super.run();
//                        //1.获取mEtFinddetailsComment内容
//                        String comment = mEtFinddetailsComment.getText().toString();
//                        System.out.println(comment + "~~~~~~~~~~~~~~~~~~~~~~~~~~");
//                        Message message = Message.obtain();
//                        message.obj = comment;
//                        handler.sendMessage(message);
//                    }
//                }.start();
//
//
//
//            }
//        });
    }

    private void initComment() {
        mFindDetailsCommentLists.add(new FindDetailsComment(R.drawable.touxiang9,"张三","2015年9月10日",
                225,"哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈"));
        mFindDetailsCommentLists.add(new FindDetailsComment(R.drawable.touxiang4,"张三","2015年9月10日",
                225,"哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈"));
        mFindDetailsCommentLists.add(new FindDetailsComment(R.drawable.touxiang5,"张三","2015年9月10日",
                225,"哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈"));
        mFindDetailsCommentLists.add(new FindDetailsComment(R.drawable.touxiang6,"张三","2015年9月10日",
                225,"哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈"));
        mFindDetailsCommentLists.add(new FindDetailsComment(R.drawable.touxiang3,"张三","2015年9月10日",
                225,"哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈"));
        mFindDetailsCommentLists.add(new FindDetailsComment(R.drawable.touxiang1,"张三","2015年9月10日",
                225,"哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈"));
        mFindDetailsCommentLists.add(new FindDetailsComment(R.drawable.touxiang4,"张三","2015年9月10日",
                225,"哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈"));
        mFindDetailsCommentLists.add(new FindDetailsComment(R.drawable.touxiang8,"张三","2015年9月10日",
                225,"哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈"));
        mFindDetailsCommentLists.add(new FindDetailsComment(R.drawable.touxiang3,"张三","2015年9月10日",
                225,"哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈"));
        mFindDetailsCommentLists.add(new FindDetailsComment(R.drawable.touxiang1,"张三","2015年9月10日",
                225,"哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈"));
        
    }
//
//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            String comment = (String) msg.obj;
//
//
//            //2.将获取的评论添加到data中
//            data.add(comment);
//            System.out.println(data);
//            //3.设置评论列表适配器
//            adapter = new ArrayAdapter<>(FindDetailsActivity.this, android.R.layout.simple_list_item_1, data);
//            //4.绑定适配器
////            mLvFinddetailsComments.setAdapter(adapter);
//
//        }
//    };
    private void findId() {
        mEtFinddetailsComment = (EditText) findViewById(R.id.EtFinddetailsComment);
        mBtnFinddetailsComment = (Button) findViewById(R.id.BtnFinddetailsComment);
        mRvFinddetailsCommentlist = (RecyclerView) findViewById(R.id.RvFinddetailsCommentlist);
    }
}
