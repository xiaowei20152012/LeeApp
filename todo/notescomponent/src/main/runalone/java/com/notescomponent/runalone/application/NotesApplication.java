package com.notescomponent.runalone.application;

import android.app.Application;

import com.componentlib.router.Router;


/**
 * Created by liwei on 2017/6/20.
 */

public class NotesApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //如果isRegisterCompoAuto为false，则需要通过反射加载组件  依赖组件需要手动加入
//        Router.registerComponent("com.mrzhang.share.applike.ShareApplike");
        Router.registerComponent("com.notescomponent.applike.NotesAppLike");
    }

}
