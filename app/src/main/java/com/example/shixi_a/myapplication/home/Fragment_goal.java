package com.example.shixi_a.myapplication.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shixi_a.myapplication.R;

/**
 * Created by Shixi-A on 2017/5/17.
 */

public class Fragment_goal extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goal, container, false);
        return view;
        //super.onCreateView(inflater, container, savedInstanceState);
    }
}
