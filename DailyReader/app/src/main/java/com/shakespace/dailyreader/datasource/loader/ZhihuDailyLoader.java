package com.shakespace.dailyreader.datasource.loader;

import com.shakespace.dailyreader.bean.ZhihuDailyBean;
import com.shakespace.dailyreader.bean.ZhihuStory;
import com.shakespace.dailyreader.datasource.local.ZhihuDailyLocalSource;
import com.shakespace.dailyreader.datasource.remote.ZhihuDailyRemoteSource;
import com.shakespace.dailyreader.datasource.source.ZhihuDailySource;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by shakespace on 2018/3/28.
 */

public class ZhihuDailyLoader implements ZhihuDailySource {

    private ZhihuDailyRemoteSource mZhihuDailyRemoteSource;

    private ZhihuDailyLocalSource mZhihuDailyLocalSource;
    private Map<Long, ZhihuStory> mCachedStroies;// TODO 需要修改

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
    public void loadZhihuDailySource(final boolean forceUpdate, final boolean cleanCache, final String date, final Observer<List<ZhihuStory>> observer) {

        // 如果不需要强制刷新，并且内存有缓存,获取当日的日报
        if (!forceUpdate && mCachedStroies != null) {
            observer.onNext(new ArrayList<ZhihuStory>(mCachedStroies.values()));
            return;
        }
        //  先调用远程方法  处理远程方法返回的List<ZhihuStory>
        mZhihuDailyRemoteSource.loadZhihuDailySource(forceUpdate, cleanCache, date, new Observer<List<ZhihuStory>>() {
            @Override
            public void onSubscribe(Disposable d) {
                observer.onSubscribe(d);
            }

            @Override
            public void onNext(List<ZhihuStory> zhihuStories) {
                // 更新缓存数据
                updateCache(cleanCache, zhihuStories);
                // 回调给Presenter   保持和缓存数据同步
                observer.onNext(new ArrayList<>(mCachedStroies.values()));
//                updateCache(cleanCache, zhihuDailyBean);//
//                saveToLocal(zhihuDailyBean);
            }

            @Override
            public void onError(Throwable e) {
                // TODO 调用本地方法
                mZhihuDailyLocalSource.loadZhihuDailySource(forceUpdate, cleanCache, date, new Observer<List<ZhihuStory>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        observer.onSubscribe(d);
                    }

                    @Override
                    public void onNext(List<ZhihuStory> zhihuStories) {
                        observer.onNext(zhihuStories);
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

    private void updateCache(boolean cleanCache, List<ZhihuStory> zhihuStories) {
        if (mCachedStroies == null) {
            mCachedStroies = new LinkedHashMap<>();
        }

        // 如果需要清空缓存
        if (cleanCache) {
            mCachedStroies.clear();
        }

        // 添加到内存缓存
        for (ZhihuStory item : zhihuStories) {
            mCachedStroies.put(item.getId(), item);
        }
    }

    private static class ZhihuDailyLoaderHolder {
        private static ZhihuDailyLoader mLoader = new ZhihuDailyLoader(ZhihuDailyLocalSource.getInstance(), ZhihuDailyRemoteSource.getInstance());
    }
}
