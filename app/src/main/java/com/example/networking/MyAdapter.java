package com.example.networking;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<Mountain> mountainList;
    public MyAdapter(List<Mountain> mountainList) {
        this.mountainList = mountainList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mountain, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(mountainList.get(position).getName());
        holder.location.setText(mountainList.get(position).getLocation());
        holder.size.setText(String.valueOf(mountainList.get(position).getSize()));
    }

    @Override
    public int getItemCount() {
        return mountainList.size();
    }
}
