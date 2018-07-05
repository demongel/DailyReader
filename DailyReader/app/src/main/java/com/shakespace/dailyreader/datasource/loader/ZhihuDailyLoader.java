package com.shakespace.dailyreader.datasource.loader;

import com.shakespace.dailyreader.bean.ZhihuDailyBean;
import com.shakespace.dailyreader.datasource.local.ZhihuDailyLocalSource;
import com.shakespace.dailyreader.datasource.remote.ZhihuDailyRemoteSource;
import com.shakespace.dailyreader.datasource.source.ZhihuDailySource;

import java.util.LinkedHashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by shakespace on 2018/3/28.
 */

public class ZhihuDailyLoader implements ZhihuDailySource {

    private ZhihuDailyRemoteSource mZhihuDailyRemoteSource;

    private ZhihuDailyLocalSource mZhihuDailyLocalSource;
    private Map<String, ZhihuDailyBean> mCachedStroies;// TODO 需要修改

    private ZhihuDailyLoader() {
    }

    private ZhihuDailyLoader(ZhihuDailyLocalSource zhihuDailyLocalSource, ZhihuDailyRemoteSource zhihuDailyRemoteSource) {
        this.mZhihuDailyLocalSource = zhihuDailyLocalSource;
        this.mZhihuDailyRemoteSource = zhihuDailyRemoteSource;
    }

    public static ZhihuDailyLoader getInstance() {
        return ZhihuDailyLoaderHolder.mLoader;
    }

    @Override
    public void loadZhihuDailySource(final boolean forceUpdate, final boolean cleanCache, final String date, final Observer<ZhihuDailyBean> observer) {

        // 如果不需要强制刷新，并且内存有缓存,获取当日的日报
        if (!forceUpdate && mCachedStroies != null) {
            if (mCachedStroies.containsKey(date)) {
                ZhihuDailyBean bean = mCachedStroies.get(date);
                observer.onNext(bean);
                return;
            }
        }

        mZhihuDailyRemoteSource.loadZhihuDailySource(forceUpdate, cleanCache, date, new Observer<ZhihuDailyBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                observer.onSubscribe(d);

            }

            @Override
            public void onNext(ZhihuDailyBean zhihuDailyBean) {
                observer.onNext(zhihuDailyBean);
                updateCache(cleanCache, zhihuDailyBean);

                saveToLocal(zhihuDailyBean);
            }

            @Override
            public void onError(Throwable e) {
                // TODO 调用本地方法
                mZhihuDailyLocalSource.loadZhihuDailySource(forceUpdate, cleanCache, date, new Observer<ZhihuDailyBean>() {
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

                    }
                });
            }

            @Override
            public void onComplete() {
                observer.onComplete();
                // 调用本地方法？
            }
        });


    }

    private void saveToLocal(ZhihuDailyBean zhihuDailyBean) {
        // TODO 待实现
        mZhihuDailyLocalSource.saveAll(zhihuDailyBean);
    }

    private void updateCache(boolean cleanCache, ZhihuDailyBean zhihuDailyBean) {
        if (mCachedStroies == null) {
            mCachedStroies = new LinkedHashMap<>();
        }

        // 如果需要清空缓存
        if (cleanCache) {
            mCachedStroies.clear();
        }

        // 添加到内存缓存
        if (!mCachedStroies.containsKey(zhihuDailyBean.getDate())) {
            mCachedStroies.put(zhihuDailyBean.getDate(), zhihuDailyBean);
        }
    }

    private static class ZhihuDailyLoaderHolder {
        private static ZhihuDailyLoader mLoader = new ZhihuDailyLoader(ZhihuDailyLocalSource.getInstance(), ZhihuDailyRemoteSource.getInstance());
    }
}
