package com.example.administrator.paintingfriends20.domain;

/**
 * Created by lenovo on 2016/12/21.
 */
public class OtherWork {
    private int id;
    private String pic;//作品地址
    private int like;//点赞数

    public OtherWork(int id, String pic, int like) {
        this.id = id;
        this.pic = pic;
        this.like = like;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
