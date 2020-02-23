package com.nyn.movieapptest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SeriesItemAdapter extends RecyclerView.Adapter<SeriesItemAdapter.SeriesItemHolder> {
    ArrayList<MovieModel> arrayList = new ArrayList<>();
    Context context;

    public SeriesItemAdapter() {
    }

    @NonNull
    @Override
    public SeriesItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.series_list_design,parent,false);
        return new SeriesItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesItemHolder holder, int position) {
        Glide.with(context)
                .load(arrayList.get(position).movieImageLink)
                .into(holder.imageView);
        holder.textViewTitle.setText(arrayList.get(position).getMovieSeries());
        holder.textViewEpisodes.setText(arrayList.get(position).getMovieTitle());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public SeriesItemAdapter(ArrayList<MovieModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public class SeriesItemHolder extends RecyclerView.ViewHolder{
        TextView textViewTitle,textViewEpisodes;
        ImageView imageView;
         public SeriesItemHolder(@NonNull View itemView) {
             super(itemView);
             textViewTitle = itemView.findViewById(R.id.textView_seiresItem_Title);
             textViewEpisodes = itemView.findViewById(R.id.textView_seiresItem_Episodes);
             imageView = itemView.findViewById(R.id.ImageView_seriesItem);
         }
     }
}
