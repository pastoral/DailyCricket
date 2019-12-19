package me.abir.dailycricketbd.model.single_live_match;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LiveMatchBatsman_ {

    @SerializedName("batsman_id")
    @Expose
    private int batsmanId;
    @SerializedName("runs")
    @Expose
    private int runs;
    @SerializedName("balls")
    @Expose
    private int balls;

    public int getBatsmanId() {
        return batsmanId;
    }

    public void setBatsmanId(int batsmanId) {
        this.batsmanId = batsmanId;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getBalls() {
        return balls;
    }

    public void setBalls(int balls) {
        this.balls = balls;
    }

    @Override
    public String toString() {
        return "LiveMatchBatsman_{" +
                "batsmanId=" + batsmanId +
                ", runs=" + runs +
                ", balls=" + balls +
                '}';
    }
}
