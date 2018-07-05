package com.shakespace.dailyreader.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shakespace.dailyreader.R;
import com.shakespace.dailyreader.base.BaseFragment;
import com.shakespace.dailyreader.bean.ZhihuDailyBean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends BaseFragment {

    private static final String TAG = "BlankFragment";
    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    protected void initListener(View view) {

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG,"blank");
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    class  MyObserver implements Observer<ZhihuDailyBean> {

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(ZhihuDailyBean zhihuDailyBean) {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }


}



