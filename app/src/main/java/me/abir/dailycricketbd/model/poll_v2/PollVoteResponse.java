package me.abir.dailycricketbd.model.poll_v2;

import com.google.gson.annotations.SerializedName;


public class PollVoteResponse {

    @SerializedName("message")
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return
                "PollVoteResponse{" +
                        "message = '" + message + '\'' +
                        "}";
    }
}