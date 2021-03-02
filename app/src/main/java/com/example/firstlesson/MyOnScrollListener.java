package com.example.firstlesson;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;

public class MyOnScrollListener extends RecyclerView.OnScrollListener {

    private Boolean scrolling = false;
    private Boolean isLoading = false;
    private Boolean isLastPage = false;
    private final MyListAdapter adapter;
    private final RecyclerView.LayoutManager layoutManager;

    public MyOnScrollListener(MyListAdapter adapter, RecyclerView.LayoutManager layoutManager){
        this.adapter = adapter;
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        scrolling = !scrolling;
        if(!scrolling){
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int lastVisibleItemPosition = 0;

        if(layoutManager instanceof LinearLayoutManager){
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
             lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        }
        if(layoutManager instanceof GridLayoutManager){
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
             lastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition();
        }

        if (!isLoading && !isLastPage) {
            if ((visibleItemCount + lastVisibleItemPosition) >= totalItemCount && lastVisibleItemPosition >= 0 && totalItemCount >= PAGE_SIZE) {
                if(adapter.buffer.isEmpty()){
                    adapter.loadMoreItemsToBuffer();
                }
                adapter.loadMoreItemsFromBuffer();
            }
        }
    }
}
