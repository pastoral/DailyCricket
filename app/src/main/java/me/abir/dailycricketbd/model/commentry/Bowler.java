package me.abir.dailycricketbd.model.commentry;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bowler {

    @SerializedName("bowler_id")
    @Expose
    private String bowlerId;
    @SerializedName("overs")
    @Expose
    private String overs;
    @SerializedName("maidens")
    @Expose
    private String maidens;
    @SerializedName("runs_conceded")
    @Expose
    private String runsConceded;
    @SerializedName("wickets")
    @Expose
    private String wickets;
    @SerializedName("noballs")
    @Expose
    private String noballs;
    @SerializedName("wides")
    @Expose
    private String wides;
    @SerializedName("econ")
    @Expose
    private String econ;

    public String getBowlerId() {
        return bowlerId;
    }

    public void setBowlerId(String bowlerId) {
        this.bowlerId = bowlerId;
    }

    public String getOvers() {
        return overs;
    }

    public void setOvers(String overs) {
        this.overs = overs;
    }

    public String getMaidens() {
        return maidens;
    }

    public void setMaidens(String maidens) {
        this.maidens = maidens;
    }

    public String getRunsConceded() {
        return runsConceded;
    }

    public void setRunsConceded(String runsConceded) {
        this.runsConceded = runsConceded;
    }

    public String getWickets() {
        return wickets;
    }

    public void setWickets(String wickets) {
        this.wickets = wickets;
    }

    public String getNoballs() {
        return noballs;
    }

    public void setNoballs(String noballs) {
        this.noballs = noballs;
    }

    public String getWides() {
        return wides;
    }

    public void setWides(String wides) {
        this.wides = wides;
    }

    public String getEcon() {
        return econ;
    }

    public void setEcon(String econ) {
        this.econ = econ;
    }

}
