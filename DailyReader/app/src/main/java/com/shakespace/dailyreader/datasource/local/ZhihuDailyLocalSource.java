package com.shakespace.dailyreader.datasource.local;

import android.util.Log;

import com.shakespace.dailyreader.App;
import com.shakespace.dailyreader.bean.ZhihuDailyBean;
import com.shakespace.dailyreader.bean.ZhihuStory;
import com.shakespace.dailyreader.datasource.source.ZhihuDailySource;
import com.shakespace.dailyreader.db.dao.ZhihuDailyBeanDao;
import com.shakespace.dailyreader.db.dao.ZhihuStoryDao;
import com.shakespace.dailyreader.util.DateFormatUtil;

import java.util.List;

import io.reactivex.Observer;

/**
 * Created by shakespace on 2018/3/28.
 */

public class ZhihuDailyLocalSource implements ZhihuDailySource {

    private ZhihuDailyLocalSource() {
    }

    public static ZhihuDailyLocalSource getInstance() {
        return ZhihuDailyLocalSourceHolder.mLocalSource;
    }

    // FIXME maybe some problem
    public void saveAll(ZhihuDailyBean zhihuDailyBean) {
        ZhihuDailyBeanDao zhihuDailyBeanDao = App.getDaoSession().getZhihuDailyBeanDao();
        ZhihuStoryDao zhihuStoryDao = App.getDaoSession().getZhihuStoryDao();
        if(zhihuDailyBeanDao!=null){
            try {
                // 设置unique的索引，不能重复插入，故使用replace方法
                zhihuDailyBeanDao.insertOrReplace(zhihuDailyBean);
            }catch(Exception e){
                Log.e("local bean",e.getMessage()+"");
            }
        }

        if(zhihuStoryDao!=null){
            String date = zhihuDailyBean.getDate();
            List<ZhihuStory> stories = zhihuDailyBean.getStories();
            for (ZhihuStory story :stories){
                story.setDate(date);
            }
            try {
                zhihuStoryDao.insertOrReplaceInTx(stories);
            }catch(Exception e){
                Log.e("local",e.getStackTrace().toString()+e.getMessage()+"");
            }
        }
    }

    @Override
    public void loadZhihuDailySource(boolean forceUpdate, boolean cleanCache, String date, Observer<ZhihuDailyBean> observer) {
        ZhihuStoryDao zhihuStoryDao = App.getDaoSession().getZhihuStoryDao();
        String pDate=DateFormatUtil.getZhihuPreviousDay(date);
        List<ZhihuStory> stories = zhihuStoryDao._queryZhihuDailyBean_Stories(pDate);
        if(stories!=null && !stories.isEmpty()){
            observer.onNext(new ZhihuDailyBean(pDate,stories));
        }else{
            observer.onError(new Exception("no data"));
        }
    }

    private static class ZhihuDailyLocalSourceHolder {
        private static ZhihuDailyLocalSource mLocalSource = new ZhihuDailyLocalSource();
    }
}
