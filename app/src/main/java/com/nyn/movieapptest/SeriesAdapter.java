package com.nyn.movieapptest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import java.util.ArrayList;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.SeriesHolder> {
    ArrayList<SeriesModel> arrayList= new ArrayList<>();
    Context context;
    Activity activity;
    RewardedAd rewardedAd;

    public SeriesAdapter() {
    }

    public SeriesAdapter(ArrayList<SeriesModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        rewardedAd = new RewardedAd(context, context.getResources().getString(R.string.RewardId));
        MobileAds.initialize(context,context.getResources().getString(R.string.admobid));
        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
                // Ad successfully loaded.
            }

            @Override
            public void onRewardedAdFailedToLoad(int errorCode) {
                // Ad failed to load.
            }
        };
        rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);
    }

    @NonNull
    @Override
    public SeriesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seriesitem,parent,false);
        return new SeriesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesHolder holder, final int position) {
        Glide.with(context)
                .load(arrayList.get(position).image)
                .into(holder.imageView);
        holder.textView.setText(arrayList.get(position).getTitle());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rewardedAd.isLoaded()) {
                    RewardedAdCallback adCallback = new RewardedAdCallback() {
                        @Override
                        public void onRewardedAdOpened() {
                            // Ad opened.
                        }

                        @Override
                        public void onRewardedAdClosed() {
                            rewardedAd = createAndLoad();
                        }

                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem reward) {
                            Intent intent = new Intent(context,SeriesList.class);
                            SeriesList.model = arrayList.get(position);
                            context.startActivity(intent);
                        }

                        @Override
                        public void onRewardedAdFailedToShow(int errorCode) {
                            // Ad failed to display.
                        }
                    };
                    rewardedAd.show(activity, adCallback);
                } else {
                    Log.d("TAG", "The rewarded ad wasn't loaded yet.");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class SeriesHolder extends RecyclerView.ViewHolder{
        ImageView imageView ;
        TextView textView;
        public SeriesHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.SeriesImageView);
            textView = itemView.findViewById(R.id.SeriesTextView);
        }
    }

    public RewardedAd createAndLoad(){
        rewardedAd = new RewardedAd(context, context.getResources().getString(R.string.RewardId));
        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
                // Ad successfully loaded.
            }

            @Override
            public void onRewardedAdFailedToLoad(int errorCode) {
                // Ad failed to load.
            }
        };
        rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);
        return rewardedAd;
    }
}
