package com.example.dell.mycook.fragments;

import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by dell on 3/9/2017.
 */

public class BaseFragment extends Fragment {
    View contentView;

    public void setContentView(View contentView) {
        this.contentView = contentView;
    }

    public View getContentView() {
        return contentView;
    }

    protected View findViewById(int res){
        return getContentView().findViewById(res);
    }
}
