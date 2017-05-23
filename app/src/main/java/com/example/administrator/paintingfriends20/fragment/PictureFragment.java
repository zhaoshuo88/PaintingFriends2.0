package com.example.administrator.paintingfriends20.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.administrator.paintingfriends20.R;
import com.example.administrator.paintingfriends20.domain.PictureAdapter;
import com.example.administrator.paintingfriends20.domain.Pricture;

import java.util.ArrayList;

/**
 * Created by 15530 on 2017/5/21.
 */

public class PictureFragment extends Fragment {

    private View view;

    private GridView mGridView;
    private PictureAdapter mPictureAdapter;
    private int[] pictureIds = new int[]{1,2,3,4};
    private int[] pictureHeadPortraits = new int[]{1,2,3,4};
    private String[] pictureNames = new String[]{"1","2","3","4"};
    private int[] pictureImages = new int[]{R.drawable.find_1,R.drawable.find_1,R.drawable.find_1,R.drawable.find_1};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.activity_picture,container,false);

        initView();
        return view;
    }

    private void initView(){
        mGridView = (GridView)view.findViewById(R.id.picfrage_gv);
        ArrayList<Pricture> pictureList = new ArrayList<>();
        for(int i = 0;i < pictureNames.length;i++){
            Pricture pricture = new Pricture(pictureIds[i],pictureHeadPortraits[i],pictureNames[i],pictureImages[i]);
            pictureList.add(pricture);
        }
        mPictureAdapter = new PictureAdapter(getActivity(),pictureList);
        mGridView.setAdapter(mPictureAdapter);
    }
}
