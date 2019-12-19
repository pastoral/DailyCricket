package me.abir.dailycricketbd.model.single_live_match;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SingleLiveMatchResponse {

    @SerializedName("mid")
    @Expose
    private int mid;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("status_str")
    @Expose
    private String statusStr;
    @SerializedName("game_state")
    @Expose
    private int gameState;
    @SerializedName("game_state_str")
    @Expose
    private String gameStateStr;
    @SerializedName("status_note")
    @Expose
    private String statusNote;
    @SerializedName("team_batting")
    @Expose
    private String teamBatting;
    @SerializedName("team_bowling")
    @Expose
    private String teamBowling;
    @SerializedName("live_inning_number")
    @Expose
    private int liveInningNumber;
    @SerializedName("live_score")
    @Expose
    private LiveScore liveScore;
    @SerializedName("batsmen")
    @Expose
    private List<LiveMatchBatsman> batsmen = null;
    @SerializedName("bowlers")
    @Expose
    private List<LiveMatchBowler> bowlers = null;
    @SerializedName("commentary")
    @Expose
    private int commentary;
    @SerializedName("wagon")
    @Expose
    private int wagon;
    @SerializedName("commentaries")
    @Expose
    private List<Commentary> commentaries = null;
    @SerializedName("live_inning")
    @Expose
    private LiveInning liveInning;
    @SerializedName("players")
    @Expose
    private List<Player> players = null;

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public String getGameStateStr() {
        return gameStateStr;
    }

    public void setGameStateStr(String gameStateStr) {
        this.gameStateStr = gameStateStr;
    }

    public String getStatusNote() {
        return statusNote;
    }

    public void setStatusNote(String statusNote) {
        this.statusNote = statusNote;
    }

    public String getTeamBatting() {
        return teamBatting;
    }

    public void setTeamBatting(String teamBatting) {
        this.teamBatting = teamBatting;
    }

    public String getTeamBowling() {
        return teamBowling;
    }

    public void setTeamBowling(String teamBowling) {
        this.teamBowling = teamBowling;
    }

    public int getLiveInningNumber() {
        return liveInningNumber;
    }

    public void setLiveInningNumber(int liveInningNumber) {
        this.liveInningNumber = liveInningNumber;
    }

    public LiveScore getLiveScore() {
        return liveScore;
    }

    public void setLiveScore(LiveScore liveScore) {
        this.liveScore = liveScore;
    }

    public List<LiveMatchBatsman> getBatsmen() {
        return batsmen;
    }

    public void setBatsmen(List<LiveMatchBatsman> batsmen) {
        this.batsmen = batsmen;
    }

    public List<LiveMatchBowler> getBowlers() {
        return bowlers;
    }

    public void setBowlers(List<LiveMatchBowler> bowlers) {
        this.bowlers = bowlers;
    }

    public int getCommentary() {
        return commentary;
    }

    public void setCommentary(int commentary) {
        this.commentary = commentary;
    }

    public int getWagon() {
        return wagon;
    }

    public void setWagon(int wagon) {
        this.wagon = wagon;
    }

    public List<Commentary> getCommentaries() {
        return commentaries;
    }

    public void setCommentaries(List<Commentary> commentaries) {
        this.commentaries = commentaries;
    }

    public LiveInning getLiveInning() {
        return liveInning;
    }

    public void setLiveInning(LiveInning liveInning) {
        this.liveInning = liveInning;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

}
