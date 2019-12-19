package me.abir.dailycricketbd.model.single_live_match;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bat {

    @SerializedName("runs")
    @Expose
    private int runs;
    @SerializedName("balls_faced")
    @Expose
    private int ballsFaced;
    @SerializedName("fours")
    @Expose
    private int fours;
    @SerializedName("sixes")
    @Expose
    private int sixes;
    @SerializedName("batsman_id")
    @Expose
    private int batsmanId;

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getBallsFaced() {
        return ballsFaced;
    }

    public void setBallsFaced(int ballsFaced) {
        this.ballsFaced = ballsFaced;
    }

    public int getFours() {
        return fours;
    }

    public void setFours(int fours) {
        this.fours = fours;
    }

    public int getSixes() {
        return sixes;
    }

    public void setSixes(int sixes) {
        this.sixes = sixes;
    }

    public int getBatsmanId() {
        return batsmanId;
    }

    public void setBatsmanId(int batsmanId) {
        this.batsmanId = batsmanId;
    }

}
