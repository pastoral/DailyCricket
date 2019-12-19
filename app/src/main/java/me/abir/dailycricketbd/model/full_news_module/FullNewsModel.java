package me.abir.dailycricketbd.model.full_news_module;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FullNewsModel {

    @SerializedName("data")
    @Expose
    private FullNewsData data;

    public FullNewsData getData() {
        return data;
    }

    public void setData(FullNewsData data) {
        this.data = data;
    }

}
