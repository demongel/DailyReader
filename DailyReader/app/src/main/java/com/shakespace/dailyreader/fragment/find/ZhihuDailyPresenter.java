package com.shakespace.dailyreader.fragment.find;

import android.util.Log;

import com.shakespace.dailyreader.api.Api;
import com.shakespace.dailyreader.api.RetrofitService;
import com.shakespace.dailyreader.bean.ZhihuDailyBean;
import com.shakespace.dailyreader.bean.ZhihuStory;
import com.shakespace.dailyreader.datasource.loader.ZhihuDailyLoader;
import com.shakespace.dailyreader.util.DateFormatUtil;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by shakespace on 2018/3/26.
 */

public class ZhihuDailyPresenter implements ZhihuDailyContract.Presenter {

    private static final String TAG = "ZhihuDailyPresenter";

    private ZhihuDailyContract.View mView;

    private ZhihuDailyLoader mLoader;
    Observer<ZhihuDailyBean> observer = new Observer<ZhihuDailyBean>() {
        @Override
        public void onSubscribe(Disposable d) {
            Log.e(TAG,"onSubscribe");
            mView.onLoadStart();
        }

        @Override
        public void onNext(ZhihuDailyBean zhihuDailyBean) {
            Log.e(TAG,"onnext");
            String date = zhihuDailyBean.getDate();
            List<ZhihuStory> stories = zhihuDailyBean.getStories();
            for (ZhihuStory story: stories ) {
                story.setDate(date);
            }
            mView.onLoadSuccess(stories);
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG,"onError"+e.getMessage());
        }

        @Override
        public void onComplete() {
            Log.e(TAG,"onComplete");
        }
    };

    public ZhihuDailyPresenter(ZhihuDailyContract.View view, ZhihuDailyLoader loader) {
        this.mView = view;
        this.mLoader = loader;
    }

//    @Override
    public void load(long date) {

        String sDate = DateFormatUtil.formatZhihuDailyDateLongToString(date);

        RetrofitService.createZhihuDailyService(Api.ZHIHU_DAILY_BASE)
                .getZhihuDaily(sDate)
                .take(1)    // 返回一个
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(observer);
    }

    @Override
    public void load(boolean forceUpdate, boolean cleanCache, String date) {

        mLoader.loadZhihuDailySource(forceUpdate, cleanCache, date, new Observer<ZhihuDailyBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe");
                mView.onLoadStart();
            }

            @Override
            public void onNext(ZhihuDailyBean zhihuDailyBean) {
                Log.e(TAG, "onnext");
                String date = zhihuDailyBean.getDate();
                List<ZhihuStory> stories = zhihuDailyBean.getStories();
                for (ZhihuStory story : stories) {
                    story.setDate(date);
                }
                mView.onLoadSuccess(stories);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError" + e.getMessage());
                mView.onLoadError(e);
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
                mView.onLoadComplete();
            }
        });
    }
}
