package com.example.firstlesson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;


public class MainActivity extends AppCompatActivity {

    private MyListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Boolean isLoading = false;
    private Boolean isScrolling = false;
    private int itemsAdded = 0;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeRecyclerView();
    }

    private void initializeRecyclerView(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        adapter = new MyListAdapter();
        loadMore();
        isLoading =false;
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                isScrolling = !isScrolling;
                if(!isScrolling & itemsAdded > 0){
                    adapter.notifyItemRangeInserted(adapter.getItemCount()-1, itemsAdded);
                    itemsAdded = 0;
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

                if(!isLoading){
                    if ((visibleItemCount + lastVisibleItemPosition) >= totalItemCount && lastVisibleItemPosition >= 0 && totalItemCount >= PAGE_SIZE) {
                            loadMore();
                            isLoading=false;
                    }
                }
            }
        });
    }

    private void loadMore(){
        isLoading = true;
        ArrayList<String> urls = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.imageURLS)));
        for(String item : urls){
            adapter.addItemToAdapter(item);
            itemsAdded++;
        }
    }
}