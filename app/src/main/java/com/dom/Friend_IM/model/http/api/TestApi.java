package com.dom.Friend_IM.model.http.api;

import com.dom.Friend_IM.model.bean.TestBean;
import com.dom.Friend_IM.model.http.response.TestResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by dom4j on 2017/4/1.
 */

public interface TestApi {
    public static final String HOST = "http://gank.io/api/";

    // http://gank.io/api/data/Android/10/1
    @GET("data/{mode}/{num}/{page}")
    Observable<TestResult<List<TestBean>>> getClassList(@Path("mode") String mode, @Path("num") int num, @Path("page") int page);
}
