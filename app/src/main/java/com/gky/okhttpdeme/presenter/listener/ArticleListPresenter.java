package com.gky.okhttpdeme.presenter.listener;

import com.gky.okhttpdeme.model.ArticleInfo;

/**
 * Created by 凯阳 on 2016/6/27.
 */
public interface ArticleListPresenter extends BasePresenter{

    void getArticleList(String type, int page);

    void openArticleDetail(ArticleInfo info);
}
