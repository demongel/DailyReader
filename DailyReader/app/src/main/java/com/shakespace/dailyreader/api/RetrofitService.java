package com.shakespace.dailyreader.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shakespace on 2018/3/26.
 */

public class RetrofitService {

    public static Api.ZhihuDailyService createZhihuDailyService(String  url){
        Retrofit  retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Api.ZhihuDailyService.class);
    }
}
