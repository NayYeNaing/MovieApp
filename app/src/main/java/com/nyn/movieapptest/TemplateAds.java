package com.nyn.movieapptest;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.style.BackgroundColorSpan;
import android.view.View;

import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.UnifiedNativeAd;

public class TemplateAds {
   static Context context;

    public TemplateAds(Context context) {
        this.context = context;
    }

    public TemplateAds() {
    }

    public static void TemplateAdsAdd(final TemplateView template){
        MobileAds.initialize(context,context.getResources().getString(R.string.admobid));
        AdLoader adLoader = new AdLoader.Builder(context, context.getResources().getString(R.string.NativeAds))
                .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                        ColorDrawable background = new ColorDrawable(Color.WHITE);
                        NativeTemplateStyle styles = new
                                NativeTemplateStyle.Builder().withMainBackgroundColor(background).build();

                        template.setStyles(styles);
                        template.setNativeAd(unifiedNativeAd);

                    }
                })
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());
    }
    }

