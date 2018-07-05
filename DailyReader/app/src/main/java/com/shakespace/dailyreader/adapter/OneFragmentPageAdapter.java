package com.shakespace.dailyreader.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.shakespace.dailyreader.fragment.BlankFragment;
import com.shakespace.dailyreader.fragment.BlankFragment2;
import com.shakespace.dailyreader.fragment.find.ZhihuDailyFragment;

/**
 * Created by shakespace on 2018/3/24.
 */

public class OneFragmentPageAdapter extends FragmentPagerAdapter {

    private  String [] mTitles;
    public OneFragmentPageAdapter(FragmentManager fm,String [] titles) {
        super(fm);
        this.mTitles = titles;
    }

    Fragment mFragment = null;

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                mFragment = new ZhihuDailyFragment();
                break;
            case 1:
                mFragment = new BlankFragment();
                break;
            case 2:
                mFragment = new BlankFragment2();
                break;
        }
        return mFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // 注释super  这样不会销毁  达到不重复创建的效果
//        super.destroyItem(container, position, object);
    }
}
