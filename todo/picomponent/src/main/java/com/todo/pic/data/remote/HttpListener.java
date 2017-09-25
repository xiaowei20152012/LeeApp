package com.todo.pic.data.remote;


/**
 * Created by dongjunkun on 2016/7/13.
 */
public interface HttpListener {
    void onSuccess(String result);

    void onFailure(int errorType, String message);
}
