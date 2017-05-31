package com.example.administrator.paintingfriends20.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.administrator.paintingfriends20.MainActivity;
import com.example.administrator.paintingfriends20.R;
import com.example.administrator.paintingfriends20.adapter.FindAdapter;
import com.example.administrator.paintingfriends20.domain.Find;
import com.example.administrator.paintingfriends20.ui.PutRequestActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15530 on 2017/5/21.
 */

public class FindFragment extends Fragment {

    private View view;
    List<Find> findLists = new ArrayList<>();
    private ImageView mIvFindAdd;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.activity_find,container,false);

        initView();
        recyclerView= (RecyclerView) view.findViewById(R.id.RvPictureShow);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        FindAdapter adapter = new FindAdapter(findLists);
        recyclerView.setAdapter(adapter);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        findId();


        mIvFindAdd.setOnClickListener(new OnClick());
    }


    class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.IvFindAdd:

                    //1.创建弹出式菜单对象
                    PopupMenu popup = new PopupMenu(getActivity(), v);
                    //2.获取菜单填充器
                    MenuInflater inflater = popup.getMenuInflater();
                    //3.填充菜单
                    inflater.inflate(R.menu.find_popupmenu,popup.getMenu());
                    //4.绑定菜单项的点击事件
                    popup.setOnMenuItemClickListener(new MenuItemClick());
                    //5.显示  --最重要的一部
                    popup.show();
                    break;

            }
        }
    }

    class  MenuItemClick implements PopupMenu.OnMenuItemClickListener{

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                case R.id.add_find_item:
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), PutRequestActivity.class);
                    startActivity(intent);
                    break;
            }
            return false;
        }
    }
    private void findId() {
        mIvFindAdd = (ImageView) view.findViewById(R.id.IvFindAdd);
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
