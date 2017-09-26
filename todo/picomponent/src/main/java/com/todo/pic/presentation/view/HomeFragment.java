package com.todo.pic.presentation.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.basiclib.base.LazyBaseFragment;
import com.bumptech.glide.Glide;
import com.picomponent.R;
import com.todo.pic.domain.model.HomeInfo;
import com.todo.pic.presentation.interfaces.HomeContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liwei5 on 2017/9/14.
 * 
 */

public class HomeFragment extends LazyBaseFragment implements HomeContract.View{

    private HomeContract.Presenter mPresenter;
    private HomeAdapter mAdapter;
    private RecyclerView mRecyclerView;
    @Override
    protected int getLayoutId() {
        getMessage();
        return R.layout.fragment_home;
    }
    private String text;
    public static HomeFragment getInstance(int i){
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("A",""+i);
        fragment.setArguments(bundle);
        return fragment;
    }

    private void getMessage(){
        Bundle a = getArguments();
        text = a.getString("A");
    }
    @Override
    protected void init() {
//        mRootView.findViewById()

        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.base_list_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<HomeInfo> list = new ArrayList<>();
        for (int i=0;i<4;i++){
            HomeInfo info = new HomeInfo();
            info.setUrl("https://ws1.sinaimg.cn/large/610dc034ly1fjfae1hjslj20u00tyq4x.jpg");
            list.add(info);
        }
        mAdapter = new HomeAdapter(list, mItemListener);
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.start();
    }

    @Override
    public void lazyLoad() {

    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public void updateView(List<HomeInfo> list) {
        mAdapter.replaceData(list);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


    private static class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private List<HomeInfo> mTasks;
        private HomeItemListener mItemListener;

        public HomeAdapter(List<HomeInfo> tasks, HomeItemListener itemListener) {
            setList(tasks);
            mItemListener = itemListener;
        }

        public void replaceData(List<HomeInfo> tasks) {
            setList(tasks);
            notifyDataSetChanged();
        }

        private void setList(List<HomeInfo> tasks) {
            mTasks = tasks;
        }
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new HomeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_pic_layout,parent,false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            HomeViewHolder viewHolder = (HomeViewHolder)holder;
            viewHolder.mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemListener.onTaskClick();
                }
            });
            HomeInfo info = mTasks.get(position);
            Glide.with(viewHolder.mImageView.getContext()).load(info.getUrl()).into(viewHolder.mImageView);
        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }
    }
    private static class HomeViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImageView;

        public HomeViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.home_iv);
        }
    }
    HomeItemListener mItemListener = new HomeItemListener() {
        @Override
        public void onTaskClick() {
//            mPresenter.completeTask(completedTask);
        }
    };
    public interface HomeItemListener {

        void onTaskClick();

//        void onCompleteTaskClick(Task completedTask);
//
//        void onActivateTaskClick(Task activatedTask);
    }
}
