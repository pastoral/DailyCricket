package me.abir.dailycricketbd.model.single_live_match;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bowl {

    @SerializedName("runs_conceded")
    @Expose
    private int runsConceded;
    @SerializedName("overs")
    @Expose
    private int overs;
    @SerializedName("wickets")
    @Expose
    private int wickets;
    @SerializedName("maidens")
    @Expose
    private int maidens;
    @SerializedName("bowler_id")
    @Expose
    private int bowlerId;

    public int getRunsConceded() {
        return runsConceded;
    }

    public void setRunsConceded(int runsConceded) {
        this.runsConceded = runsConceded;
    }

    public int getOvers() {
        return overs;
    }

    public void setOvers(int overs) {
        this.overs = overs;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public int getMaidens() {
        return maidens;
    }

    public void setMaidens(int maidens) {
        this.maidens = maidens;
    }

    public int getBowlerId() {
        return bowlerId;
    }

    public void setBowlerId(int bowlerId) {
        this.bowlerId = bowlerId;
    }

}
