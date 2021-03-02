package com.example.firstlesson;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        Picasso.get().load(myListDataListItem.get(position).getImgId()).into(holder.imageView);
    }

    @Override
    public int getItemCount () {
        return myListDataListItem.size();
    }

    public void loadMoreItemsFromBuffer(){
        if(buffer.isEmpty()){
            loadMoreItemsToBuffer();
        }
        myListDataListItem.addAll(buffer);
        buffer.clear();
        loadMoreItemsToBuffer();
    }

    private void loadMoreItemsToBuffer(){
        String[] imagesUrls = {
                "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",
                "https://homepages.cae.wisc.edu/~ece533/images/arctichare.png",
                "https://homepages.cae.wisc.edu/~ece533/images/baboon.png",
                "https://homepages.cae.wisc.edu/~ece533/images/boat.png",
                "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",
                "https://homepages.cae.wisc.edu/~ece533/images/peppers.png",
                "https://homepages.cae.wisc.edu/~ece533/images/pool.png",
                "https://homepages.cae.wisc.edu/~ece533/images/tulips.png",
                "https://homepages.cae.wisc.edu/~ece533/images/watch.png",
                "https://homepages.cae.wisc.edu/~ece533/images/zelda.png"
        };
        Random random = new Random();
        while (buffer.size() < 20){
            int urlNumber = random.nextInt(10);
            buffer.add(new MyListDataItem(imagesUrls[urlNumber]));
        }
    }
}
