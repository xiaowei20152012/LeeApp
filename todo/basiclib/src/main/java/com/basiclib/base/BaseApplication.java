package com.basiclib.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by liwei5 on 2017/9/14.
 */

public class BaseApplication {
    private static Application mApplication;
    private BaseApplication(){}
    public static void setContext(Application context){
        if (context!=null){
            mApplication = context;
        }
    }
    public static Application getContext(){
        if (mApplication!=null)
            return mApplication;
        return null;
    }
}
