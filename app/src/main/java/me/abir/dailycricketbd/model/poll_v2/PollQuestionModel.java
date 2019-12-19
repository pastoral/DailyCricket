package me.abir.dailycricketbd.model.poll_v2;

import com.google.gson.annotations.SerializedName;

public class PollQuestionModel {

    @SerializedName("data")
    private Data data;

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    @Override
    public String toString() {
        return
                "PollQuestionModel{" +
                        "data = '" + data + '\'' +
                        "}";
    }
}