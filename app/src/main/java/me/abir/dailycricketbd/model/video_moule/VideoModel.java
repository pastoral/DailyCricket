package me.abir.dailycricketbd.model.video_moule;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoModel {

    @SerializedName("videos")
    @Expose
    private Videos videos;

    public Videos getVideos() {
        return videos;
    }

    public void setVideos(Videos videos) {
        this.videos = videos;
    }

}
