package com.gky.okhttpdeme.ui.listener;

import com.gky.okhttpdeme.model.entity.ArticleInfo;

/**
 * Created by 凯阳 on 2016/6/27.
 */
public interface ArticleItemListener {

    void OnItemClick(ArticleInfo articleInfo);

    void OnItemLongClick(ArticleInfo articleInfo);
}
