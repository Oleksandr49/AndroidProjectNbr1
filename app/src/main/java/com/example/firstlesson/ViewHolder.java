package com.example.firstlesson;

import android.view.View;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

class ViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public ConstraintLayout constraintLayout;

    public ViewHolder(View itemView) {
        super(itemView);
        this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
        constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.constraintLayout);
    }
}
