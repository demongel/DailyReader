package com.shakespace.dailyreader.fragment.find;

import com.shakespace.dailyreader.base.BasePresenter;
import com.shakespace.dailyreader.base.BaseView;
import com.shakespace.dailyreader.bean.ZhihuStory;

import java.util.List;

/**
 * Created by shakespace on 2018/3/26.
 */

public interface ZhihuDailyContract {


    interface View extends BaseView<Presenter> {
        // 请求加载时
        void onLoadStart();
        // 加载成功时
        void onLoadSuccess(List<ZhihuStory> stories);
        //  加载出错么
        void onLoadError(Throwable e);

        void onLoadComplete();
        // 单独申请刷新
        void refresh();

    }

    interface Presenter extends BasePresenter {

//        void loadNews(boolean forceUpdate, boolean clearCache, long date);

        /**
         *
         * @param forceUpdate   是否需要强制刷新
         * @param cleanCache    是否需要清除缓存
         * @param date  20180629   日期
         */
        void load(boolean forceUpdate,boolean cleanCache,String date);
    }

}
