package me.abir.dailycricketbd.interfaces;

import me.abir.dailycricketbd.model.featured_news.FeaturedNews;
import me.abir.dailycricketbd.model.featured_news_v2.FeaturedArticleModel;
import me.abir.dailycricketbd.model.latest_news.LatestNews;
import me.abir.dailycricketbd.model.latest_news_v2.LatestArticleModel;

/**
 * Created by Abir on 28-Oct-17.
 */

public interface OnNewsResponseListener {
    void onLatestNewsResponse(LatestArticleModel latestNews);
    void onFeaturedNewsResponse(FeaturedArticleModel featuredNews);
}
