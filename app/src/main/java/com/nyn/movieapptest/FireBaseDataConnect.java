package com.nyn.movieapptest;

import android.content.Context;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class FireBaseDataConnect {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference Cref = db.collection("categories");
    CollectionReference Sref = db.collection("Series");
    CollectionReference Mref = db.collection("Movies");
    Context context;

    public FireBaseDataConnect(Context context) {
        this.context = context;
    }

    public void getMovies(){
        Mref.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                ArrayList<MovieModel> MMArrayList = new ArrayList<>();
                for (DocumentSnapshot ds : queryDocumentSnapshots){
                    MMArrayList.add(ds.toObject(MovieModel.class));
                }
                MovieAdapter movieAdapter = new MovieAdapter(MMArrayList,context);
                MovieFragment.Mrv.setAdapter(movieAdapter);
                MovieFragment.Rcf.setAdapter(movieAdapter);
                MovieFragment.HMRV.setAdapter(movieAdapter);
                MovieFragment.ALLMVRV.setAdapter(movieAdapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
                LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context,3);
                MovieFragment.Mrv.setLayoutManager(linearLayoutManager);
                MovieFragment.HMRV.setLayoutManager(linearLayoutManager1);
                MovieFragment.ALLMVRV.setLayoutManager(gridLayoutManager);
                int index=(int)Math.floor(movieAdapter.getItemCount()/2.0);
                MovieFragment.Rcf.scrollToPosition(index);
            }
        });
    }

    public void getSeries(){
        Sref.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                ArrayList<SeriesModel> SMArrayList = new ArrayList<>();
                for (DocumentSnapshot ds : queryDocumentSnapshots){
                    SeriesModel temp = ds.toObject(SeriesModel.class);
                    SMArrayList.add(temp);
                    ImageView image = new ImageView(context);
                    Glide.with(context)
                            .load(temp.image)
                            .into(image);
                    SeriesFragment.viewFlipper.addView(image);
                }
                SeriesAdapter seriesAdapter = new SeriesAdapter(SMArrayList,context);
                SeriesFragment.Srv.setAdapter(seriesAdapter);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context,3);
                SeriesFragment.Srv.setLayoutManager(gridLayoutManager);
                SeriesFragment.viewFlipper.startFlipping();
                SeriesFragment.viewFlipper.setFlipInterval(3000);
            }
        });
    }

}
