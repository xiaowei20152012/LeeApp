package com.todo.pic.domain.usecase;

import android.support.annotation.NonNull;


import com.basiclib.domain.UseCase;
import com.todo.pic.data.TasksDataSource;
import com.todo.pic.data.TasksRepository;
import com.todo.pic.domain.model.HomeInfo;

import java.util.List;

/**
 * Created by liwei5 on 2017/9/14.
 */

public class GetPicsTask extends UseCase<GetPicsTask.RequestValues,GetPicsTask.ResponseValue> {
    private final TasksRepository mTasksRepository;

//    private final FilterFactory mFilterFactory;

    public GetPicsTask(@NonNull TasksRepository tasksRepository) {
        mTasksRepository = tasksRepository;
//        mFilterFactory = checkNotNull(filterFactory, "filterFactory cannot be null!");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
//                if (values.isForceUpdate()) {
//            mTasksRepository.refreshTasks();
//        }

        mTasksRepository.getTasks(new TasksDataSource.LoadTasksCallback() {
            @Override
            public void onTasksLoaded(List<HomeInfo> tasks) {
//                TasksFilterType currentFiltering = values.getCurrentFiltering();
//                TaskFilter taskFilter = mFilterFactory.create(currentFiltering);
//
//                List<HomeInfo> tasksFiltered = taskFilter.filter(tasks);
                ResponseValue responseValue = new ResponseValue(tasks);
                getUseCaseCallback().onSuccess(responseValue);
            }

            @Override
            public void onDataNotAvailable() {
                getUseCaseCallback().onError();
            }
        });
//        if (values.isForceUpdate()) {
//            mTasksRepository.refreshTasks();
//        }
//
//        mTasksRepository.getTasks(new TasksDataSource.LoadTasksCallback() {
//            @Override
//            public void onTasksLoaded(List<Task> tasks) {
//                TasksFilterType currentFiltering = values.getCurrentFiltering();
//                TaskFilter taskFilter = mFilterFactory.create(currentFiltering);
//
//                List<Task> tasksFiltered = taskFilter.filter(tasks);
//                ResponseValue responseValue = new ResponseValue(tasksFiltered);
//                getUseCaseCallback().onSuccess(responseValue);
//            }
//
//            @Override
//            public void onDataNotAvailable() {
//                getUseCaseCallback().onError();
//            }
//        });
    }

    public static final class RequestValues implements UseCase.RequestValues {

//        private final TasksFilterType mCurrentFiltering;
        private final boolean mForceUpdate;
        public RequestValues(boolean forceUpdate) {
            mForceUpdate = forceUpdate;
//            mCurrentFiltering = currentFiltering;
        }
//        public RequestValues(boolean forceUpdate, @NonNull TasksFilterType currentFiltering) {
//            mForceUpdate = forceUpdate;
//            mCurrentFiltering = currentFiltering;
//        }

//        public boolean isForceUpdate() {
//            return mForceUpdate;
//        }

//        public TasksFilterType getCurrentFiltering() {
//            return mCurrentFiltering;
//        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        private final List<HomeInfo> mTasks;

        public ResponseValue(@NonNull List<HomeInfo> tasks) {
            mTasks = tasks;
        }

        public List<HomeInfo> getTasks() {
            return mTasks;
        }
    }
}
