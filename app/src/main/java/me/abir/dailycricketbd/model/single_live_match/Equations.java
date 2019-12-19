package me.abir.dailycricketbd.model.single_live_match;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Equations {

    @SerializedName("runs")
    @Expose
    private int runs;
    @SerializedName("wickets")
    @Expose
    private int wickets;
    @SerializedName("overs")
    @Expose
    private String overs;
    @SerializedName("bowlers_used")
    @Expose
    private int bowlersUsed;
    @SerializedName("runrate")
    @Expose
    private String runrate;

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public String getOvers() {
        return overs;
    }

    public void setOvers(String overs) {
        this.overs = overs;
    }

    public int getBowlersUsed() {
        return bowlersUsed;
    }

    public void setBowlersUsed(int bowlersUsed) {
        this.bowlersUsed = bowlersUsed;
    }

    public String getRunrate() {
        return runrate;
    }

    public void setRunrate(String runrate) {
        this.runrate = runrate;
    }

}
