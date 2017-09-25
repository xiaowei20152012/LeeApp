package com.todo.pic.presentation.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.picomponent.R;


/**
 * Created by liwei5 on 2017/9/8.
 */

public class ShelfFragment extends Fragment {
    private String text;
    public static ShelfFragment getInstance(int i){
        ShelfFragment shelfFragment = new ShelfFragment();
        Bundle bundle = new Bundle();
        bundle.putString("A",""+i);
        shelfFragment.setArguments(bundle);
        return shelfFragment;
    }

    private void getMessage(){
        Bundle a = getArguments();
        text = a.getString("A");
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMessage();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_shelf_layout,container,false);
        TextView tv = (TextView) rootView.findViewById(R.id.shelf_tv);
        tv.setText(text);

        return rootView;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
