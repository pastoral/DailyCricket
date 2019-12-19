package me.abir.dailycricketbd.model.commentry;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Team {

    @SerializedName("tid")
    @Expose
    private int tid;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("players")
    @Expose
    private List<Object> players = null;
    @SerializedName("abbr")
    @Expose
    private String abbr;
    @SerializedName("thumb_url")
    @Expose
    private String thumbUrl;
    @SerializedName("alt_name")
    @Expose
    private String altName;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("logo_url")
    @Expose
    private String logoUrl;
    @SerializedName("country")
    @Expose
    private String country;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Object> getPlayers() {
        return players;
    }

    public void setPlayers(List<Object> players) {
        this.players = players;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getAltName() {
        return altName;
    }

    public void setAltName(String altName) {
        this.altName = altName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
