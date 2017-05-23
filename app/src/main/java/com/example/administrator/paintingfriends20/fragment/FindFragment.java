package com.example.administrator.paintingfriends20.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.administrator.paintingfriends20.R;
import com.example.administrator.paintingfriends20.adapter.FindAdapter;
import com.example.administrator.paintingfriends20.domain.Find;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15530 on 2017/5/21.
 */

public class FindFragment extends Fragment {

    private View view;


    List<Find> findLists = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.activity_find,container,false);

        initView();
        RecyclerView recyclerView= (RecyclerView) view.findViewById(R.id.RvPictureShow);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        FindAdapter adapter = new FindAdapter(findLists);
        recyclerView.setAdapter(adapter);
        return view;
    }



    private void initView(){


        findLists.add(new Find(1,R.drawable.touxiang1,"张三",R.drawable.details_picture));
        findLists.add(new Find(2,R.drawable.touxiang3,"张三",R.drawable.find_2));
        findLists.add(new Find(3,R.drawable.touxiang1,"张三",R.drawable.find_3));
        findLists.add(new Find(4,R.drawable.touxiang3,"张三",R.drawable.find_4));
        findLists.add(new Find(5,R.drawable.touxiang1,"张三",R.drawable.details_picture));
        findLists.add(new Find(6,R.drawable.touxiang3,"张三",R.drawable.find_1));
        findLists.add(new Find(7,R.drawable.touxiang1,"张三",R.drawable.find_3));
        findLists.add(new Find(8,R.drawable.touxiang1,"张三",R.drawable.find_2));
        findLists.add(new Find(9,R.drawable.touxiang1,"张三",R.drawable.details_picture));
        findLists.add(new Find(10,R.drawable.touxiang1,"张三",R.drawable.find_1));



    }
}
