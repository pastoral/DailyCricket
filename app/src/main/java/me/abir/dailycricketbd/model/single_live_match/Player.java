package me.abir.dailycricketbd.model.single_live_match;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Player {

    @SerializedName("pid")
    @Expose
    private int pid;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("short_name")
    @Expose
    private String shortName;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("middle_name")
    @Expose
    private String middleName;
    @SerializedName("birthdate")
    @Expose
    private String birthdate;
    @SerializedName("birthplace")
    @Expose
    private String birthplace;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("primary_team")
    @Expose
    private List<Object> primaryTeam = null;
    @SerializedName("thumb_url")
    @Expose
    private String thumbUrl;
    @SerializedName("logo_url")
    @Expose
    private String logoUrl;
    @SerializedName("playing_role")
    @Expose
    private String playingRole;
    @SerializedName("batting_style")
    @Expose
    private String battingStyle;
    @SerializedName("bowling_style")
    @Expose
    private String bowlingStyle;
    @SerializedName("fielding_position")
    @Expose
    private String fieldingPosition;
    @SerializedName("recent_match")
    @Expose
    private int recentMatch;
    @SerializedName("recent_appearance")
    @Expose
    private int recentAppearance;
    @SerializedName("role")
    @Expose
    private String role;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Object> getPrimaryTeam() {
        return primaryTeam;
    }

    public void setPrimaryTeam(List<Object> primaryTeam) {
        this.primaryTeam = primaryTeam;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getPlayingRole() {
        return playingRole;
    }

    public void setPlayingRole(String playingRole) {
        this.playingRole = playingRole;
    }

    public String getBattingStyle() {
        return battingStyle;
    }

    public void setBattingStyle(String battingStyle) {
        this.battingStyle = battingStyle;
    }

    public String getBowlingStyle() {
        return bowlingStyle;
    }

    public void setBowlingStyle(String bowlingStyle) {
        this.bowlingStyle = bowlingStyle;
    }

    public String getFieldingPosition() {
        return fieldingPosition;
    }

    public void setFieldingPosition(String fieldingPosition) {
        this.fieldingPosition = fieldingPosition;
    }

    public int getRecentMatch() {
        return recentMatch;
    }

    public void setRecentMatch(int recentMatch) {
        this.recentMatch = recentMatch;
    }

    public int getRecentAppearance() {
        return recentAppearance;
    }

    public void setRecentAppearance(int recentAppearance) {
        this.recentAppearance = recentAppearance;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
