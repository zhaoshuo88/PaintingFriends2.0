package com.example.administrator.paintingfriends20.ui;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.paintingfriends20.R;
import com.example.administrator.paintingfriends20.adapter.HomeLikeAdapter;
import com.example.administrator.paintingfriends20.domain.HomeLike;

import java.util.ArrayList;
import java.util.List;

public class MyWorksActivity extends Activity {

    private List<HomeLike> mwLike = new ArrayList<>();
    private HomeLikeAdapter mwlikeAdapter;
    private GridView mwLikeList;

    private ImageView mwpic;
    private TextView mwname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mywork);

        mwLikeList = (GridView)findViewById(R.id.mywork_grid);
        mwpic = (ImageView)findViewById(R.id.pic );
        mwname = (TextView) findViewById(R.id.name);

        getmwLikeDate();

        mwlikeAdapter = new HomeLikeAdapter(this,mwLike);
        mwLikeList.setAdapter(mwlikeAdapter);
    }

    private void getmwLikeDate() {
        mwLike .add(new HomeLike(0,R.drawable.yuepai,"              张三  "));
        mwLike .add(new HomeLike(1,R.drawable.yuepai,"        啦啦啦啦啦了李四"));
        mwLike .add(new HomeLike(0,R.drawable.yuepai,"张三  "));
        mwLike .add(new HomeLike(1,R.drawable.yuepai,"李四"));
        mwLike .add(new HomeLike(0,R.drawable.yuepai,"张三  "));
        mwLike .add(new HomeLike(1,R.drawable.yuepai,"李四"));
        mwLike .add(new HomeLike(0,R.drawable.yuepai,"张三  "));
        mwLike .add(new HomeLike(1,R.drawable.yuepai,"李四"));
        mwLike .add(new HomeLike(0,R.drawable.yuepai,"张三  "));
        mwLike .add(new HomeLike(1,R.drawable.yuepai,"李四"));
        mwLike .add(new HomeLike(0,R.drawable.yuepai,"张三  "));
        mwLike .add(new HomeLike(1,R.drawable.yuepai,"李四"));
    }
}
