package com.example.networking;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView location;
    TextView size;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.item_name);
        location = itemView.findViewById(R.id.item_location);
        size = itemView.findViewById(R.id.item_size);

    }
}
