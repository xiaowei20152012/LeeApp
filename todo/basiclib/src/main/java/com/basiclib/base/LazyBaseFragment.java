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

public abstract class LazyBaseFragment extends Fragment {
    protected Activity mActivity;
    protected View mRootView;
    private Unbinder mUnbinder;
    public boolean hasInitialized = false;//视图是否已经初始化
    public boolean hasLoaded = false;//视图是否已经加载过数据

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
        hasInitialized = true;
        isCanLoadData();
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

    /**
     * 视图销毁时将Fragment是否初始化的状态变为false
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hasInitialized = false;
        hasLoaded = false;
        mUnbinder.unbind();
    }


    /**
     * 判断是否可以加载数据，如果可以便进行数据的加载
     */
    private void isCanLoadData() {
        if (!hasInitialized) {
            return;
        }

        //如果可见且未曾加载过数据
        if (getUserVisibleHint() && !hasLoaded) {
            lazyLoad();
            hasLoaded = true;
        } else if (hasLoaded)
            stopLoad();
    }


    /**
     * 当视图初始化并且对可见时加载数据
     */
    public abstract void lazyLoad();


    /**
     * 当该视图对用户不可见并且已经加载过数据的时候，如果需要在切换到其他页面时停止加载数据，通过覆写此方法实现
     */
    public void stopLoad() {
    }


    /**
     * 说明：当前视图可见性发生变化时调用该方法
     *
     * @param isVisibleToUser 当前视图是否可见
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isCanLoadData();
    }





}
