package com.readercomponent.applike;


import com.componentlib.applicationlike.IApplicationLike;
import com.componentlib.router.Router;
import com.componentservice.reader.ReadBookService;
import com.readercomponent.serviceimpl.ReadBookServiceImpl;

/**
 * Created by mrzhang on 2017/6/15.
 */

public class ReaderAppLike implements IApplicationLike {

    Router router = Router.getInstance();

    @Override
    public void onCreate() {
        router.addService(ReadBookService.class.getSimpleName(), new ReadBookServiceImpl());
    }

    @Override
    public void onStop() {
        router.removeService(ReadBookService.class.getSimpleName());
    }
}
