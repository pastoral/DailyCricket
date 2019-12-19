package me.abir.dailycricketbd.model.single_match_scorecard;

public class ScoreCardWrapperBowler {

    private String id;
    private String name;
    private String overs;
    private String maidens;
    private String runsConceded;
    private String wickets;
    private String noBalls;
    private String wides;
    private String economyRate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getNoBalls() {
        return noBalls;
    }

    public void setNoBalls(String noBalls) {
        this.noBalls = noBalls;
    }

    public String getWides() {
        return wides;
    }

    public void setWides(String wides) {
        this.wides = wides;
    }

    public String getEconomyRate() {
        return economyRate;
    }

    public void setEconomyRate(String economyRate) {
        this.economyRate = economyRate;
    }
}
