package com.shakespace.dailyreader.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shakespace.dailyreader.R;
import com.shakespace.dailyreader.adapter.OneFragmentPageAdapter;
import com.shakespace.dailyreader.base.BaseFragment;
import com.shakespace.dailyreader.fragment.find.ZhihuDailyFragment;

import butterknife.BindArray;
import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends BaseFragment {

    private static final String TAG = "OneFragment";
    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    private OneFragmentPageAdapter mAdapter;

    @BindArray(R.array.titles)
    String[] mTitles;

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            FragmentManager fm = getChildFragmentManager();
            ZhihuDailyFragment zhihuDailyFragment = (ZhihuDailyFragment) fm.getFragment(savedInstanceState, ZhihuDailyFragment.class.getSimpleName());
            if(mAdapter!= null){
                mAdapter.setPrimaryItem(null,0,zhihuDailyFragment);
            }

        }
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @Override
    protected void initListener(View view) {

        //   不能用内部类创建  getChildFragmentManager 需要在attach后使用
        mAdapter = new OneFragmentPageAdapter(getChildFragmentManager(),mTitles);


        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setAdapter(mAdapter);
//        mViewPager.setOffscreenPageLimit(3);

//        mAdapter.getItem()

    }


    //---避免叠加
    // 使用viewpager的时候不要使用该方法，影响预加载
    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (getView() != null) {
            getView().setVisibility(menuVisible ? View.VISIBLE : View.INVISIBLE);
        }
    }
}
