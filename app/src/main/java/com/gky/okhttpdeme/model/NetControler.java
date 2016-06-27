package com.gky.okhttpdeme.model;

import com.gky.okhttpdeme.MainApp;
import com.gky.okhttpdeme.model.entity.RequestData;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 凯阳 on 2016/6/20.
 */
public class NetControler {

    private static final boolean DEBUG = false;

    private OkHttpClient mClient;

    private static class NetControlerLoader {
        public static NetControler INSTANCE = new NetControler();
    }

    private NetControler(){
        File cacheFile = new File(MainApp.getContext().getCacheDir(), "NetCache");
        Cache cache = new Cache(cacheFile,
            5 * 1024 * 1024/* 5MiB */);
        mClient = new OkHttpClient.Builder()
            .connectTimeout(10 , TimeUnit.SECONDS)
            .readTimeout(30 , TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .cache(cache)
            .addInterceptor(new LogInterceptor())
            .addNetworkInterceptor(new LogInterceptor())
            .build();
    }

    public static NetControler getInstance(){
        return NetControlerLoader.INSTANCE;
    }

    public <T> void get(final RequestData<T> requestData){

        Request request = new Request.Builder()
            .url(requestData.getUrl())
            .build();

        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                requestData.deliver(response);
            }
        });

    }

    public void post(){

    }

    class LogInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Response response = null;

            long t1 = System.nanoTime();
            Request request = chain.request();
            if (DEBUG) System.out.println(String.format("Send Request: [%s] %s%n%s",
                request.url(), chain.connection(), request.headers()));

            response = chain.proceed(request);

            long t2 = System.nanoTime();
            if (DEBUG) System.out.println(String.format("Receive Response: [%s] %.1fms%n%s",
                response.request().url(), (t2-t1)/1e6d, response.headers()));

            return response;
        }
    }
}
