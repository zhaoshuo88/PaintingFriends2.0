package com.example.administrator.paintingfriends20.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.paintingfriends20.R;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;

/**
 * Created by 15530 on 2017/6/14.
 */

public class ChatActivity extends AppCompatActivity {

    private EaseChatFragment chatFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        chatFragment = new EaseChatFragment();
//        //将参数传递给聊天界面
//        chatFragment.setArguments(getIntent().getExtras());
//        getSupportFragmentManager().beginTransaction().add(R.id.FlayChatMain, chatFragment).commit();
        setContentView(R.layout.activity_chat);

        initView();
    }

    /**
     *  初始化界面
     */
    private void initView() {
        //new出EaseChatFragment或其子类的实例
        chatFragment = new EaseChatFragment();
        //传入参数
        Bundle args = getIntent().getExtras();
//        Bundle args = new Bundle();
        int anInt = args.getInt(EaseConstant.EXTRA_CHAT_TYPE);
        String userid = args.getString(EaseConstant.EXTRA_USER_ID);
        System.out.println("-----------------------------------------");
        System.out.println("anInt:  " + anInt);
        System.out.println("userid: " + userid);
        System.out.println("-----------------------------------------");
//        args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_GROUP);
//        args.putString(EaseConstant.EXTRA_USER_ID, "zw123");
        chatFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().add(R.id.FlayChatMain, chatFragment).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
