package me.abir.dailycricketbd.model.single_live_match;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LastWicket {

    @SerializedName("batsman_id")
    @Expose
    private String batsmanId;
    @SerializedName("runs")
    @Expose
    private String runs;
    @SerializedName("balls")
    @Expose
    private String balls;
    @SerializedName("how_out")
    @Expose
    private String howOut;
    @SerializedName("score_at_dismissal")
    @Expose
    private int scoreAtDismissal;
    @SerializedName("overs_at_dismissal")
    @Expose
    private String oversAtDismissal;
    @SerializedName("bowler_id")
    @Expose
    private String bowlerId;
    @SerializedName("dismissal")
    @Expose
    private String dismissal;
    @SerializedName("number")
    @Expose
    private int number;

    public String getBatsmanId() {
        return batsmanId;
    }

    public void setBatsmanId(String batsmanId) {
        this.batsmanId = batsmanId;
    }

    public String getRuns() {
        return runs;
    }

    public void setRuns(String runs) {
        this.runs = runs;
    }

    public String getBalls() {
        return balls;
    }

    public void setBalls(String balls) {
        this.balls = balls;
    }

    public String getHowOut() {
        return howOut;
    }

    public void setHowOut(String howOut) {
        this.howOut = howOut;
    }

    public int getScoreAtDismissal() {
        return scoreAtDismissal;
    }

    public void setScoreAtDismissal(int scoreAtDismissal) {
        this.scoreAtDismissal = scoreAtDismissal;
    }

    public String getOversAtDismissal() {
        return oversAtDismissal;
    }

    public void setOversAtDismissal(String oversAtDismissal) {
        this.oversAtDismissal = oversAtDismissal;
    }

    public String getBowlerId() {
        return bowlerId;
    }

    public void setBowlerId(String bowlerId) {
        this.bowlerId = bowlerId;
    }

    public String getDismissal() {
        return dismissal;
    }

    public void setDismissal(String dismissal) {
        this.dismissal = dismissal;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
