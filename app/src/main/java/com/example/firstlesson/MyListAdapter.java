package com.example.firstlesson;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {

    private final List<MyListData> myListDataList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, int position) {
        holder.imageView.setImageResource(myListDataList.get(position).getImgId());
    }

    @Override
    public int getItemCount () {
        return myListDataList.size();
    }

    public void addITemToList(MyListData item){
        myListDataList.add(item);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public ConstraintLayout constraintLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            constraintLayout = (ConstraintLayout)itemView.findViewById(R.id.constraintLayout);
        }
    }
}
