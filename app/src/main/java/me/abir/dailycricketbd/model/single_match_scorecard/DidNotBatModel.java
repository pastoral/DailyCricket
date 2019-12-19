package me.abir.dailycricketbd.model.single_match_scorecard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DidNotBatModel {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("player_id")
    @Expose
    private String playerId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
}
