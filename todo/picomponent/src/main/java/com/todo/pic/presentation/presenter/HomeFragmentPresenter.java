package com.todo.pic.presentation.presenter;


import com.basiclib.domain.UseCase;
import com.basiclib.domain.UseCaseHandler;
import com.todo.pic.domain.model.HomeInfo;
import com.todo.pic.domain.usecase.GetPicsTask;
import com.todo.pic.presentation.interfaces.HomeContract;

import java.util.List;

/**
 * Created by liwei5 on 2017/9/13.
 */

public class HomeFragmentPresenter implements HomeContract.Presenter {
    private UseCaseHandler mUseCaseHandler;
    private final GetPicsTask mGetTasks;
    private HomeContract.View mView;
    public HomeFragmentPresenter(UseCaseHandler useCaseHandler, HomeContract.View homeView, GetPicsTask getTasks){
        this.mUseCaseHandler = useCaseHandler;
        this.mView = homeView;
        this.mGetTasks = getTasks;

        mView.setPresenter(this);//绑定view 与presenter
    }
    @Override
    public void start() {
        mView.showLoadingView();
//        load
        loadTasks(true,true);

    }
    /**
     * @param forceUpdate   Pass in true to refresh the data in the {@link }
     * @param showLoadingUI Pass in true to display a loading icon in the UI
     */
    private void loadTasks(boolean forceUpdate, final boolean showLoadingUI) {
        if (showLoadingUI) {
//            mTasksView.setLoadingIndicator(true);
        }

        GetPicsTask.RequestValues requestValue = new GetPicsTask.RequestValues(forceUpdate);

        mUseCaseHandler.execute(mGetTasks, requestValue,
                new UseCase.UseCaseCallback<GetPicsTask.ResponseValue>() {
                    @Override
                    public void onSuccess(GetPicsTask.ResponseValue response) {
                        List<HomeInfo> tasks = response.getTasks();
                        // The view may not be able to handle UI updates anymore
                        if (!mView.isActive()) {
                            return;
                        }
                        if (showLoadingUI) {
//                            mView.setLoadingIndicator(false);
                        }
                        mView.updateView(tasks);
//
//                        processTasks(tasks);
                    }

                    @Override
                    public void onError() {
                        // The view may not be able to handle UI updates anymore
                        if (!mView.isActive()) {
                            return;
                        }
//                        mView.showLoadingTasksError();
                    }
                });
    }
}
