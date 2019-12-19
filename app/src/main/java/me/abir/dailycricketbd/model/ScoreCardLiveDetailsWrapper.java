package me.abir.dailycricketbd.model;

import java.util.List;

import me.abir.dailycricketbd.model.single_match_scorecard.ScoreCardWrapperModel;

public class ScoreCardLiveDetailsWrapper {

    private List<ScoreCardWrapperModel> scoreCardWrapperModels;
    private String teamNameA;
    private String teamFlagA;
    private String teamNameScoreA;
    private String teamNameB;
    private String teamFlagB;
    private String teamNameScoreB;
    private String matchStatus;
    private String matchResult;
    private String tossInfo;
    private String manOfTheMatch;
    private String venue;
    private String matchStartTime;

    public List<ScoreCardWrapperModel> getScoreCardWrapperModels() {
        return scoreCardWrapperModels;
    }

    public void setScoreCardWrapperModels(List<ScoreCardWrapperModel> scoreCardWrapperModels) {
        this.scoreCardWrapperModels = scoreCardWrapperModels;
    }

    public String getTeamNameA() {
        return teamNameA;
    }

    public void setTeamNameA(String teamNameA) {
        this.teamNameA = teamNameA;
    }

    public String getTeamFlagA() {
        return teamFlagA;
    }

    public void setTeamFlagA(String teamFlagA) {
        this.teamFlagA = teamFlagA;
    }

    public String getTeamNameScoreA() {
        return teamNameScoreA;
    }

    public void setTeamNameScoreA(String teamNameScoreA) {
        this.teamNameScoreA = teamNameScoreA;
    }

    public String getTeamNameB() {
        return teamNameB;
    }

    public void setTeamNameB(String teamNameB) {
        this.teamNameB = teamNameB;
    }

    public String getTeamFlagB() {
        return teamFlagB;
    }

    public void setTeamFlagB(String teamFlagB) {
        this.teamFlagB = teamFlagB;
    }

    public String getTeamNameScoreB() {
        return teamNameScoreB;
    }

    public void setTeamNameScoreB(String teamNameScoreB) {
        this.teamNameScoreB = teamNameScoreB;
    }

    public String getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }

    public String getMatchResult() {
        return matchResult;
    }

    public void setMatchResult(String matchResult) {
        this.matchResult = matchResult;
    }

    public String getTossInfo() {
        return tossInfo;
    }

    public void setTossInfo(String tossInfo) {
        this.tossInfo = tossInfo;
    }

    public String getManOfTheMatch() {
        return manOfTheMatch;
    }

    public void setManOfTheMatch(String manOfTheMatch) {
        this.manOfTheMatch = manOfTheMatch;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getMatchStartTime() {
        return matchStartTime;
    }

    public void setMatchStartTime(String matchStartTime) {
        this.matchStartTime = matchStartTime;
    }
}
