package com.nyn.movieapptest;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;

import recycler.coverflow.RecyclerCoverFlow;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    public  static RecyclerView Mrv,HMRV,ALLMVRV ;
    public static RecyclerCoverFlow Rcf;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        Mrv = view.findViewById(R.id.rvMovie);
        HMRV = view.findViewById(R.id.rvHotMovie);
        ALLMVRV = view.findViewById(R.id.rvAllMovie);
        Rcf = view.findViewById(R.id.RcoverFlow);
        FireBaseDataConnect fireBaseDataConnect = new FireBaseDataConnect(getContext());
        fireBaseDataConnect.getMovies();
        Ads.context = getContext();
        Ads.loadAds(view);
        return view;
    }
}
