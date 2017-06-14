package com.example.administrator.paintingfriends20.domain;

/**
 * Created by Loktar on 2017/6/1.
 */
public class OtherRequest {
    private String request; //具体需求
    private String time;    //发布时间

    public OtherRequest(String request, String time) {
        this.request = request;
        this.time = time;
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
