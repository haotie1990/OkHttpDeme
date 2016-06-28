package com.gky.okhttpdeme.presenter;

import com.gky.okhttpdeme.config.ConfigDef;
import com.gky.okhttpdeme.model.NetControler;
import com.gky.okhttpdeme.model.entity.ArticleInfo;
import com.gky.okhttpdeme.model.entity.ArticlesRequest;
import com.gky.okhttpdeme.model.listener.ResponseListener;
import com.gky.okhttpdeme.presenter.listener.ArticleListPresenter;
import com.gky.okhttpdeme.ui.listener.ArticleListViewListener;

import java.util.List;

/**
 * Created by 凯阳 on 2016/6/26.
 */
public class ArticleListPresenterImpl implements ArticleListPresenter{

    private ArticleListViewListener mView;

    private String articleType;

    private int page = 1;

    public ArticleListPresenterImpl(ArticleListViewListener view, String articleType) {
        this.mView = view;
        this.articleType = articleType;

        mView.setPresenter(this);
    }

    public static void newInstance(ArticleListViewListener view, String articleType){
        new ArticleListPresenterImpl(view, articleType);
    }

    @Override
    public void getArticleList(boolean isFirst){
        mView.showLoading();
        String url = String.format("%s/%s/15/%d", ConfigDef.DATA_BASE_URL, articleType,
            isFirst?page=1:++page);
        System.out.println(url);
        ArticlesRequest request = new ArticlesRequest(url, new ResponseListener<List<ArticleInfo>>() {
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
        getArticleList(true);
    }
}
