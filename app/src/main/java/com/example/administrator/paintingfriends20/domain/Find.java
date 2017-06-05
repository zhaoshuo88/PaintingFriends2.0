package com.example.administrator.paintingfriends20.domain;

import android.graphics.Bitmap;

/**
 * Created by 15530 on 2017/5/21.
 */

public class Find {
    private int id;
    private int headPortrait;   //头像
    private String name;    //昵称
    private Bitmap image;  //作品图片


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(int headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Find(int id, int headPortrait, String name, Bitmap image) {
        this.id = id;
        this.headPortrait = headPortrait;
        this.name = name;
        this.image = image;
    }
}
