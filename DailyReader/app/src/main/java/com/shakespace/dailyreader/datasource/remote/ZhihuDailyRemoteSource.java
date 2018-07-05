package com.shakespace.dailyreader.datasource.remote;

import com.shakespace.dailyreader.api.Api;
import com.shakespace.dailyreader.api.RetrofitService;
import com.shakespace.dailyreader.bean.ZhihuDailyBean;
import com.shakespace.dailyreader.datasource.source.ZhihuDailySource;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by shakespace on 2018/3/28.
 */

public class ZhihuDailyRemoteSource implements ZhihuDailySource {

    private ZhihuDailyRemoteSource(){}


    private static class ZhihuDailyRemoteSourceHolder{
        private static ZhihuDailyRemoteSource mZhihuDailyRemoteSource = new ZhihuDailyRemoteSource();
    }

    public static ZhihuDailyRemoteSource getInstance(){
        return ZhihuDailyRemoteSourceHolder.mZhihuDailyRemoteSource;
    }

    @Override
    public void loadZhihuDailySource(boolean forceUpdate, boolean cleanCache, String date, final Observer<ZhihuDailyBean> observer) {

        RetrofitService.createZhihuDailyService(Api.ZHIHU_DAILY_BASE)
                .getZhihuDaily(date)
                .take(1)    // 返回一个
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<ZhihuDailyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        observer.onSubscribe(d);
                    }

                    @Override
                    public void onNext(ZhihuDailyBean zhihuDailyBean) {
                        observer.onNext(zhihuDailyBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        observer.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        observer.onComplete();;
                    }
                });
    }

}
