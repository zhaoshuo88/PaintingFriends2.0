package com.example.administrator.paintingfriends20.domain;

/**
 * Created by 15530 on 2017/5/21.
 */

public class FindDetailsComment {

    private int headPortrait;   //头像
    private String name;    //昵称
    private String date;    //评论日期
    private long likeNum;   //点赞数
    private String content; //评论内容

    public FindDetailsComment(int headPortrait, String name, String date, long likeNum, String content) {
        this.headPortrait = headPortrait;
        this.name = name;
        this.date = date;
        this.likeNum = likeNum;
        this.content = content;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(long likeNum) {
        this.likeNum = likeNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
