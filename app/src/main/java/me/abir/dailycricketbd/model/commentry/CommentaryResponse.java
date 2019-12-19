package me.abir.dailycricketbd.model.commentry;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import me.abir.dailycricketbd.model.single_live_match.Commentary;
import me.abir.dailycricketbd.model.single_live_match.Player;

public class CommentaryResponse {

    @SerializedName("match")
    @Expose
    private Match match;
    @SerializedName("inning")
    @Expose
    private Inning inning;
    @SerializedName("commentaries")
    @Expose
    private List<Commentary> commentaries = null;
    @SerializedName("teams")
    @Expose
    private List<Team> teams = null;
    @SerializedName("players")
    @Expose
    private List<Player> players = null;

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Inning getInning() {
        return inning;
    }

    public void setInning(Inning inning) {
        this.inning = inning;
    }

    public List<Commentary> getCommentaries() {
        return commentaries;
    }

    public void setCommentaries(List<Commentary> commentaries) {
        this.commentaries = commentaries;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

}
