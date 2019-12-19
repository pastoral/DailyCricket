package me.abir.dailycricketbd.model.single_live_match;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LiveInning {

    @SerializedName("iid")
    @Expose
    private int iid;
    @SerializedName("number")
    @Expose
    private int number;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("short_name")
    @Expose
    private String shortName;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("result")
    @Expose
    private int result;
    @SerializedName("batting_team_id")
    @Expose
    private int battingTeamId;
    @SerializedName("fielding_team_id")
    @Expose
    private int fieldingTeamId;
    @SerializedName("scores")
    @Expose
    private String scores;
    @SerializedName("scores_full")
    @Expose
    private String scoresFull;
    @SerializedName("last_wicket")
    @Expose
    private transient LastWicket lastWicket;
    @SerializedName("extra_runs")
    @Expose
    private ExtraRuns extraRuns;
    @SerializedName("equations")
    @Expose
    private Equations equations;
    @SerializedName("current_partnership")
    @Expose
    private CurrentPartnership currentPartnership;
    @SerializedName("recent_scores")
    @Expose
    private String recentScores;
    @SerializedName("last_five_overs")
    @Expose
    private String lastFiveOvers;
    @SerializedName("last_ten_overs")
    @Expose
    private String lastTenOvers;

    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getBattingTeamId() {
        return battingTeamId;
    }

    public void setBattingTeamId(int battingTeamId) {
        this.battingTeamId = battingTeamId;
    }

    public int getFieldingTeamId() {
        return fieldingTeamId;
    }

    public void setFieldingTeamId(int fieldingTeamId) {
        this.fieldingTeamId = fieldingTeamId;
    }

    public String getScores() {
        return scores;
    }

    public void setScores(String scores) {
        this.scores = scores;
    }

    public String getScoresFull() {
        return scoresFull;
    }

    public void setScoresFull(String scoresFull) {
        this.scoresFull = scoresFull;
    }

    public LastWicket getLastWicket() {
        return lastWicket;
    }

    public void setLastWicket(LastWicket lastWicket) {
        this.lastWicket = lastWicket;
    }

    public ExtraRuns getExtraRuns() {
        return extraRuns;
    }

    public void setExtraRuns(ExtraRuns extraRuns) {
        this.extraRuns = extraRuns;
    }

    public Equations getEquations() {
        return equations;
    }

    public void setEquations(Equations equations) {
        this.equations = equations;
    }

    public CurrentPartnership getCurrentPartnership() {
        return currentPartnership;
    }

    public void setCurrentPartnership(CurrentPartnership currentPartnership) {
        this.currentPartnership = currentPartnership;
    }

    public String getRecentScores() {
        return recentScores;
    }

    public void setRecentScores(String recentScores) {
        this.recentScores = recentScores;
    }

    public String getLastFiveOvers() {
        return lastFiveOvers;
    }

    public void setLastFiveOvers(String lastFiveOvers) {
        this.lastFiveOvers = lastFiveOvers;
    }

    public String getLastTenOvers() {
        return lastTenOvers;
    }

    public void setLastTenOvers(String lastTenOvers) {
        this.lastTenOvers = lastTenOvers;
    }

}
