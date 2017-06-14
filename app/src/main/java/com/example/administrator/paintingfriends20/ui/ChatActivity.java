package com.example.administrator.paintingfriends20.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.administrator.paintingfriends20.R;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.ui.EaseChatFragment;

/**
 * Created by 15530 on 2017/6/14.
 */

public class ChatActivity extends AppCompatActivity {

    private EaseChatFragment chatFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //直接使用EaseUI封装好的聊天界面
        chatFragment = new EaseChatFragment();

        //将参数传递给聊天界面
        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.FlayChatMain,chatFragment).commit();

        initView();
    }

    /**
     * 初始化界面
     */
    private void initView() {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();

                // 调用SDK的退出登录方法，第一个参数表示是否解绑推送的token，没有使用推送或者被踢都要传false
        EMClient.getInstance().logout(false, new EMCallBack() {
            @Override
            public void onSuccess() {
                Log.i("退出登录","logout success");
                finish();
            }

            @Override
            public void onError(int i, String s) {
                Log.e("退出登录错误","logout error " + i + ", " + s);
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }
}
