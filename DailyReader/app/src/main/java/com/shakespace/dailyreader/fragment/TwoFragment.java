package com.shakespace.dailyreader.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shakespace.dailyreader.R;
import com.shakespace.dailyreader.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends BaseFragment {


    public TwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    @Override
    protected void initListener(View view) {

    }

    //---避免叠加
    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (getView() != null) {
            getView().setVisibility(menuVisible ? View.VISIBLE : View.INVISIBLE);
        }
    }
}
