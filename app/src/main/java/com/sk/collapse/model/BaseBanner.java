package com.sk.collapse.model;

/**
 * Created by sk on 16-8-31.
 */
public class BaseBanner {


    private String title;
    private String img;
    private String link;

    public BaseBanner(String img) {
        this.img = img;
    }

    public BaseBanner(String title, String img, String link) {
        this.title = title;
        this.img = img;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
