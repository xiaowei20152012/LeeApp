package com.readercomponent.serviceimpl;

import android.support.v4.app.Fragment;

import com.componentservice.reader.ReadBookService;
import com.readercomponent.ReaderFragment;


/**
 * Created by mrzhang on 2017/6/15.
 */

public class ReadBookServiceImpl implements ReadBookService {
    @Override
    public Fragment getReadBookFragment() {
        return new ReaderFragment();
    }
}
