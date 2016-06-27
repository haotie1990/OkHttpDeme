package com.gky.okhttpdeme.ui.listener;

import com.gky.okhttpdeme.model.ArticleInfo;
import com.gky.okhttpdeme.presenter.listener.ArticleListPresenter;

import java.util.List;

/**
 * Created by 凯阳 on 2016/6/27.
 */
public interface ArticleListViewListener extends BaseViewListener<ArticleListPresenter> {

    void showLoading();

    void hideLoading();

    void onArticleList(String type, List<ArticleInfo> infos);

    void openArticleDeatail(ArticleInfo info);

    void showError(String error);
}
