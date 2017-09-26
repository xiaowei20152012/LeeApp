package com.notescomponent.applike;

import com.componentlib.applicationlike.IApplicationLike;
import com.componentlib.router.Router;
import com.componentservice.notes.NotesService;
import com.notescomponent.serviceimpl.NotesServiceImpl;

/**
 * Created by liwei on 17/9/26.
 */

public class NotesAppLike implements IApplicationLike {
    Router router = Router.getInstance();
    @Override
    public void onCreate() {
        router.addService(NotesService.class.getSimpleName(),new NotesServiceImpl());
    }

    @Override
    public void onStop() {
        router.removeService(NotesService.class.getSimpleName());
    }
}
