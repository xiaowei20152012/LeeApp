package com.todo.pic.presentation.interfaces;


import com.basiclib.presenter.BasePresenter;
import com.basiclib.presenter.BaseView;
import com.todo.pic.domain.model.HomeInfo;

import java.util.List;

/**
 * Created by liwei5 on 2017/9/13.
 * 首页fragment
 */

public interface HomeContract {
    interface View extends BaseView<Presenter> {
        void showLoadingView();
        void hideLoadingView();
        void updateView(List<HomeInfo> list);

        boolean isActive();

    }
    interface Presenter extends BasePresenter {

    }
}
