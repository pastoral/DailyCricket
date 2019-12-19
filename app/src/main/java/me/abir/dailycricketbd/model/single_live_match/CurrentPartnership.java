package me.abir.dailycricketbd.model.single_live_match;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentPartnership {

    @SerializedName("runs")
    @Expose
    private int runs;
    @SerializedName("balls")
    @Expose
    private int balls;
    @SerializedName("overs")
    @Expose
    private double overs;
    @SerializedName("batsmen")
    @Expose
    private List<LiveMatchBatsman_> batsmen = null;

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

    public double getOvers() {
        return overs;
    }

    public void setOvers(double overs) {
        this.overs = overs;
    }

    public List<LiveMatchBatsman_> getBatsmen() {
        return batsmen;
    }

    public void setBatsmen(List<LiveMatchBatsman_> batsmen) {
        this.batsmen = batsmen;
    }

    @Override
    public String toString() {
        return "CurrentPartnership{" +
                "runs=" + runs +
                ", balls=" + balls +
                ", overs=" + overs +
                ", batsmen=" + batsmen +
                '}';
    }
}
