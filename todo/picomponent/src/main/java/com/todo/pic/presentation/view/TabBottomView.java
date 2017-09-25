package com.todo.pic.presentation.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.picomponent.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liwei5 on 2017/9/6.
 */

public class TabBottomView extends LinearLayout implements View.OnClickListener {

    private OnItemClickListener mOnItemClickListener;

    interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    private int normalTextColor;
    private int selectTextColor;

//    private LinearLayout mLinearLayout;
    private List<TabEntity> tabList = new ArrayList<>();

    public TabBottomView(Context context) {
        super(context);
        init(context);
    }

    public TabBottomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
//        mLinearLayout = (LinearLayout) View.inflate(context, R.layout.container_layout, null);
//        addView(mLinearLayout);
    }

    public void setNormalTextColor(int color) {
        this.normalTextColor = color;
    }

    public void setSelectTextColor(int color) {
        this.selectTextColor = color;
    }

    public void setTabList(List<TabEntity> tabs) {
        if (tabs == null || tabs.size() == 0) {
            return;
        }
        this.tabList = tabs;
        for (int i = 0; i < tabs.size(); i++) {
            View itemView = View.inflate(getContext(), R.layout.tab_item, null);
            itemView.setId(i);
            itemView.setOnClickListener(this);
            TextView text = (TextView) itemView.findViewById(R.id.tv_title);
            ImageView icon = (ImageView) itemView.findViewById(R.id.iv_icon);
            View redPoint = itemView.findViewById(R.id.red_point);
            TextView number = (TextView) itemView.findViewById(R.id.tv_count);
            TabEntity itemTab = tabList.get(i);
            text.setText(itemTab.getText());
            text.setTextColor(normalTextColor);
            icon.setImageResource(itemTab.getNormalIconId());
            if (itemTab.isShowPoint()) {
                redPoint.setVisibility(View.VISIBLE);
            } else {
                redPoint.setVisibility(View.GONE);
            }
            if (itemTab.getNewsCount() == 0) {
                number.setVisibility(View.GONE);
            } else if (itemTab.getNewsCount() > 99) {
                number.setVisibility(View.VISIBLE);
                number.setText("99+");
            } else {
                number.setVisibility(View.VISIBLE);
                number.setText(String.format("%d", itemTab.getNewsCount()));
            }
            addView(itemView);
            if (i == 0) {
                showTab(0, itemView);
            }

        }
    }


    @Override
    public void onClick(View view) {
        if (mOnItemClickListener == null) {
            return;
        }
        switch (view.getId()) {
            case 0:
                mOnItemClickListener.onItemClick(0);
                showTab(0, view);
                break;
            case 1:
                mOnItemClickListener.onItemClick(1);
                showTab(1, view);
                break;
            case 2:
                mOnItemClickListener.onItemClick(2);
                showTab(2, view);
                break;
            case 3:
                mOnItemClickListener.onItemClick(3);
                showTab(3, view);
                break;
        }
    }

    public void showTab(int position, View view) {
        clearStatus();
        TextView text = (TextView) view.findViewById(R.id.tv_title);
        text.setTextColor(selectTextColor);
        ImageView icon = (ImageView) view.findViewById(R.id.iv_icon);
        icon.setImageResource(tabList.get(position).getSelectIconId());

    }

    private void clearStatus() {
        for (int i = 0; i < getChildCount(); i++) {
            View itemView = getChildAt(i);
            ImageView icon = (ImageView) itemView.findViewById(R.id.iv_icon);
            TextView text = (TextView) itemView.findViewById(R.id.tv_title);
            text.setTextColor(normalTextColor);
            icon.setImageResource(tabList.get(i).getNormalIconId());
        }
    }

    public class TabEntity {
        private String text;
        private int normalIconId;
        private int selectIconId;
        private boolean isShowPoint;
        private int newsCount;

        public int getNewsCount() {
            return newsCount;
        }

        public void setNewsCount(int newsCount) {
            this.newsCount = newsCount;
        }

        public boolean isShowPoint() {
            return isShowPoint;
        }

        public void setShowPoint(boolean showPoint) {
            isShowPoint = showPoint;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getNormalIconId() {
            return normalIconId;
        }

        public void setNormalIconId(int normalIconId) {
            this.normalIconId = normalIconId;
        }

        public int getSelectIconId() {
            return selectIconId;
        }

        public void setSelectIconId(int selectIconId) {
            this.selectIconId = selectIconId;
        }
    }
}
