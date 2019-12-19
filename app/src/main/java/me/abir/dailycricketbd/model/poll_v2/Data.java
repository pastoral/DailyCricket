package me.abir.dailycricketbd.model.poll_v2;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("question")
    private String question;

    @SerializedName("user_id")
    private int userId;

    @SerializedName("show")
    private boolean show;

    @SerializedName("options")
    private List<OptionsItem> options;

    @SerializedName("id")
    private int id;

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public boolean isShow() {
        return show;
    }

    public void setOptions(List<OptionsItem> options) {
        this.options = options;
    }

    public List<OptionsItem> getOptions() {
        return options;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return
                "Data{" +
                        "question = '" + question + '\'' +
                        ",user_id = '" + userId + '\'' +
                        ",show = '" + show + '\'' +
                        ",options = '" + options + '\'' +
                        ",id = '" + id + '\'' +
                        "}";
    }
}