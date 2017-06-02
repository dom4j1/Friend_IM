package com.dom.Friend_IM.model.http.help;

import android.util.Log;


import com.dom.Friend_IM.app.App;
import com.dom.Friend_IM.model.bean.TestBean;
import com.dom.Friend_IM.model.http.api.TestApi;
import com.dom.Friend_IM.model.http.response.TestResult;
import com.dom.Friend_IM.util.NewWorkUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dom4j on 2017/3/7.
 */

public class RetrofitHelper {
    private static OkHttpClient mOkHttpClient = null;
    private static TestApi mTestApi = null;

    public RetrofitHelper(){
        init();
    }

    private void init(){
        initOkHttp();
        mTestApi = getApiService(mTestApi.HOST,TestApi.class);
    }

    private void initOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        /**
         * 日志拦截器
         */
        Interceptor loggingIntercept = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                long t1 = System.nanoTime();
                Log.e("TAG",String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));

                Response response = chain.proceed(request);
                long t2 = System.nanoTime();
                Log.e("TAG",String.format("Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));

                return response;
            }
        };
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = null;
                if(!NewWorkUtil.isNetworkConnected()){
                  Request request = chain.request()
                          .newBuilder()
                          .cacheControl(CacheControl.FORCE_CACHE)
                          .build();
                      response = chain.proceed(request);
                    return response;
                }else{
                    int maxAge = 0;
                    response = chain.proceed(chain.request())
                            .newBuilder()
                            .removeHeader("pragma")
                            .header("Cache-Control","max-age=" + maxAge)
                            .build();
                    return response;
                }
            }
        };
        /**
         * 缓存路径
         */
        File httpCacheDirectory = new File(App.getInstance().getExternalFilesDir("test").getPath());
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(httpCacheDirectory, cacheSize);
        builder.addInterceptor(loggingIntercept);
        builder.addInterceptor(cacheInterceptor);
        builder.cache(cache);
        //设置超时
        builder.connectTimeout(5, TimeUnit.SECONDS);
        builder.readTimeout(20,TimeUnit.SECONDS);
        builder.writeTimeout(20,TimeUnit.SECONDS);
        //错误重连
        mOkHttpClient = builder.build();
    }

    private <T> T getApiService(String host, Class<T> clz) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(host)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(clz);
    }

    public Observable<TestResult<List<TestBean>>> test(String mode, int num, int page){return mTestApi.getClassList(mode,num,page);}


}
