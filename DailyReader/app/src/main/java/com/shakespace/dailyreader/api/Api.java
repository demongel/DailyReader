package com.shakespace.dailyreader.api;

import com.shakespace.dailyreader.bean.ZhihuDailyBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by shakespace on 2018/3/26.
 */

public interface Api {
    /**
     * @Path：所有在网址中的参数（URL的问号前面），如： http://102.10.10.132/api/Accounts/{accountId}
     * @Query：URL问号后面的参数，如： http://102.10.10.132/api/Comments?access_token={access_token}
     * @QueryMap：相当于多个@Query
     * @Field：用于POST请求，提交单个数据
     * @Body：相当于多个@Field，以对象的形式提交
     */
    String ZHIHU_DAILY_BASE = "https://news-at.zhihu.com/api/4/news/";

    interface ZhihuDailyService {
        @GET("before/{date}")
        Observable<ZhihuDailyBean> getZhihuDaily(@Path("date") String date);

//        @GET("{id}")
//        Call<RequestBody> getZhihuContent(@Path("id") int id);
    }
}
