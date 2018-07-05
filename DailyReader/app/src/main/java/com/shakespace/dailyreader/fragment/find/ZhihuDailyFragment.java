package com.shakespace.dailyreader.fragment.find;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shakespace.dailyreader.R;
import com.shakespace.dailyreader.adapter.ZhihuDailyAdapter;
import com.shakespace.dailyreader.base.BaseFragment;
import com.shakespace.dailyreader.bean.ZhihuStory;
import com.shakespace.dailyreader.datasource.loader.ZhihuDailyLoader;
import com.shakespace.dailyreader.util.DateFormatUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZhihuDailyFragment extends BaseFragment implements ZhihuDailyContract.View {

    private static final String TAG = "ZhihuDailyFragment";
    @BindView(R.id.recycler_view)
    RecyclerView mReayclerView;

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;

    private ZhihuDailyPresenter mPresenter = new ZhihuDailyPresenter(this, ZhihuDailyLoader.getInstance());
    private List<ZhihuStory> mStories;
    private ZhihuDailyAdapter mAdapter;
    private int mYear, mMonth, mDay;    //年月日
    private boolean isFirstLoad = true;
    public ZhihuDailyFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initListener(View view) {
        mStories = new ArrayList<>();

        mAdapter = new ZhihuDailyAdapter(getActivity(), mStories);
        mReayclerView.setAdapter(mAdapter);
        mReayclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeZone(TimeZone.getTimeZone("GMT+08"));
                String date = DateFormatUtil.formatZhihuDailyDateLongToString(calendar.getTimeInMillis());
                mPresenter.load(true,true,date);
            }
        });
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_zhihu_daily, container, false);
    }

    /**
     * onCreate 时View还没有测量好  onStart又会影响生命周期
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Calendar calendar = Calendar.getInstance();// 以当前时间设置日历
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+08"));   // 设置时区
        mYear=calendar.get(Calendar.YEAR);
        mMonth=calendar.get(Calendar.MONTH);
        mDay= calendar.get(Calendar.DAY_OF_MONTH);

        Log.e(TAG, mYear+"-"+mMonth+"-"+mDay);

        // FIXME  待确定知乎daily每天更新的时间
        String date = DateFormatUtil.formatZhihuDailyDateLongToString(calendar.getTimeInMillis());
        // 首次加载  需要强制刷新 不需要清除缓存
        if(isFirstLoad){
            mPresenter.load(true,false,date);
            isFirstLoad= false;
        }else{
            // 再次打开App,不重新获取，也不清空缓存
            mPresenter.load(false,false,date);
        }
        Log.e(TAG, "onresume");
    }

    @Override
    public void onLoadSuccess(List<ZhihuStory> stories) {
//        mStories.clear();
//        for(ZhihuStory story:stroies){
//            if(!mStories.contains(story)){
//                Log.e(TAG,story.hashCode()+"");
//                mStories.addAll(stroies);
//            }
//        }
        mStories.addAll(stories);
        mAdapter.notifyDataSetChanged();
       setIndicator(false);
    }

    @Override
    public void onLoadError(Throwable  e) {

        setIndicator(false);
    }

    @Override
    public void onLoadComplete() {
        setIndicator(false);
    }

    @Override
    public void onLoadStart() {
        setIndicator(true);
    }

    public void setIndicator(final boolean isShowing) {
        mRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(isShowing);
            }
        });
    }

    @Override
    public void refresh() {

    }

    @Override
    public void setPresenter(ZhihuDailyContract.Presenter presenter) {

    }

}
