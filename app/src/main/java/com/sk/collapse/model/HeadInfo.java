package com.sk.collapse.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sk on 16-9-5.
 */
public class HeadInfo {
    private String param;
    @SerializedName("goto")
    private String gotoX;
    private String style;
    private String title;
    private int count;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getGotoX() {
        return gotoX;
    }

    public void setGotoX(String gotoX) {
        this.gotoX = gotoX;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
