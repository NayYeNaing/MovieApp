package com.nyn.movieapptest;

import android.content.Context;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class Ads {
    static Context context;

    public Ads() {
    }

    public static void loadAds(View view){
        MobileAds.initialize(context,context.getResources().getString(R.string.admobid));
        AdView mAdView = view.findViewById(R.id.adView);
        AdView mAdView2 = view.findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mAdView2.loadAd(adRequest2);
    }
    }
