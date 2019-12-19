package me.abir.dailycricketbd.adapter;

import android.content.Intent;
import android.content.res.ColorStateList;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.model.featured_news_v2.Data;
import me.abir.dailycricketbd.model.featured_news_v2.FeaturedArticleModel;
import me.abir.dailycricketbd.model.latest_news_v2.Datum;
import me.abir.dailycricketbd.util.Configuration;
import me.abir.dailycricketbd.view.activity.FullNewsActivity;

/**
 * Created by Abir on 28-Oct-17.
 */

public class NewsAdapter extends RecyclerView.Adapter {

    public static final int FEATURED_ARTICLE = 0;
    public static final int LATEST_ARTICLE = 1;
    private List<Object> newsList = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        if (viewType == FEATURED_ARTICLE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_news_featured_v2
                    , parent, false);
            viewHolder = new FeaturedNewsViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_news_latest_v2
                    , parent, false);
            viewHolder = new LatestNewsViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == FEATURED_ARTICLE) {
            setView((FeaturedNewsViewHolder) holder, position);
        } else {
            setView((LatestNewsViewHolder) holder, position);

        }
    }

    private void setView(FeaturedNewsViewHolder holder, int position) {
        try {
            FeaturedArticleModel featured = (FeaturedArticleModel) newsList.get(position);
            Data featuredData = featured.getData();

            holder.tvFeaturedTitle.setText(featuredData.getTitle());
            holder.btnFeaturedTag.setText(featuredData.getCategory().get(0).getName().toUpperCase());
            holder.tvFeaturedMore.setText(featuredData.getExcerpt());

            Picasso.with(holder.ivPreview.getContext())
                    .load(featuredData.getImage())
                    .fit()
                    .placeholder(R.mipmap.app_icon)
                    .error(R.mipmap.app_icon)
                    .into(holder.ivPreview);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setView(LatestNewsViewHolder holder, int position) {
        try {
            Datum latestNews = (Datum) newsList.get(position);

            holder.tvLatestTitle.setText(latestNews.getTitle());
            String category = latestNews.getCategory().get(0).getName().toUpperCase();
            holder.btnLatestTag.setText(category);

            Picasso.with(holder.ivArticlePreview.getContext())
                    .load(latestNews.getImage())
                    .fit()
                    .placeholder(R.mipmap.app_icon)
                    .error(R.mipmap.app_icon)
                    .into(holder.ivArticlePreview);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return FEATURED_ARTICLE;
        }
        return LATEST_ARTICLE;
    }

    private class FeaturedNewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvFeaturedTitle;
        TextView tvFeaturedMore;
        Button btnFeaturedTag;
        ImageView ivPreview;

        FeaturedNewsViewHolder(View itemView) {
            super(itemView);
            this.tvFeaturedTitle = itemView.findViewById(R.id.tvFeaturedTitle);
            this.btnFeaturedTag = itemView.findViewById(R.id.btnFeaturedTag);
            this.tvFeaturedMore = itemView.findViewById(R.id.tvFeaturedMore);
            this.ivPreview = itemView.findViewById(R.id.ivPreview);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            String slug = ((FeaturedArticleModel) newsList.get(pos)).getData().getSlug();
            Log.d("FeaturedNewsViewHolder", "onClick() " + pos + " slug: " + slug);
            showFullNews(v, slug);
        }
    }

    private class LatestNewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvLatestTitle;
        Button btnLatestTag;
        ImageView ivArticlePreview;

        LatestNewsViewHolder(View itemView) {
            super(itemView);
            this.tvLatestTitle = itemView.findViewById(R.id.tvLatestTitle);
            this.btnLatestTag = itemView.findViewById(R.id.btnLatestTag);
            this.ivArticlePreview = itemView.findViewById(R.id.ivArticlePreview);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            String slug = ((Datum) newsList.get(pos)).getSlug();
            Log.d("LatestNewsViewHolder", "onClick() " + pos);
            showFullNews(v, slug);
        }
    }

    public void setData(List<Object> newsList) {
        //this.newsList.clear();
        this.newsList = newsList;
        notifyDataSetChanged();
    }

    private void showFullNews(View view, String slug) {
        Intent intent = new Intent(view.getContext(), FullNewsActivity.class);
        intent.putExtra(Configuration.NEWS_SLUG_TAG, slug);
        view.getContext().startActivity(intent);
    }

}
