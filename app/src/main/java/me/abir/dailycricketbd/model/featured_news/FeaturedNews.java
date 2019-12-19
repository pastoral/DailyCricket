package me.abir.dailycricketbd.model.featured_news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeaturedNews {

    @SerializedName("featured")
    @Expose
    private Featured featured;

    public Featured getFeatured() {
        return featured;
    }

    public void setFeatured(Featured featured) {
        this.featured = featured;
    }

}
