package com.example.administrator.paintingfriends20.domain;

import android.graphics.Bitmap;

/**
 * Created by 15530 on 2017/5/21.
 */

public class Find {
    private int id;
    private String headPortrait;   //头像
    private String name;    //昵称
    private String image;  //作品图片

    public Find(int id, String headPortrait, String name, String image) {
        this.id = id;
        this.headPortrait = headPortrait;
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    //    public Find(int id, int headPortrait, String name, int image) {
//        this.id = id;
//        this.headPortrait = headPortrait;
//        this.name = name;
//        this.image = image;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getHeadPortrait() {
//        return headPortrait;
//    }
//
//    public void setHeadPortrait(int headPortrait) {
//        this.headPortrait = headPortrait;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getImage() {
//        return image;
//    }
//
//    public void setImage(int image) {
//        this.image = image;
//    }

    //    private Bitmap image;  //作品图片
//
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getHeadPortrait() {
//        return headPortrait;
//    }
//
//    public void setHeadPortrait(int headPortrait) {
//        this.headPortrait = headPortrait;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Bitmap getImage() {
//        return image;
//    }
//
//    public void setImage(Bitmap image) {
//        this.image = image;
//    }
//
//    public Find(int id, int headPortrait, String name, Bitmap image) {
//        this.id = id;
//        this.headPortrait = headPortrait;
//        this.name = name;
//        this.image = image;
//    }
}
