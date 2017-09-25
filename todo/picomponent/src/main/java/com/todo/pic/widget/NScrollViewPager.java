package com.todo.pic.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by liwei5 on 2017/9/12.
 */

public class NScrollViewPager extends ViewPager {
    public NScrollViewPager(Context context) {
        super(context);
    }

    public NScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item,false);
    }
}
