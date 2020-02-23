package com.nyn.movieapptest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

public class SeriesList extends AppCompatActivity {
    public static RecyclerView recyclerView_SeriesItem;
    public static ImageView imageView ;
    static SeriesModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_list);
        recyclerView_SeriesItem = findViewById(R.id.seriesItemShow);
        imageView = findViewById(R.id.ImageViewSeries);
        FireBaseDataConnect fireBaseDataConnect = new FireBaseDataConnect(getApplicationContext());
        fireBaseDataConnect.getMoviesFromSeries(model.title);
    }
}
