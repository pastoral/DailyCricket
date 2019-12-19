package me.abir.dailycricketbd.model.poll_v2;

import com.google.gson.annotations.SerializedName;


public class OptionsItem {

    @SerializedName("option_key")
    private int optionKey;

    @SerializedName("option_id")
    private int optionId;

    @SerializedName("vote")
    private int voteCount;

    @SerializedName("option")
    private String option;

    //Extra transient field to detect if app user has selected this option or not
    private Boolean userChecked = false;

    public void setOptionKey(int optionKey) {
        this.optionKey = optionKey;
    }

    public int getOptionKey() {
        return optionKey;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }

    public Boolean getUserChecked() {
        return userChecked;
    }

    public void setUserChecked(Boolean userChecked) {
        this.userChecked = userChecked;
    }

    @Override
    public String toString() {
        return
                "OptionsItem{" +
                        "option_key = '" + optionKey + '\'' +
                        ",option = '" + option + '\'' +
                        ",option_id = '" + optionId + '\'' +
                        ",vote = '" + voteCount + '\'' +
                        "}";
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
}