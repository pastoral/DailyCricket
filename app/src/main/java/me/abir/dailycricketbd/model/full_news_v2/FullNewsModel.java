package me.abir.dailycricketbd.model.full_news_v2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FullNewsModel {

    @SerializedName("data")
    @Expose
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
