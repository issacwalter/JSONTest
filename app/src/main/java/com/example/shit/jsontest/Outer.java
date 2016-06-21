package com.example.shit.jsontest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by He on 2016/3/1.
 */
public class Outer extends android.support.v4.app.Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    MyPagerAdapter pagerAdapter;
    private LayoutInflater layoutInflater;
    private List<String> titleList = new ArrayList<>();
    List<View> viewList = new ArrayList<>();
    View view1, view2, view3, view4, view5;
    private Button buttonOfView2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("TTEST", "in fragment");
        View view = inflater.inflate(R.layout.outerlayout, container, false);
//        To inflate inner class in outer
//        FragmentManager manager = getChildFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        Inner inner = new Inner() ;
//        transaction.replace(R.id.outerfram,inner);
//        transaction.commit();
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        layoutInflater = LayoutInflater.from(this.getActivity());
        view1 = inflater.inflate(R.layout.view1, null);
        view2 = inflater.inflate(R.layout.view2, null);
        view3 = inflater.inflate(R.layout.view3, null);
        view4 = inflater.inflate(R.layout.view4, null);
        view5 = inflater.inflate(R.layout.view5, null);


        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);
        viewList.add(view5);

        titleList.add("A");
        titleList.add("B");
        titleList.add("C");
        titleList.add("D");
        titleList.add("E");

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < titleList.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(titleList.get(i)));
        }

        pagerAdapter = new MyPagerAdapter(viewList, titleList);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

}
