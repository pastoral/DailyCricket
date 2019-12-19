package me.abir.dailycricketbd.model.poll;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Option {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("poll_question_id")
    @Expose
    private Integer pollQuestionId;
    @SerializedName("option")
    @Expose
    private String option;
    @SerializedName("vote")
    @Expose
    private Object vote;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    //Extra transient field to detect if app user has selected this option or not
    private Boolean userChecked = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPollQuestionId() {
        return pollQuestionId;
    }

    public void setPollQuestionId(Integer pollQuestionId) {
        this.pollQuestionId = pollQuestionId;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Object getVote() {
        return vote;
    }

    public void setVote(Object vote) {
        this.vote = vote;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getUserChecked() {
        return userChecked;
    }

    public void setUserChecked(Boolean userChecked) {
        this.userChecked = userChecked;
    }
}