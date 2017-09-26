package com.notescomponent.runalone;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.componentlib.router.Router;
import com.componentservice.notes.NotesService;
import com.notescomponent.NotesFragemnt;
import com.notescomponent.R;


/**
 * Created by liwei on 2017/6/15.
 */

public class NotesTestActivity extends AppCompatActivity {
//    Router router = Router.getInstance();
//    if (router.getService(ReadBookService.class.getSimpleName()) != null) {
//        ReadBookService service = (ReadBookService) router.getService(ReadBookService.class.getSimpleName());
//        fragment = service.getReadBookFragment();
//        ft = getSupportFragmentManager().beginTransaction();
//        ft.add(R.id.tab_content, fragment).commitAllowingStateLoss();
//    }

//
//   ReaderFragment fragment;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.readerbook_activity_test);
//        fragment= new ReaderFragment();
//        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.add(R.id.tab_content, fragment).commitAllowingStateLoss();
//    }

    Router router = Router.getInstance();
    private NotesFragemnt fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_activity_test);
        if (router.getService(NotesService.class.getSimpleName()) != null) {
            NotesService service = (NotesService) router.getService(NotesService.class.getSimpleName());
            fragment = (NotesFragemnt) service.getNotesFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.tab_content, fragment).commitAllowingStateLoss();
        }
    }
}
