package me.abir.dailycricketbd.model.single_live_match;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Commentary {

    @SerializedName("event")
    @Expose
    private String event;
    @SerializedName("batsman_id")
    @Expose
    private int batsmanId;
    @SerializedName("bowler_id")
    @Expose
    private int bowlerId;
    @SerializedName("over")
    @Expose
    private String over;
    @SerializedName("ball")
    @Expose
    private String ball;
    @SerializedName("score")
    @Expose
    private String score;
    @SerializedName("commentary")
    @Expose
    private String commentary;
    @SerializedName("runs")
    @Expose
    private int runs;
    @SerializedName("bats")
    @Expose
    private List<Bat> bats = null;
    @SerializedName("bowls")
    @Expose
    private List<Bowl> bowls = null;
    @SerializedName("how_out")
    @Expose
    private String howOut;
    @SerializedName("wicket_batsman_id")
    @Expose
    private String wicketBatsmanId;
    @SerializedName("batsman_runs")
    @Expose
    private String batsmanRuns;
    @SerializedName("batsman_balls")
    @Expose
    private String batsmanBalls;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public int getBatsmanId() {
        return batsmanId;
    }

    public void setBatsmanId(int batsmanId) {
        this.batsmanId = batsmanId;
    }

    public int getBowlerId() {
        return bowlerId;
    }

    public void setBowlerId(int bowlerId) {
        this.bowlerId = bowlerId;
    }

    public String getOver() {
        return over;
    }

    public void setOver(String over) {
        this.over = over;
    }

    public String getBall() {
        return ball;
    }

    public void setBall(String ball) {
        this.ball = ball;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public List<Bat> getBats() {
        return bats;
    }

    public void setBats(List<Bat> bats) {
        this.bats = bats;
    }

    public List<Bowl> getBowls() {
        return bowls;
    }

    public void setBowls(List<Bowl> bowls) {
        this.bowls = bowls;
    }

    public String getHowOut() {
        return howOut;
    }

    public void setHowOut(String howOut) {
        this.howOut = howOut;
    }

    public String getWicketBatsmanId() {
        return wicketBatsmanId;
    }

    public void setWicketBatsmanId(String wicketBatsmanId) {
        this.wicketBatsmanId = wicketBatsmanId;
    }

    public String getBatsmanRuns() {
        return batsmanRuns;
    }

    public void setBatsmanRuns(String batsmanRuns) {
        this.batsmanRuns = batsmanRuns;
    }

    public String getBatsmanBalls() {
        return batsmanBalls;
    }

    public void setBatsmanBalls(String batsmanBalls) {
        this.batsmanBalls = batsmanBalls;
    }

}
