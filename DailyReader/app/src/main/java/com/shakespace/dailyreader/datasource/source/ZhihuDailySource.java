package com.shakespace.dailyreader.datasource.source;

import com.shakespace.dailyreader.bean.ZhihuDailyBean;

import io.reactivex.Observer;

/**
 * Created by shakespace on 2018/3/28.
 */

public interface ZhihuDailySource {
    /**
     *
     * @param forceUpdate   是否需要强制刷新界面
     * @param cleanCache    是否需要清空缓存
     * @param date  日期
     * @param observer  观察者
     */
    void  loadZhihuDailySource(boolean forceUpdate, boolean cleanCache, String date, Observer<ZhihuDailyBean> observer);
}


