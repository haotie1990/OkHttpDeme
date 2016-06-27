package com.gky.okhttpdeme.model.entity;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.gky.okhttpdeme.model.listener.ResponseListener;

import java.util.List;

/**
 * Created by 凯阳 on 2016/6/26.
 */
public class ArticlesRequest extends RequestData<List<ArticleInfo>> {

    private ResponseListener<List<ArticleInfo>> mListener;

    public ArticlesRequest(@NonNull String url, @NonNull ResponseListener<List<ArticleInfo>> listener) {
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
