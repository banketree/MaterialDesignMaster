package com.sk.collapse.model;

import java.util.List;

/**
 * Created by sk on 16-9-5.
 */
public class ResultInfo {

    private String type;
    private HeadInfo head;
    private List<BodyInfo> body;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HeadInfo getHead() {
        return head;
    }

    public void setHead(HeadInfo head) {
        this.head = head;
    }

    public List<BodyInfo> getBody() {
        return body;
    }

    public void setBody(List<BodyInfo> body) {
        this.body = body;
    }
}
