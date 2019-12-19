package me.abir.dailycricketbd.model.single_live_match;

/**
 * Created by Abir on 04-May-18.
 */
public class InningsStatusModel {
    private String inningsStatus;
    private String firstBatsman;
    private String secondBatsman;
    private String currentBowler;
    private String bowlerFigure;
    private String lastFiveOvers;

    public String getInningsStatus() {
        return inningsStatus;
    }

    public void setInningsStatus(String inningsStatus) {
        this.inningsStatus = inningsStatus;
    }

    public String getFirstBatsman() {
        return firstBatsman;
    }

    public void setFirstBatsman(String firstBatsman) {
        this.firstBatsman = firstBatsman;
    }

    public String getSecondBatsman() {
        return secondBatsman;
    }

    public void setSecondBatsman(String secondBatsman) {
        this.secondBatsman = secondBatsman;
    }

    public String getCurrentBowler() {
        return currentBowler;
    }

    public void setCurrentBowler(String currentBowler) {
        this.currentBowler = currentBowler;
    }

    public String getBowlerFigure() {
        return bowlerFigure;
    }

    public void setBowlerFigure(String bowlerFigure) {
        this.bowlerFigure = bowlerFigure;
    }

    public String getLastFiveOvers() {
        return lastFiveOvers;
    }

    public void setLastFiveOvers(String lastFiveOvers) {
        this.lastFiveOvers = lastFiveOvers;
    }
}
