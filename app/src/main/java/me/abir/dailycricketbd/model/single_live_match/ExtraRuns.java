package me.abir.dailycricketbd.model.single_live_match;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExtraRuns {

    @SerializedName("byes")
    @Expose
    private int byes;
    @SerializedName("legbyes")
    @Expose
    private int legbyes;
    @SerializedName("wides")
    @Expose
    private int wides;
    @SerializedName("noballs")
    @Expose
    private int noballs;
    @SerializedName("penalty")
    @Expose
    private String penalty;
    @SerializedName("total")
    @Expose
    private int total;

    public int getByes() {
        return byes;
    }

    public void setByes(int byes) {
        this.byes = byes;
    }

    public int getLegbyes() {
        return legbyes;
    }

    public void setLegbyes(int legbyes) {
        this.legbyes = legbyes;
    }

    public int getWides() {
        return wides;
    }

    public void setWides(int wides) {
        this.wides = wides;
    }

    public int getNoballs() {
        return noballs;
    }

    public void setNoballs(int noballs) {
        this.noballs = noballs;
    }

    public String getPenalty() {
        return penalty;
    }

    public void setPenalty(String penalty) {
        this.penalty = penalty;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
