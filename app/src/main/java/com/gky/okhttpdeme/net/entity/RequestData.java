package com.gky.okhttpdeme.net.entity;

import okhttp3.Response;

/**
 * Created by 凯阳 on 2016/6/26.
 */
public abstract class RequestData<T> {

    private String url;

    public RequestData(String url) {
        this.url = url;
    }

    public String getUrl(){
        return url;
    }

    public void deliver(Response response){
        if(response.isSuccessful()){
            T t = parse(response.body().toString());
            onComplete(t);
        }else{
            onError("Unknown expection:[" + response.code() + "] "
                + response.message());
        }
    }

    public abstract T parse(String body);

    public abstract void onComplete(T response);

    public abstract void onError(String error);
}
