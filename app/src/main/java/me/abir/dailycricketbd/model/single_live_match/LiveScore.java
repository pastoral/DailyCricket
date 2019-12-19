package me.abir.dailycricketbd.model.single_live_match;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LiveScore {

    @SerializedName("runs")
    @Expose
    private int runs;
    @SerializedName("overs")
    @Expose
    private double overs;
    @SerializedName("wickets")
    @Expose
    private int wickets;
    @SerializedName("target")
    @Expose
    private int target;
    @SerializedName("runrate")
    @Expose
    private double runrate;
    @SerializedName("required_runrate")
    @Expose
    private Object requiredRunrate;

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public double getOvers() {
        return overs;
    }

    public void setOvers(double overs) {
        this.overs = overs;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public double getRunrate() {
        return runrate;
    }

    public void setRunrate(double runrate) {
        this.runrate = runrate;
    }

    public Object getRequiredRunrate() {
        return requiredRunrate;
    }

    public void setRequiredRunrate(Object requiredRunrate) {
        this.requiredRunrate = requiredRunrate;
    }

}
