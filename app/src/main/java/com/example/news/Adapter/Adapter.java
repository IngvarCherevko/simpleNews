package com.example.news.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.news.ViewHolder.ExampleViewHolder;
import com.example.news.Interface.ItemClickListener;
import com.example.news.Model.Article;
import com.example.news.NewsDetaile;
import com.example.news.R;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<ExampleViewHolder>{

    private List<Article> articles;
    private Context context;
    private ItemClickListener itemClickListener;

    public Adapter(List<Article> articles, Context context) {
        this.articles = articles;
        this.context = context;
    }


    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.source_layout, viewGroup, false);
        return new ExampleViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder exampleViewHolder, int i) {
        final ExampleViewHolder holder = exampleViewHolder;
        final Article model = articles.get(i);

        RequestOptions requestOptions = new RequestOptions();

        Glide.with(context)
                .load(model.getUrlToImage())
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.imageView);

        holder.newsTitle.setText(model.getTitle());
        holder.shortNews.setText(model.getDescription());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(context, NewsDetaile.class);
                intent.putExtra("title", holder.newsTitle.getText());
                intent.putExtra("image", model.getUrlToImage());
                intent.putExtra("url", model.getUrl());
                context.startActivities(new Intent[]{intent});
            }
        });

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
