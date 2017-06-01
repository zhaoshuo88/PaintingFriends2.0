package com.example.administrator.paintingfriends20.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.paintingfriends20.R;
import com.example.administrator.paintingfriends20.adapter.HomeLikeAdapter;
import com.example.administrator.paintingfriends20.adapter.MyRequestAdapter;
import com.example.administrator.paintingfriends20.domain.HomeLike;
import com.example.administrator.paintingfriends20.domain.MyRequestDomain;

import java.util.ArrayList;
import java.util.List;

public class MyRequestsActivity extends AppCompatActivity {

    private ListView mLvReqAdpview;
    private List<MyRequestDomain> requestLists = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrequest);

        mLvReqAdpview = (ListView) findViewById(R.id.LvReqAdpview);
        getrequestListsDate();
        MyRequestAdapter adapter = new MyRequestAdapter(getApplicationContext(), requestLists);
        mLvReqAdpview.setAdapter(adapter);
    }

    private void getrequestListsDate() {
        for (int i = 0; i < 20; i++) {
            requestLists .add(new MyRequestDomain("需求一需求一需求一需求一需求一需求一需求一需求一需求一需求一","2017年1月1日"));

        }
    }
}
