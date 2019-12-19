package me.abir.dailycricketbd.model.commentry;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Batsman {

    @SerializedName("batsman_id")
    @Expose
    private String batsmanId;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("role_str")
    @Expose
    private String roleStr;
    @SerializedName("runs")
    @Expose
    private String runs;
    @SerializedName("balls_faced")
    @Expose
    private String ballsFaced;
    @SerializedName("fours")
    @Expose
    private String fours;
    @SerializedName("sixes")
    @Expose
    private String sixes;
    @SerializedName("how_out")
    @Expose
    private String howOut;
    @SerializedName("dismissal")
    @Expose
    private String dismissal;
    @SerializedName("strike_rate")
    @Expose
    private String strikeRate;
    @SerializedName("bowler_id")
    @Expose
    private String bowlerId;
    @SerializedName("first_fielder_id")
    @Expose
    private String firstFielderId;
    @SerializedName("second_fielder_id")
    @Expose
    private String secondFielderId;
    @SerializedName("third_fielder_id")
    @Expose
    private String thirdFielderId;

    public String getBatsmanId() {
        return batsmanId;
    }

    public void setBatsmanId(String batsmanId) {
        this.batsmanId = batsmanId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRoleStr() {
        return roleStr;
    }

    public void setRoleStr(String roleStr) {
        this.roleStr = roleStr;
    }

    public String getRuns() {
        return runs;
    }

    public void setRuns(String runs) {
        this.runs = runs;
    }

    public String getBallsFaced() {
        return ballsFaced;
    }

    public void setBallsFaced(String ballsFaced) {
        this.ballsFaced = ballsFaced;
    }

    public String getFours() {
        return fours;
    }

    public void setFours(String fours) {
        this.fours = fours;
    }

    public String getSixes() {
        return sixes;
    }

    public void setSixes(String sixes) {
        this.sixes = sixes;
    }

    public String getHowOut() {
        return howOut;
    }

    public void setHowOut(String howOut) {
        this.howOut = howOut;
    }

    public String getDismissal() {
        return dismissal;
    }

    public void setDismissal(String dismissal) {
        this.dismissal = dismissal;
    }

    public String getStrikeRate() {
        return strikeRate;
    }

    public void setStrikeRate(String strikeRate) {
        this.strikeRate = strikeRate;
    }

    public String getBowlerId() {
        return bowlerId;
    }

    public void setBowlerId(String bowlerId) {
        this.bowlerId = bowlerId;
    }

    public String getFirstFielderId() {
        return firstFielderId;
    }

    public void setFirstFielderId(String firstFielderId) {
        this.firstFielderId = firstFielderId;
    }

    public String getSecondFielderId() {
        return secondFielderId;
    }

    public void setSecondFielderId(String secondFielderId) {
        this.secondFielderId = secondFielderId;
    }

    public String getThirdFielderId() {
        return thirdFielderId;
    }

    public void setThirdFielderId(String thirdFielderId) {
        this.thirdFielderId = thirdFielderId;
    }

}
