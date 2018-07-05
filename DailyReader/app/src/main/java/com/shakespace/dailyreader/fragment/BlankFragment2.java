package com.shakespace.dailyreader.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shakespace.dailyreader.R;
import com.shakespace.dailyreader.base.BaseFragment;


public class BlankFragment2 extends BaseFragment {


    public BlankFragment2() {
        // Required empty public constructor
    }

    @Override
    protected void initListener(View view) {

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
    }





}
