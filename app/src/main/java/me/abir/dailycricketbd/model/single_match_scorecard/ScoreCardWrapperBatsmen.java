package me.abir.dailycricketbd.model.single_match_scorecard;

public class ScoreCardWrapperBatsmen {

    private String name;
    private String id;
    private String howGotOut;
    private String run;
    private String ball;
    private String fours;
    private String sixes;
    private String strikeRate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHowGotOut() {
        return howGotOut;
    }

    public void setHowGotOut(String howGotOut) {
        this.howGotOut = howGotOut;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public String getBall() {
        return ball;
    }

    public void setBall(String ball) {
        this.ball = ball;
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

    public String getStrikeRate() {
        return strikeRate;
    }

    public void setStrikeRate(String strikeRate) {
        this.strikeRate = strikeRate;
    }
}
