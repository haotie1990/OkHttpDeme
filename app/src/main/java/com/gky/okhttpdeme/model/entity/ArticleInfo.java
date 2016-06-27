package com.gky.okhttpdeme.model.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by 凯阳 on 2016/6/26.
 */
public class ArticleInfo {

    @JSONField(name = "_id")
    public String _id;

    @JSONField(name = "createdAt")
    public String createAt;

    @JSONField(name = "desc")
    public String desc;

    @JSONField(name = "publishedAt")
    public String publishAt;

    @JSONField(name = "source")
    public String source;

    @JSONField(name = "type")
    public String type;

    @JSONField(name = "url")
    public String url;

    @JSONField(name = "used")
    public boolean used;

    @JSONField(name = "who")
    public String who;

    public String getDesc() {
        return desc;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public boolean isUsed() {
        return used;
    }

    public String getWho() {
        return who;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
