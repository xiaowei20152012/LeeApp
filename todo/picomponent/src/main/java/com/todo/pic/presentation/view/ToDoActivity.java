package com.todo.pic.presentation.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;


import com.basiclib.base.BaseActivity;
import com.picomponent.R;
import com.todo.pic.common.Injection;
import com.todo.pic.presentation.presenter.HomeFragmentPresenter;
import com.todo.pic.widget.NScrollViewPager;

import java.util.ArrayList;
import java.util.List;

public class ToDoActivity extends BaseActivity {
    private NScrollViewPager mViewPager;
    private List<Fragment> mFragments;
    HomeTabManager mTabManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        mTabManager = new HomeTabManager(this, findViewById(R.id.tab_bottom), new HomeTabManager.TabOnClickListener() {
            @Override
            public void onClick(View v, int position) {
                mViewPager.setCurrentItem(position);
            }
        });
        mViewPager = (NScrollViewPager) findViewById(R.id.home_viewpager);

        mFragments = new ArrayList<>();
        HomeFragment homeFragment = HomeFragment.getInstance(0);
        // Create the presenter
        new HomeFragmentPresenter(Injection.provideUseCaseHandler(),homeFragment, Injection.provideGetTasks(this));
        mFragments.add(homeFragment);

        for (int i=1;i<4;i++){
            Fragment fragment = ShelfFragment.getInstance(i);
            mFragments.add(fragment);
        }
        mViewPager.setAdapter(new TabFragmentAdapter(getSupportFragmentManager(),mFragments));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabManager.setCurrentItem(position);
                mTabManager.setCurrentPointVisible(false);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mTabManager.setCurrentItem(0);
        mTabManager.setCurrentPointVisible(true);
    }
    public static class TabFragmentAdapter extends FragmentPagerAdapter {

        private List<Fragment> mFragments;
        public TabFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.mFragments = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
    public static class TabStateFragmentAdapter extends FragmentStatePagerAdapter {

        public TabStateFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return 0;
        }
    }
}
