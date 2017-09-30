package com.example.administrator.demos;

/**
 * Created by Administrator on 2017/9/28.
 */

public class ZanBean {
    private boolean isZanded;//是否点过赞
    private int zanCount;//赞的数量

    public ZanBean(boolean isZanded, int zanCount) {
        this.isZanded = isZanded;
        this.zanCount = zanCount;
    }

    public boolean isZanded() {
        return isZanded;
    }

    public void setZanded(boolean zanded) {
        isZanded = zanded;
    }

    public int getZanCount() {
        return zanCount;
    }

    public void setZanCount(int zanCount) {
        this.zanCount = zanCount;
    }
}
