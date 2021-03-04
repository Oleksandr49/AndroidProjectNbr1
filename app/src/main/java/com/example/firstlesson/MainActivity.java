package com.example.firstlesson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private MyListAdapter adapter;
    private Boolean isLoading = false;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeRecyclerView();
    }

    private void initializeRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        adapter = new MyListAdapter();
        loadMore();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {

            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);

                    outRect.bottom = 20;
                    outRect.top = 20;
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();

                if (manager == null)
                    return;

                int visibleItemCount = manager.getChildCount();
                int totalItemCount = manager.getItemCount();
                int lastVisibleItemPosition = 0;

                if(manager instanceof LinearLayoutManager){
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) manager;
                    lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                }
                if(manager instanceof GridLayoutManager){
                    GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
                    lastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition();
                }

                if ((visibleItemCount + lastVisibleItemPosition) >= totalItemCount && lastVisibleItemPosition >= 0 && totalItemCount >= 5) {
                        loadMore();

                }
            }
        });
    }

    private void loadMore(){
        if(!isLoading) {
            isLoading = true;
            adapter.addItemsToAdapter(Arrays.asList(getResources().getStringArray(R.array.imageUrls)));
        }
        isLoading = false;
    }
}