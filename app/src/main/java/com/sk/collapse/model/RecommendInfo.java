package com.sk.collapse.model;

import java.util.List;

/**
 * Created by sk on 16-9-5.
 */
public class RecommendInfo {

    public final static String RECOMMEND_TYPE_WEBLINK = "weblink";
    public final static String RECOMMEND_TYPE_RECOMMEND = "recommend";
    public final static String RECOMMEND_TYPE_LIVE = "live";
    public final static String RECOMMEND_TYPE_BANGUMI_2 = "bangumi_2";
    public final static String RECOMMEND_TYPE_REGION = "region";
    public final static String RECOMMEND_TYPE_ACTIVITY = "activity";


    private int code;
    private List<ResultInfo> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ResultInfo> getResult() {
        return result;
    }

    public void setResult(List<ResultInfo> result) {
        this.result = result;
    }
}
