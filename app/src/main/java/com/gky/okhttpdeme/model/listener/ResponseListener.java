package com.gky.okhttpdeme.model.listener;

/**
 * Created by 凯阳 on 2016/6/26.
 */
public interface ResponseListener<T> {

    public void onComplete(T response);

    public void onError(String error);
}
