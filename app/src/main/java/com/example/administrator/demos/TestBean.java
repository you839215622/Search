package com.example.administrator.demos;

/**
 * Created by Administrator on 2017/9/28.
 */

public class TestBean {
    private String name;
    private String url;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public TestBean(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
