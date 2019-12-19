package me.abir.dailycricketbd.model.results_module;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Abir on 24-Nov-17.
 */

public class ResultsModelWrapper implements Parcelable {
    private String tournamentName;
    private String dateAndMatchNo;
    private String teamA;
    private String teamAScore;
    private String teamAScoreFull;
    private String teamAUrl;
    private String teamB;
    private String teamBScore;
    private String teamBScoreFull;
    private String teamBUrl;
    private String matchStatus;
    private String matchNotes;
    private String matchStartTime;
    private int domestic;
    private String matchId;
    private boolean isSection;

    public ResultsModelWrapper() {

    }

    protected ResultsModelWrapper(Parcel in) {
        this.tournamentName = in.readString();
        this.dateAndMatchNo = in.readString();
        this.teamA = in.readString();
        this.teamAScore = in.readString();
        this.teamBScoreFull = in.readString();
        this.teamAUrl = in.readString();
        this.teamB = in.readString();
        this.teamBScore = in.readString();
        this.teamBScoreFull = in.readString();
        this.teamBUrl = in.readString();
        this.matchStatus = in.readString();
        this.matchNotes = in.readString();
        this.matchStartTime = in.readString();
        this.domestic = in.readInt();
        this.matchId = in.readString();
        this.isSection = in.readByte() != 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(tournamentName);
        parcel.writeString(dateAndMatchNo);
        parcel.writeString(teamA);
        parcel.writeString(teamAScore);
        parcel.writeString(teamAScoreFull);
        parcel.writeString(teamAUrl);
        parcel.writeString(teamB);
        parcel.writeString(teamBScore);
        parcel.writeString(teamBScoreFull);
        parcel.writeString(teamBUrl);
        parcel.writeString(matchStatus);
        parcel.writeString(matchNotes);
        parcel.writeString(matchStartTime);
        parcel.writeInt(domestic);
        parcel.writeString(matchId);
        parcel.writeByte((byte) (isSection ? 1 : 0));
    }

    public static final Creator<ResultsModelWrapper> CREATOR = new Creator<ResultsModelWrapper>() {
        @Override
        public ResultsModelWrapper createFromParcel(Parcel in) {
            return new ResultsModelWrapper(in);
        }

        @Override
        public ResultsModelWrapper[] newArray(int size) {
            return new ResultsModelWrapper[size];
        }
    };

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public String getDateAndMatchNo() {
        return dateAndMatchNo;
    }

    public void setDateAndMatchNo(String dateAndMatchNo) {
        this.dateAndMatchNo = dateAndMatchNo;
    }

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamAScore() {
        return teamAScore;
    }

    public void setTeamAScore(String teamAScore) {
        this.teamAScore = teamAScore;
    }

    public String getTeamAUrl() {
        return teamAUrl;
    }

    public void setTeamAUrl(String teamAUrl) {
        this.teamAUrl = teamAUrl;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public String getTeamBScore() {
        return teamBScore;
    }

    public void setTeamBScore(String teamBScore) {
        this.teamBScore = teamBScore;
    }

    public String getTeamBUrl() {
        return teamBUrl;
    }

    public void setTeamBUrl(String teamBUrl) {
        this.teamBUrl = teamBUrl;
    }

    public String getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }

    public int getDomestic() {
        return domestic;
    }

    public void setDomestic(int domestic) {
        this.domestic = domestic;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }


    public boolean isSection() {
        return isSection;
    }

    public void setSection(boolean section) {
        isSection = section;
    }

    public String getMatchNotes() {
        return matchNotes;
    }

    public void setMatchNotes(String matchNotes) {
        this.matchNotes = matchNotes;
    }

    public String getTeamAScoreFull() {
        return teamAScoreFull;
    }

    public void setTeamAScoreFull(String teamAScoreFull) {
        this.teamAScoreFull = teamAScoreFull;
    }

    public String getTeamBScoreFull() {
        return teamBScoreFull;
    }

    public void setTeamBScoreFull(String teamBScoreFull) {
        this.teamBScoreFull = teamBScoreFull;
    }

    public String getMatchStartTime() {
        return matchStartTime;
    }

    public void setMatchStartTime(String matchStartTime) {
        this.matchStartTime = matchStartTime;
    }
}
