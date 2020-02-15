package com.nyn.movieapptest;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;





/**
 * A simple {@link Fragment} subclass.
 */
public class SeriesFragment extends Fragment {

    public static RecyclerView Srv ;
    public static ViewFlipper viewFlipper ;

    public SeriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_series, container, false);
        Srv = view.findViewById(R.id.Srv);
        viewFlipper = view.findViewById(R.id.viewFlip);
        FireBaseDataConnect fireBaseDataConnect = new FireBaseDataConnect(getContext());
        fireBaseDataConnect.getSeries();
        Ads.context = getContext();
        Ads.loadAds(view);
        return view;
    }

}
