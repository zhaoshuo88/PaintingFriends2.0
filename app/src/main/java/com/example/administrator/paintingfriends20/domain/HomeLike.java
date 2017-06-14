package com.example.administrator.paintingfriends20.domain;

/**
 * Created by lenovo on 2016/12/21.
 */
public class HomeLike {
    private int id;
    private String pic;//作品名字
    private String uname;//点赞数

    public HomeLike(int id, String pic, String uname) {
        this.id = id;
        this.pic = pic;
        this.uname = uname;
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

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
