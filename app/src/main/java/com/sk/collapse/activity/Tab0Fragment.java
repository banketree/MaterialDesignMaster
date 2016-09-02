package com.sk.collapse.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tab0Fragment extends Fragment {



    public Tab0Fragment() {
        // Required empty public constructor
    }


    public static  Tab0Fragment newInstance() {
        return new Tab0Fragment();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = (View)inflater.inflate(R.layout.tab0_layout, container, false);

        return root;
    }

}
