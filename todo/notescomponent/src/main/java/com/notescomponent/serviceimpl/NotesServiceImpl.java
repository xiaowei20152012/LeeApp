package com.notescomponent.serviceimpl;

import android.support.v4.app.Fragment;

import com.componentservice.notes.NotesService;
import com.notescomponent.NotesFragemnt;

/**
 * Created by liwei on 17/9/26.
 */

public class NotesServiceImpl implements NotesService {
    @Override
    public Fragment getNotesFragment() {
        return new NotesFragemnt();
    }
}
