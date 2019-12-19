package me.abir.dailycricketbd.model.video_module_v2;

import com.google.gson.annotations.SerializedName;


public class DataItem {

    @SerializedName("date")
    private String date;

    @SerializedName("video_category_id")
    private int videoCategoryId;

    @SerializedName("user_id")
    private int userId;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("id")
    private int id;

    @SerializedName("time")
    private String time;

    @SerializedName("title")
    private String title;

    @SerializedName("category")
    private Category category;

    @SerializedName("url")
    private String url;

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setVideoCategoryId(int videoCategoryId) {
        this.videoCategoryId = videoCategoryId;
    }

    public int getVideoCategoryId() {
        return videoCategoryId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return
                "DataItem{" +
                        "date = '" + date + '\'' +
                        ",video_category_id = '" + videoCategoryId + '\'' +
                        ",user_id = '" + userId + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",id = '" + id + '\'' +
                        ",time = '" + time + '\'' +
                        ",title = '" + title + '\'' +
                        ",category = '" + category + '\'' +
                        ",url = '" + url + '\'' +
                        "}";
    }
}