package com.example.infodotav1.Adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.MyViewHodler> {


    @NonNull
    @Override
    public RecyclerviewAdapter.MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewAdapter.MyViewHodler holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHodler extends RecyclerView.ViewHolder {
        public MyViewHodler(@NonNull View itemView) {
            super(itemView);
        }
    }
}
