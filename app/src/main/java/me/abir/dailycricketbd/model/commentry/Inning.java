package me.abir.dailycricketbd.model.commentry;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Inning {

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
    @SerializedName("batsmen")
    @Expose
    private List<Batsman> batsmen = null;
    @SerializedName("bowlers")
    @Expose
    private List<Bowler> bowlers = null;

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

    public List<Batsman> getBatsmen() {
        return batsmen;
    }

    public void setBatsmen(List<Batsman> batsmen) {
        this.batsmen = batsmen;
    }

    public List<Bowler> getBowlers() {
        return bowlers;
    }

    public void setBowlers(List<Bowler> bowlers) {
        this.bowlers = bowlers;
    }

}
