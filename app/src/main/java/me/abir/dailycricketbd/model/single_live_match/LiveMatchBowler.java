package me.abir.dailycricketbd.model.single_live_match;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LiveMatchBowler {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("bowler_id")
    @Expose
    private String bowlerId;
    @SerializedName("overs")
    @Expose
    private String overs;
    @SerializedName("runs_conceded")
    @Expose
    private int runsConceded;
    @SerializedName("wickets")
    @Expose
    private int wickets;
    @SerializedName("maidens")
    @Expose
    private int maidens;
    @SerializedName("econ")
    @Expose
    private String econ;

    public String getBowlerId() {
        return bowlerId;
    }

    public void setBowlerId(String bowlerId) {
        this.bowlerId = bowlerId;
    }

    public String getOvers() {
        return overs;
    }

    public void setOvers(String overs) {
        this.overs = overs;
    }

    public int getRunsConceded() {
        return runsConceded;
    }

    public void setRunsConceded(int runsConceded) {
        this.runsConceded = runsConceded;
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

    public String getEcon() {
        return econ;
    }

    public void setEcon(String econ) {
        this.econ = econ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
