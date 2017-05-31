package com.example.dell.mycook.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.mycook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeOfweek extends BaseFragment {


    public RecipeOfweek() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        setContentView(inflater.inflate(R.layout.fragment_recipe_ofweek, container, false));

        return getContentView();
    }

}
