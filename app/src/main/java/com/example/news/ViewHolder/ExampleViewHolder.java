package com.example.news.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.news.Interface.ItemClickListener;
import com.example.news.R;

public class ExampleViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

    public TextView newsTitle;
    public TextView shortNews;
    public ImageView imageView;
    ItemClickListener itemClickListener;

    public ExampleViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
        super(itemView);

        itemView.setOnClickListener(this);
        newsTitle = itemView.findViewById(R.id.news_title);
        shortNews = itemView.findViewById(R.id.short_news);
        imageView = itemView.findViewById(R.id.image);

        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onItemClick(view, getAdapterPosition());
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
