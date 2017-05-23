package com.example.administrator.paintingfriends20.domain;

/**
 * Created by Loktar on 2017/5/23.
 */
public class Request {
    private Long id;
    private int headPhoto;  //头像
    private String name;    //昵称
    private String request; //具体需求
    private String time;    //发布时间

    public Request(Long id, int headPhoto, String name, String request, String time) {
        this.id = id;
        this.headPhoto = headPhoto;
        this.name = name;
        this.request = request;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getHeadPhoto() {
        return headPhoto;
    }

    public void setHeadPhoto(int headPhoto) {
        this.headPhoto = headPhoto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
