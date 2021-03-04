package com.example.firstlesson;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final List<String> imageUrl = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, int position) {
        Picasso.get().load(imageUrl.get(position)).into(holder.imageView);
    }

    @Override
    public int getItemCount () {
        return imageUrl.size();
    }


    public void addItemsToAdapter(List <String> imageUrls){
        this.imageUrl.addAll(imageUrls);
    }
}
