package me.abir.dailycricketbd.model.poll;

/**
 * Created by mayes on 11/8/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PollModel {

    @SerializedName("poll")
    @Expose
    private Poll poll;

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

}