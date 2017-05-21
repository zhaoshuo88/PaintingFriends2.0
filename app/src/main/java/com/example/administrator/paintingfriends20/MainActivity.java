package com.example.administrator.paintingfriends20;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.administrator.paintingfriends20.R;
import com.example.administrator.paintingfriends20.fragment.HomeFragment;
import com.example.administrator.paintingfriends20.fragment.MineFragment;
import com.example.administrator.paintingfriends20.fragment.PictureFragment;
import com.example.administrator.paintingfriends20.fragment.RequestFragment;
import com.example.administrator.paintingfriends20.utils.Utils;

public class MainActivity extends Activity {

    private int[] image0 = new int[]{
            R.drawable.home,
            R.drawable.picture,
            R.drawable.request,
            R.drawable.mine
    };
    private int[] image1 = new int[]{
            R.drawable.home1,
            R.drawable.picture1,
            R.drawable.request1,
            R.drawable.mine1
    };
    private LinearLayout mLlayMainTotal;
    private ImageButton mIbMainHome;
    private ImageButton mIbMainPicture;
    private ImageButton mIbMainRequest;
    private ImageButton mIbMainMine;
    private LinearLayout mLlayMainHome;
    private LinearLayout mLlayMainPicture;
    private LinearLayout mLlayMainRequest;
    private LinearLayout mLlayMainMine;
    //声明Fragment属性
    private HomeFragment mHome;
    private PictureFragment mPicture;
    private RequestFragment mRequest;
    private MineFragment mMine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //1.获取界面的控件
        getViews();

        //2. 注册事件监听器
        setListener();
        
        switch (Utils.flag){
            case 1:
                setHomePage();
                break;
            case 2:
                setPicturePage();
                break;
            case 3:
                setRequestPage();
                break;
            case 4:
                setMinePage();
                break;
        }
    }

    private void setMinePage() {
        //1.获取一个FragmentManager对象
        FragmentManager fm = getFragmentManager();
        //2.获取FragmentTransaction对象
        FragmentTransaction transaction = fm.beginTransaction();
        if (mMine == null) {
            mMine = new MineFragment();
        }
        //3.设置页面
        transaction.replace(R.id.FlayMainContent,mMine);
        //4.执行更改
        transaction.commit();
        mLlayMainTotal.invalidate();

    }

    private void setRequestPage() {
        //1.获取一个FragmentManager对象
        FragmentManager fm = getFragmentManager();
        //2.获取FragmentTransaction对象
        FragmentTransaction transaction = fm.beginTransaction();
        if (mRequest == null) {
            mRequest = new RequestFragment();
        }
        //3.设置页面
        transaction.replace(R.id.FlayMainContent,mRequest);
        //4.执行更改
        transaction.commit();
        mLlayMainTotal.invalidate();

    }

    private void setPicturePage() {
        //1.获取一个FragmentManager对象
        FragmentManager fm = getFragmentManager();
        //2.获取FragmentTransaction对象
        FragmentTransaction transaction = fm.beginTransaction();
        if (mPicture == null) {
            mPicture = new PictureFragment();
        }
        //3.设置页面
        transaction.replace(R.id.FlayMainContent,mPicture);
        //4.执行更改
        transaction.commit();
        mLlayMainTotal.invalidate();

    }

    private void setHomePage() {
        //1.获取一个FragmentManager对象
        FragmentManager fm = getFragmentManager();
        //2.获取FragmentTransaction对象
        FragmentTransaction transaction = fm.beginTransaction();
        if (mHome == null) {
            mHome = new HomeFragment();
        }
        //3.设置页面
        transaction.replace(R.id.FlayMainContent,mHome);
        //4.执行更改
        transaction.commit();
        mLlayMainTotal.invalidate();

    }


    private void setListener() {
        MyListener listener = new MyListener();
        mLlayMainHome.setOnClickListener(listener);
        mLlayMainPicture.setOnClickListener(listener);
        mLlayMainRequest.setOnClickListener(listener);
        mLlayMainMine.setOnClickListener(listener);
    }

    private void getViews() {
        mLlayMainTotal = (LinearLayout) findViewById(R.id.LlayMainTotal);
        mLlayMainHome = (LinearLayout) findViewById(R.id.LlayMainHome);
        mLlayMainPicture = (LinearLayout) findViewById(R.id.LlayMainPicture);
        mLlayMainRequest = (LinearLayout) findViewById(R.id.LlayMainRequest);
        mLlayMainMine = (LinearLayout) findViewById(R.id.LlayMainMine);

        mIbMainHome = (ImageButton) findViewById(R.id.IbMainHome);
        mIbMainPicture = (ImageButton) findViewById(R.id.IbMainPicture);
        mIbMainRequest = (ImageButton) findViewById(R.id.IbMainRequest);
        mIbMainMine = (ImageButton) findViewById(R.id.IbMainMine);
    }

    class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //1. 获取一个FragmentManager对象
            FragmentManager fm = getFragmentManager();
            //2. 获取FragmentTransaction对象
            FragmentTransaction transaction = fm.beginTransaction();
            switch (v.getId()) {
                case R.id.LlayMainHome:     //点击首页
                    if (mHome == null) {
                        mHome = new HomeFragment();
                    }
                    //3. 设置页面
                    mIbMainHome.setImageResource(image1[0]);
                    mIbMainPicture.setImageResource(image0[1]);
                    mIbMainRequest.setImageResource(image0[2]);
                    mIbMainMine.setImageResource(image0[3]);
                    transaction.replace(R.id.FlayMainContent, mHome);
                    break;
                case R.id.LlayMainPicture:  //点击作品页面
                    if (mPicture == null) {
                        mPicture = new PictureFragment();
                    }
                    //3. 设置页面
                    mIbMainPicture.setImageResource(image1[1]);
                    mIbMainHome.setImageResource(image0[0]);
                    mIbMainRequest.setImageResource(image0[2]);
                    mIbMainMine.setImageResource(image0[3]);

                    transaction.replace(R.id.FlayMainContent, mPicture);
                    break;

                case R.id.LlayMainRequest:      //点击需求页面
                    if (mRequest == null) {
                        mRequest = new RequestFragment();
                    }
                    //3. 设置页面
                    mIbMainRequest.setImageResource(image1[2]);
                    mIbMainHome.setImageResource(image0[0]);
                    mIbMainPicture.setImageResource(image0[1]);
                    mIbMainMine.setImageResource(image0[3]);

                    transaction.replace(R.id.FlayMainContent, mRequest);
                    break;
                case R.id.LlayMainMine:
//                    Intent i=new Intent(MainActivity.this,PersonnalMessage.class);
//                    startActivity(i);
                    if (mMine == null) {
                        mMine = new MineFragment();
                    }
                    //3. 设置页面
                    mIbMainMine.setImageResource(image1[3]);
                    mIbMainHome.setImageResource(image0[0]);
                    mIbMainPicture.setImageResource(image0[1]);
                    mIbMainRequest.setImageResource(image0[2]);
                    transaction.replace(R.id.FlayMainContent, mMine);
                    break;
            }
            //4. 执行更改
            transaction.commit();
            mLlayMainTotal.invalidate();
        }
    }
}
