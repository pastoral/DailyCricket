package me.abir.dailycricketbd.model.results_module;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Toss {

    @SerializedName("winner")
    @Expose
    private Integer winner;
    @SerializedName("decision")
    @Expose
    private Integer decision;
    @SerializedName("text")
    @Expose
    private String text;

    public Integer getWinner() {
        return winner;
    }

    public void setWinner(Integer winner) {
        this.winner = winner;
    }

    public Integer getDecision() {
        return decision;
    }

    public void setDecision(Integer decision) {
        this.decision = decision;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
