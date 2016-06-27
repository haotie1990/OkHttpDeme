package com.gky.okhttpdeme.net.entity;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.gky.okhttpdeme.model.ArticleInfo;
import com.gky.okhttpdeme.net.listener.ResponseListener;

import java.util.List;

/**
 * Created by 凯阳 on 2016/6/26.
 */
public class ArticlesRquest extends RequestData<List<ArticleInfo>>{

    private ResponseListener<List<ArticleInfo>> mListener;

    public ArticlesRquest(@NonNull String url, @NonNull ResponseListener<List<ArticleInfo>> listener) {
        super(url);
        mListener = listener;
    }

    @Override
    public List<ArticleInfo> parse(String body) {
        JSONObject root = JSON.parseObject(body);
        List<ArticleInfo> articleInfoList = JSON.parseObject(root.get("results").toString(),
            new TypeReference<List<ArticleInfo>>(){});
        return articleInfoList;
    }

    @Override
    public void onComplete(List<ArticleInfo> response) {
        mListener.onComplete(response);
    }

    @Override
    public void onError(String error) {
        mListener.onError(error);
    }


}
