package me.abir.dailycricketbd.model.fcm_models;

import com.google.gson.annotations.SerializedName;

public class RemotePushModel {

    @SerializedName("id")
    private String id; // Common Field

    @SerializedName("page")
    private String page; // Common Field

    @SerializedName("title")
    private String title; // Common Field

    @SerializedName("message")
    private String message; // Common Field

    @SerializedName("slug")
    private String slug; // News Format Field

    @SerializedName("match_id")
    private String matchId; // Innings Break/ Toss/ Match Completed Format Field

    @SerializedName("match_status")
    private String matchStatus; // Innings Break/ Toss/ Match Completed Format Field

    @SerializedName("link")
    private String link; // Custom[web] Format Field

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSlug() {
        return slug;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "RemotePushModel{" +
                "id=" + id +
                ", page='" + page + '\'' +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", slug='" + slug + '\'' +
                ", matchId='" + matchId + '\'' +
                ", matchStatus='" + matchStatus + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}