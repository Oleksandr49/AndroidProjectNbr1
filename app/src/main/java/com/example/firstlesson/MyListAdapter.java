package com.example.firstlesson;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<ViewHolder> {


    public final List<MyListDataItem> buffer = new ArrayList<>(20);
    private final List<MyListDataItem> myListDataListItem = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, int position) {
        holder.imageView.setImageResource(myListDataListItem.get(position).getImgId());
    }

    @Override
    public int getItemCount () {
        return myListDataListItem.size();
    }

    public void initAdapterItems(){
        loadMoreItemsToBuffer();
        loadMoreItemsFromBuffer();
    }

    public void loadMoreItemsToBuffer(){
        while (buffer.size() < 20){
            buffer.add(new MyListDataItem(R.drawable.germini_flower_pink_petals_608384));
        }
    }

    public void loadMoreItemsFromBuffer(){
        myListDataListItem.addAll(buffer);
        buffer.clear();
        loadMoreItemsToBuffer();
    }
}
