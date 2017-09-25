package com.todo.pic.common;

import android.content.Context;
import android.support.annotation.NonNull;

import com.basiclib.domain.UseCaseHandler;
import com.todo.pic.data.TasksRepository;
import com.todo.pic.data.local.TasksLocalDataSource;
import com.todo.pic.data.remote.TasksRemoteDataSource;
import com.todo.pic.domain.usecase.GetPicsTask;

/**
 * Created by liwei5 on 2017/9/14.
 */

public class Injection {
    //提供全局的use case handler
    public static UseCaseHandler provideUseCaseHandler() {
        return UseCaseHandler.getInstance();
    }
    public static TasksRepository provideTasksRepository(@NonNull Context context) {
        return TasksRepository.getInstance(TasksRemoteDataSource.getInstance(),
                TasksLocalDataSource.getInstance(context));
    }
    public static GetPicsTask provideGetTasks(@NonNull Context context) {
        return new GetPicsTask(provideTasksRepository(context));
    }

}
