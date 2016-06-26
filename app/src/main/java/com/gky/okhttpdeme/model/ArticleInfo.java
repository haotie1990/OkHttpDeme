package com.gky.okhttpdeme.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by 凯阳 on 2016/6/26.
 */
public class ArticleInfo {

    @JSONField(name = "_id")
    private String _id;

    @JSONField(name = "createdAt")
    private String createAt;

    @JSONField(name = "desc")
    private String desc;

    @JSONField(name = "publishedAt")
    private String publishAt;

    @JSONField(name = "source")
    private String source;

    @JSONField(name = "type")
    private String type;

    @JSONField(name = "url")
    private String url;

    @JSONField(name = "used")
    private boolean used;

    @JSONField(name = "who")
    private String who;
}
