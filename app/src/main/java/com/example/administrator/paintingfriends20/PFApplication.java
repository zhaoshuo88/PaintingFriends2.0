package com.example.administrator.paintingfriends20;

import android.app.Application;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.EaseUI;

/**
 * Created by 15530 on 2017/6/13.
 */

public class PFApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        EaseUI.getInstance().init(this, null);
        EMClient.getInstance().setDebugMode(true);
    }
}
