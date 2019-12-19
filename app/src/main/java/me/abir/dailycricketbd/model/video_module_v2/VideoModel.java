package me.abir.dailycricketbd.model.video_module_v2;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoModel {

    @SerializedName("data")
    private List<DataItem> data;

    @SerializedName("meta")
    private Meta meta;

    @SerializedName("links")
    private Links links;

    public void setData(List<DataItem> data) {
        this.data = data;
    }

    public List<DataItem> getData() {
        return data;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Links getLinks() {
        return links;
    }

    @Override
    public String toString() {
        return
                "VideoResponseModel{" +
                        "data = '" + data + '\'' +
                        ",meta = '" + meta + '\'' +
                        ",links = '" + links + '\'' +
                        "}";
    }
}