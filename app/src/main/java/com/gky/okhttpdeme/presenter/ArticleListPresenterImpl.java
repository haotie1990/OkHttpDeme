package com.gky.okhttpdeme.presenter;

import com.gky.okhttpdeme.config.ConfigDef;
import com.gky.okhttpdeme.model.ArticleInfo;
import com.gky.okhttpdeme.net.NetControler;
import com.gky.okhttpdeme.net.entity.ArticlesRquest;
import com.gky.okhttpdeme.net.listener.ResponseListener;
import com.gky.okhttpdeme.presenter.listener.ArticleListPresenter;
import com.gky.okhttpdeme.ui.listener.ArticleListViewListener;

import java.util.List;

/**
 * Created by 凯阳 on 2016/6/26.
 */
public class ArticleListPresenterImpl implements ArticleListPresenter{

    private ArticleListViewListener mView;

    private String articleType;

    public ArticleListPresenterImpl(ArticleListViewListener view, String articleType) {
        this.mView = view;
        this.articleType = articleType;

        mView.setPresenter(this);
    }

    @Override
    public void getArticleList(String type, int page){
        mView.showLoading();
        String url = String.format("%s/%s/7/%d", ConfigDef.DATA_BASE_URL, type, page);
        ArticlesRquest request = new ArticlesRquest(url, new ResponseListener<List<ArticleInfo>>() {
            @Override
            public void onComplete(List<ArticleInfo> response) {
                mView.hideLoading();
                mView.onArticleList(articleType, response);
            }

            @Override
            public void onError(String error) {
                mView.hideLoading();
                mView.showError(error);
            }
        });
        NetControler.getInstance().get(request);
    }

    @Override
    public void openArticleDetail(ArticleInfo info) {
        mView.openArticleDeatail(info);
    }

    @Override
    public void start() {

    }
}
