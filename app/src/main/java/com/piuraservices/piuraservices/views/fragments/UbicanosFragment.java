package com.piuraservices.piuraservices.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.piuraservices.piuraservices.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UbicanosFragment extends Fragment {


    public UbicanosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ubicanos, container, false);
    }

}
