package com.sk.collapse.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sk on 16-9-2.
 */
public class HomeFragment extends Fragment {


    private ViewPager mViewPager;
    private List<Fragment> mList;
    private MyFragAdapter mAdapter;
    private TabLayout mTablayout;


    public HomeFragment() {

    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.home_fragment, container, false);

        mViewPager  = (ViewPager) root.findViewById(R.id.id_viewpager);
        mTablayout = (TabLayout) root.findViewById(R.id.id_tab);


        mList = new ArrayList<>();
        mList.add(Tab0Fragment.newInstance());
        mList.add(Tab1Fragment.newInstance());
        mList.add(Tab2Fragment.newInstance());

        mAdapter = new MyFragAdapter(getFragmentManager());
        mViewPager.setAdapter(mAdapter);

        mTablayout.setupWithViewPager(mViewPager);

        return root;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }



    private class MyFragAdapter extends FragmentStatePagerAdapter {

        private Context context;
        private List<Fragment> list;

        public MyFragAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            super.getPageTitle(position);
            String []titles = getResources().getStringArray(R.array.tab_titles);
            return titles[position % titles.length];
        }
    }
}
