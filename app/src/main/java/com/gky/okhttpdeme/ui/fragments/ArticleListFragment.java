package com.gky.okhttpdeme.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.gky.okhttpdeme.R;
import com.gky.okhttpdeme.model.entity.ArticleInfo;
import com.gky.okhttpdeme.presenter.listener.ArticleListPresenter;
import com.gky.okhttpdeme.ui.adapter.ArticleRyAdapter;
import com.gky.okhttpdeme.ui.listener.ArticleItemListener;
import com.gky.okhttpdeme.ui.listener.ArticleListViewListener;

import java.util.List;

/**
 * Created by 凯阳 on 2016/6/26.
 */
public class ArticleListFragment extends Fragment
    implements ArticleListViewListener, ArticleItemListener{

    private ArticleListPresenter mPresenter;

    private String articleType;

    private RecyclerView mRecyclerView;

    private ArticleRyAdapter mAdapter;

    private ProgressBar mProgress;

    public ArticleListFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        articleType = getArguments().getString("ArticleType");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View bodyView = inflater.inflate(R.layout.fragment_articles, container, false);
        mProgress = (ProgressBar) bodyView.findViewById(R.id.pb_loading);
        mRecyclerView = (RecyclerView) bodyView.findViewById(R.id.rv_content);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new ArticleRyAdapter(getActivity(), this);
        mRecyclerView.setAdapter(mAdapter);
        return bodyView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(articleType, "onResume");
        mPresenter.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void showLoading() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProgress.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onArticleList(String type, final List<ArticleInfo> infos) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapter.setArticles(infos);
            }
        });
    }

    @Override
    public void openArticleDeatail(ArticleInfo info) {
        Intent i = new Intent();
        getActivity().startActivity(i);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void setPresenter(ArticleListPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void OnItemClick(ArticleInfo articleInfo) {
        mPresenter.openArticleDetail(articleInfo);
    }

    @Override
    public void OnItemLongClick(ArticleInfo articleInfo) {

    }
}
