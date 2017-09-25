package com.basiclib.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by liwei5 on 2017/8/31.
 */

public abstract class BaseFragment extends Fragment {
    protected Activity mActivity;
    protected View mRootView;
    private Unbinder mUnbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity)context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(),container,false);
        mUnbinder = ButterKnife.bind(this,mRootView);
        init();
        return mRootView;
    }
    /**
     * @return 返回该Fragment的layout id
     */
    protected abstract int getLayoutId();


    /**
     * 说明：创建视图时的初始化操作均写在该方法
     */
    protected abstract void init();


    /**
     * 获取控件对象
     *
     * @param id 控件id
     * @return 控件对象
     */
    public View findViewById(int id) {
        if (getContentView() != null) {
            return getContentView().findViewById(id);
        } else {
            return null;
        }
    }

    /**
     * 说明：返回当前View
     *
     * @return view
     */
    protected View getContentView() {
        return mRootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
