package me.abir.dailycricketbd.model.single_match_scorecard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import me.abir.dailycricketbd.model.results_module.Competition;
import me.abir.dailycricketbd.model.results_module.TeamA;
import me.abir.dailycricketbd.model.results_module.TeamB;
import me.abir.dailycricketbd.model.results_module.Toss;
import me.abir.dailycricketbd.model.results_module.Venue;
import me.abir.dailycricketbd.model.single_live_match.Player;

public class ScoreCardResponse {

    @SerializedName("match_id")
    @Expose
    private int matchId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("subtitle")
    @Expose
    private String subtitle;
    @SerializedName("format")
    @Expose
    private int format;
    @SerializedName("format_str")
    @Expose
    private String formatStr;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("status_str")
    @Expose
    private String statusStr;
    @SerializedName("status_note")
    @Expose
    private String statusNote;
    @SerializedName("game_state")
    @Expose
    private int gameState;
    @SerializedName("game_state_str")
    @Expose
    private String gameStateStr;
    @SerializedName("competition")
    @Expose
    private Competition competition;
    @SerializedName("teama")
    @Expose
    private TeamA teama;
    @SerializedName("teamb")
    @Expose
    private TeamB teamb;
    @SerializedName("date_start")
    @Expose
    private String dateStart;
    @SerializedName("date_end")
    @Expose
    private String dateEnd;
    @SerializedName("timestamp_start")
    @Expose
    private int timestampStart;
    @SerializedName("timestamp_end")
    @Expose
    private int timestampEnd;
    @SerializedName("venue")
    @Expose
    private Venue venue;
    @SerializedName("umpires")
    @Expose
    private String umpires;
    @SerializedName("referee")
    @Expose
    private String referee;
    @SerializedName("equation")
    @Expose
    private String equation;
    @SerializedName("live")
    @Expose
    private String live;
    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("win_margin")
    @Expose
    private String winMargin;
    @SerializedName("winning_team_id")
    @Expose
    private int winningTeamId;
    @SerializedName("commentary")
    @Expose
    private int commentary;
    @SerializedName("wagon")
    @Expose
    private int wagon;
    @SerializedName("latest_inning_number")
    @Expose
    private int latestInningNumber;
    @SerializedName("toss")
    @Expose
    private Toss toss;
    @SerializedName("current_over")
    @Expose
    private String currentOver;
    @SerializedName("previous_over")
    @Expose
    private String previousOver;
    @SerializedName("man_of_the_match")
    @Expose
    private ManOfTheMatch manOfTheMatch;
    @SerializedName("man_of_the_series")
    @Expose
    private String manOfTheSeries;
    @SerializedName("is_followon")
    @Expose
    private int isFollowon;
    @SerializedName("team_batting_first")
    @Expose
    private String teamBattingFirst;
    @SerializedName("team_batting_second")
    @Expose
    private String teamBattingSecond;
    @SerializedName("last_five_overs")
    @Expose
    private String lastFiveOvers;
    @SerializedName("live_inning_number")
    @Expose
    private String liveInningNumber;
    @SerializedName("innings")
    @Expose
    private List<ScoreCardInnings> innings = null;
    @SerializedName("players")
    @Expose
    private List<Player> players = null;

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getFormat() {
        return format;
    }

    public void setFormat(int format) {
        this.format = format;
    }

    public String getFormatStr() {
        return formatStr;
    }

    public void setFormatStr(String formatStr) {
        this.formatStr = formatStr;
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

    public String getStatusNote() {
        return statusNote;
    }

    public void setStatusNote(String statusNote) {
        this.statusNote = statusNote;
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

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public TeamA getTeama() {
        return teama;
    }

    public void setTeama(TeamA teama) {
        this.teama = teama;
    }

    public TeamB getTeamb() {
        return teamb;
    }

    public void setTeamb(TeamB teamb) {
        this.teamb = teamb;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getTimestampStart() {
        return timestampStart;
    }

    public void setTimestampStart(int timestampStart) {
        this.timestampStart = timestampStart;
    }

    public int getTimestampEnd() {
        return timestampEnd;
    }

    public void setTimestampEnd(int timestampEnd) {
        this.timestampEnd = timestampEnd;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public String getUmpires() {
        return umpires;
    }

    public void setUmpires(String umpires) {
        this.umpires = umpires;
    }

    public String getReferee() {
        return referee;
    }

    public void setReferee(String referee) {
        this.referee = referee;
    }

    public String getEquation() {
        return equation;
    }

    public void setEquation(String equation) {
        this.equation = equation;
    }

    public String getLive() {
        return live;
    }

    public void setLive(String live) {
        this.live = live;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getWinMargin() {
        return winMargin;
    }

    public void setWinMargin(String winMargin) {
        this.winMargin = winMargin;
    }

    public int getWinningTeamId() {
        return winningTeamId;
    }

    public void setWinningTeamId(int winningTeamId) {
        this.winningTeamId = winningTeamId;
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

    public int getLatestInningNumber() {
        return latestInningNumber;
    }

    public void setLatestInningNumber(int latestInningNumber) {
        this.latestInningNumber = latestInningNumber;
    }

    public Toss getToss() {
        return toss;
    }

    public void setToss(Toss toss) {
        this.toss = toss;
    }

    public String getCurrentOver() {
        return currentOver;
    }

    public void setCurrentOver(String currentOver) {
        this.currentOver = currentOver;
    }

    public String getPreviousOver() {
        return previousOver;
    }

    public void setPreviousOver(String previousOver) {
        this.previousOver = previousOver;
    }

    public ManOfTheMatch getManOfTheMatch() {
        return manOfTheMatch;
    }

    public void setManOfTheMatch(ManOfTheMatch manOfTheMatch) {
        this.manOfTheMatch = manOfTheMatch;
    }

    public String getManOfTheSeries() {
        return manOfTheSeries;
    }

    public void setManOfTheSeries(String manOfTheSeries) {
        this.manOfTheSeries = manOfTheSeries;
    }

    public int getIsFollowon() {
        return isFollowon;
    }

    public void setIsFollowon(int isFollowon) {
        this.isFollowon = isFollowon;
    }

    public String getTeamBattingFirst() {
        return teamBattingFirst;
    }

    public void setTeamBattingFirst(String teamBattingFirst) {
        this.teamBattingFirst = teamBattingFirst;
    }

    public String getTeamBattingSecond() {
        return teamBattingSecond;
    }

    public void setTeamBattingSecond(String teamBattingSecond) {
        this.teamBattingSecond = teamBattingSecond;
    }

    public String getLastFiveOvers() {
        return lastFiveOvers;
    }

    public void setLastFiveOvers(String lastFiveOvers) {
        this.lastFiveOvers = lastFiveOvers;
    }

    public String getLiveInningNumber() {
        return liveInningNumber;
    }

    public void setLiveInningNumber(String liveInningNumber) {
        this.liveInningNumber = liveInningNumber;
    }

    public List<ScoreCardInnings> getInnings() {
        return innings;
    }

    public void setInnings(List<ScoreCardInnings> innings) {
        this.innings = innings;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

}
