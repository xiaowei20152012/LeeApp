package com.todo.pic.presentation.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.picomponent.R;


/**
 * Created by liwei5 on 2017/9/8.
 */

public class HomeTabManager implements View.OnClickListener{

    private Context mContext;
    private View rootView;
    private TabOnClickListener mTabOnClickListener;
    public HomeTabManager(Context context, View rootView, TabOnClickListener listener){
        this.mContext = context;
        this.rootView = rootView;
        this.mTabOnClickListener = listener;
        initView();
    }
    private View mShelfRl,mStoreRl,mFreeRl,mSpaceRl;
    private ImageView mShelfIv,mStoreIv,mFreeIv,mSpaceIv;
    private TextView mShelfTv,mStoreTv,mFreeTv,mSpaceTv;
    private TextView mShelfPointTv,mStorePointTv,mFreePointTv,mSpacePointTv;
    private void initView(){
        mShelfRl = rootView.findViewById(R.id.home_shelf_rl);
        mShelfRl.setOnClickListener(this);
        mStoreRl = rootView.findViewById(R.id.home_store_rl);
        mStoreRl.setOnClickListener(this);
        mFreeRl = rootView.findViewById(R.id.home_free_rl);
        mFreeRl.setOnClickListener(this);
        mSpaceRl = rootView.findViewById(R.id.home_space_rl);
        mSpaceRl.setOnClickListener(this);
        //iv
//        mShelfIv = rootView.findViewById(R.id.hom)
        //tv

        //point
        mSpacePointTv = (TextView) rootView.findViewById(R.id.space_point_tv);
        mSpacePointTv.setVisibility(View.GONE);
    }


    @Override
    public void onClick(final View view) {
        int id = view.getId();
        if (id == R.id.home_shelf_rl){
            switchPosition(0);
            onTabClick(view ,0);
        } else if (id == R.id.home_store_rl){
            switchPosition(1);
            onTabClick(view ,1);
        } else if (id == R.id.home_free_rl){
            switchPosition(2);
            onTabClick(view ,2);
        } else if (id == R.id.home_space_rl){
            switchPosition(3);
            onTabClick(view ,3);
        }

    }
    private void onTabClick(View v, int position){
        if (mTabOnClickListener!=null)
            mTabOnClickListener.onClick(v,position);
    }
    private void switchPosition(int positon){
        if (positon>3)
            return;
        mShelfRl.setSelected(false);
        mStoreRl.setSelected(false);
        mFreeRl.setSelected(false);
        mSpaceRl.setSelected(false);
        switch (positon){
            case 0:
                mShelfRl.setSelected(true);
                break;
            case 1:
                mStoreRl.setSelected(true);

                break;
            case 2:
                mFreeRl.setSelected(true);

                break;
            case 3:
                mSpaceRl.setSelected(true);

                break;
        }
    }
    public void setCurrentItem(int position){
        switchPosition(position);
    }
    public void setCurrentPointVisible(boolean shouldVisible){
        if (mSpacePointTv!=null){
            if (shouldVisible){
                mSpacePointTv.setVisibility(View.VISIBLE);
            } else {
                mSpacePointTv.setVisibility(View.GONE);
            }
        }
    }
    public interface TabOnClickListener{
        void onClick(View v, int position);
    }
}
