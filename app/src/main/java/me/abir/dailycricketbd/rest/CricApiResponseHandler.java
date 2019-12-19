package me.abir.dailycricketbd.rest;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.abir.dailycricketbd.interfaces.CommentaryResponseListener;
import me.abir.dailycricketbd.interfaces.OnFullNewsResponse;
import me.abir.dailycricketbd.interfaces.OnNewsResponseListener;
import me.abir.dailycricketbd.interfaces.OnPhotoResponseListener;
import me.abir.dailycricketbd.interfaces.OnPollResponseListener;
import me.abir.dailycricketbd.interfaces.OnResultResponseListener;
import me.abir.dailycricketbd.interfaces.OnScorecardResponseListener;
import me.abir.dailycricketbd.interfaces.OnVideoResponseListener;
import me.abir.dailycricketbd.interfaces.SingleLiveMatchDetailsListener;
import me.abir.dailycricketbd.interfaces.TokenResponseListener;
import me.abir.dailycricketbd.model.ScoreCardLiveDetailsWrapper;
import me.abir.dailycricketbd.model.Token;
import me.abir.dailycricketbd.model.commentry.CommentaryModel;
import me.abir.dailycricketbd.model.featured_news_v2.FeaturedArticleModel;
import me.abir.dailycricketbd.model.full_news_v2.FullNewsModel;
import me.abir.dailycricketbd.model.image_module_v2.PhotosModel;
import me.abir.dailycricketbd.model.latest_news_v2.LatestArticleModel;
import me.abir.dailycricketbd.model.poll_v2.PollQuestionModel;
import me.abir.dailycricketbd.model.poll_v2.PollVoteResponse;
import me.abir.dailycricketbd.model.results_module.Venue;
import me.abir.dailycricketbd.model.single_live_match.Commentary;
import me.abir.dailycricketbd.model.single_live_match.Equations;
import me.abir.dailycricketbd.model.single_live_match.ExtraRuns;
import me.abir.dailycricketbd.model.single_live_match.InningsStatusModel;
import me.abir.dailycricketbd.model.single_live_match.LiveInning;
import me.abir.dailycricketbd.model.single_live_match.LiveMatchBatsman;
import me.abir.dailycricketbd.model.single_live_match.LiveMatchBowler;
import me.abir.dailycricketbd.model.single_live_match.Player;
import me.abir.dailycricketbd.model.single_live_match.SingleLiveMatchModel;
import me.abir.dailycricketbd.model.single_live_match.SingleLiveMatchResponse;
import me.abir.dailycricketbd.model.single_match_scorecard.DidNotBatModel;
import me.abir.dailycricketbd.model.single_match_scorecard.ScoreCardBatsman;
import me.abir.dailycricketbd.model.single_match_scorecard.ScoreCardBowler;
import me.abir.dailycricketbd.model.single_match_scorecard.ScoreCardFow;
import me.abir.dailycricketbd.model.single_match_scorecard.ScoreCardInnings;
import me.abir.dailycricketbd.model.single_match_scorecard.ScoreCardWrapperBatsmen;
import me.abir.dailycricketbd.model.single_match_scorecard.ScoreCardWrapperBowler;
import me.abir.dailycricketbd.model.single_match_scorecard.ScoreCardWrapperKeyValue;
import me.abir.dailycricketbd.model.single_match_scorecard.ScoreCardWrapperModel;
import me.abir.dailycricketbd.model.single_match_scorecard.SingleMatchScoreCardModel;
import me.abir.dailycricketbd.model.single_match_scorecard.TotalScoreWrapper;
import me.abir.dailycricketbd.model.video_module_v2.VideoModel;
import me.abir.dailycricketbd.util.Configuration;
import me.abir.dailycricketbd.util.CricResponseParser;
import me.abir.dailycricketbd.util.CricUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Abir on 13-Oct-17.
 */

public class CricApiResponseHandler {
    private static final String TAG = "CricApiResponseHandler";
    private AppApiInterface apiInterface;
    private CricBdApiInterface cricApiInterface;
    private OnNewsResponseListener onNewsResponseListener;
    private OnPhotoResponseListener onPhotoResponseListener;
    private OnVideoResponseListener onVideoResponseListener;
    private OnFullNewsResponse onFullNewsResponse;
    private OnPollResponseListener onPollResponseListener;
    private OnResultResponseListener onResultResponseListener;
    private OnScorecardResponseListener onScorecardResponseListener;
    private CommentaryResponseListener commentaryResponseListener;
    private SingleLiveMatchDetailsListener singleLiveMatchDetailsListener;
    private TokenResponseListener tokenResponseListener;

    public CricApiResponseHandler(String language) {
        if (language.equals("en")) {
            apiInterface = CricRetroClient.getInstance().getAppClient().create(AppApiInterface.class);
        } else {
            apiInterface = CricRetroClient.getInstance().getAppClientBn().create(AppApiInterface.class);
        }
        cricApiInterface = CricRetroClient.getInstance().getCricketClient().create(CricBdApiInterface.class);
    }

    public void getLatestNews(int pageNo) {
        apiInterface.getLatestArticle(pageNo).enqueue(new Callback<LatestArticleModel>() {
            @Override
            public void onResponse(Call<LatestArticleModel> call, Response<LatestArticleModel> response) {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                if (response.isSuccessful()) {
                    onNewsResponseListener.onLatestNewsResponse(response.body());
                } else {
                    onNewsResponseListener.onLatestNewsResponse(null);
                }
            }

            @Override
            public void onFailure(Call<LatestArticleModel> call, Throwable t) {
                onNewsResponseListener.onLatestNewsResponse(null);
            }
        });
    }

    public void getFeaturedNews() {
        apiInterface.getFeaturedArticle().enqueue(new Callback<FeaturedArticleModel>() {
            @Override
            public void onResponse(Call<FeaturedArticleModel> call, Response<FeaturedArticleModel> response) {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                if (response.isSuccessful()) {
                    onNewsResponseListener.onFeaturedNewsResponse(response.body());
                } else {
                    onNewsResponseListener.onFeaturedNewsResponse(null);
                }
            }

            @Override
            public void onFailure(Call<FeaturedArticleModel> call, Throwable t) {
                onNewsResponseListener.onFeaturedNewsResponse(null);
            }
        });
    }

    public void getPhotos(int pageNo) {
        apiInterface.getImages(pageNo).enqueue(new Callback<PhotosModel>() {
            @Override
            public void onResponse(Call<PhotosModel> call, Response<PhotosModel> response) {
                Log.d(TAG, "getPhotos.onResponse() called with: call = [" + call +
                        "], response = [" + response + "]");
                if (response.isSuccessful()) {
                    onPhotoResponseListener.onPhotoResponse(response.body());
                } else {
                    onPhotoResponseListener.onPhotoResponse(null);
                }
            }

            @Override
            public void onFailure(Call<PhotosModel> call, Throwable t) {
                Log.e(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                onPhotoResponseListener.onPhotoResponse(null);

            }
        });
    }

    public void getVideos(int pageNo) {
        apiInterface.getVideos(pageNo).enqueue(new Callback<VideoModel>() {
            @Override
            public void onResponse(Call<VideoModel> call, Response<VideoModel> response) {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                if (response.isSuccessful()) {
                    onVideoResponseListener.onVideoResponse(response.body());
                } else {
                    onVideoResponseListener.onVideoResponse(null);
                }
            }

            @Override
            public void onFailure(Call<VideoModel> call, Throwable t) {
                onVideoResponseListener.onVideoResponse(null);
            }
        });
    }

    public void getFullNews(String slug) {
        apiInterface.getFullNews(slug).enqueue(new Callback<FullNewsModel>() {
            @Override
            public void onResponse(Call<FullNewsModel> call, Response<FullNewsModel> response) {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                if (response.isSuccessful()) {
                    onFullNewsResponse.onFullNews(response.body());
                } else {
                    onFullNewsResponse.onFullNews(null);
                }
            }

            @Override
            public void onFailure(Call<FullNewsModel> call, Throwable t) {

            }
        });
    }

    public void getPoll() {
        apiInterface.getPoll().enqueue(new Callback<PollQuestionModel>() {
            @Override
            public void onResponse(Call<PollQuestionModel> call, Response<PollQuestionModel> response) {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                if (response.isSuccessful()) {
                    PollQuestionModel singlePoll = response.body();
                    Log.i(TAG, "onResponse success " + singlePoll);
                    onPollResponseListener.onPollResponse(response.body());
                } else {
                    onPollResponseListener.onPollResponse(null);
                }
            }

            @Override
            public void onFailure(Call<PollQuestionModel> call, Throwable t) {
                onPollResponseListener.onPollResponse(null);
            }
        });
    }

    public void updatePoll(String optionId, String pollId, String deviceId) {
        apiInterface.updatePoll(optionId, pollId, deviceId).enqueue(new Callback<PollVoteResponse>() {
            @Override
            public void onResponse(Call<PollVoteResponse> call, Response<PollVoteResponse> response) {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                if (response.isSuccessful()) {
                    Log.i(TAG, "Voting Successful");
                    PollVoteResponse voteResponse = response.body();
                    onPollResponseListener.onVoteResponse(voteResponse.getMessage());
                } else {
                    onPollResponseListener.onVoteResponse(null);
                }
            }

            @Override
            public void onFailure(Call<PollVoteResponse> call, Throwable t) {
                onPollResponseListener.onVoteResponse(null);
            }
        });
    }

    public void getToken() {
        cricApiInterface.getLiveScoreToken().enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                Log.d(TAG, "getToken.onResponse() called with: call = [" + call + "]," +
                        " response = [" + response + "]");
                if (response.isSuccessful() && tokenResponseListener != null) {
                    Token token = response.body();
                    tokenResponseListener.onTokenResponse(token.getToken());
                }

            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Log.e(TAG, "getToken.onFailure() called with: call = [" + call +
                        "], t = [" + t + "]");
                if (tokenResponseListener != null)
                    tokenResponseListener.onTokenResponse(null);
            }
        });
    }

    /*public void getSingleMatchInfoData(String token) {
        Log.i(TAG, "getSingleMatchInfoData() called");
        apiInterface.getSingleMatchInfo(token, "37773").enqueue(new Callback<SingleMatchInfoModel>() {
            @Override
            public void onResponse(Call<SingleMatchInfoModel> call, Response<SingleMatchInfoModel> response) {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
            }

            @Override
            public void onFailure(Call<SingleMatchInfoModel> call, Throwable t) {
                Log.e(TAG, "onFailure: ");
            }
        });
    }*/

    public void getSingleLiveMatchData(String matchId, String token, final boolean isLive) {
        cricApiInterface.getSingleLiveMatch(token, matchId).enqueue(new Callback<SingleLiveMatchModel>() {
            @Override
            public void onResponse(Call<SingleLiveMatchModel> call, Response<SingleLiveMatchModel> response) {
                Log.d(TAG, "getSingleLiveMatchData() called with: call = [" + call + "], response = [" + response + "]");
                if (response.isSuccessful()) {
                    singleLiveMatchDetailsListener.onSingleLiveMatchResponse(parseSingleLiveMatchDetails(response.body(), isLive));
                }
            }

            @Override
            public void onFailure(Call<SingleLiveMatchModel> call, Throwable t) {
                Log.e(TAG, "getSingleLiveMatchData() called with: call = [" + call + "], t = [" + t + "]");
                singleLiveMatchDetailsListener.onSingleLiveMatchResponse(null);
            }
        });
    }

    /**
     * Main method to parse Live score info
     *
     * @param liveMatchModel
     * @return
     */
    private List<Object> parseSingleLiveMatchDetails(SingleLiveMatchModel liveMatchModel, boolean isLive) {
        List<Object> objectList = new ArrayList<>();

        SingleLiveMatchResponse liveMatchResponse = liveMatchModel.getResponse();

        // Batsmen
        List<ScoreCardWrapperBatsmen> batsmen = getSingleLiveMatchBatsManList(liveMatchResponse.getBatsmen());
        if (isLive) {
            objectList.addAll(batsmen);
        }
        //Bowlers
        List<ScoreCardWrapperBowler> bowlers = getLiveMatchScoreBowlerList(liveMatchResponse.getBowlers());
        if (isLive) {
            objectList.addAll(bowlers);
        }
        //Live Innings Status
        objectList.add(getInningsStatus(batsmen, bowlers, liveMatchResponse.getLiveInning()));
        // Recent Commentaries
        List<Commentary> commentaries = liveMatchResponse.getCommentaries();
        if (commentaries != null && commentaries.size() > 0) {
            Collections.reverse(commentaries);
            objectList.addAll(commentaries);
        }

        return objectList;
    }

    private InningsStatusModel getInningsStatus(List<ScoreCardWrapperBatsmen> batsmen,
                                                List<ScoreCardWrapperBowler> bowlers,
                                                LiveInning liveInning) {
        InningsStatusModel model = new InningsStatusModel();
        // First and second batsman
        try {
            for (int i = 0; i < batsmen.size(); i++) {
                ScoreCardWrapperBatsmen bat = batsmen.get(i);
                String val = bat.getName();
                val = val + "  " + bat.getRun() + "(" + bat.getBall() + ")";
                if (i == 1) {
                    model.setFirstBatsman(val);
                } else if (i == 2) {
                    model.setSecondBatsman(val);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Match status
        try {
            String temName = liveInning.getName().substring(0, 3);
            Equations equations = liveInning.getEquations();
            String matchStatus = "Score after " + equations.getOvers() + " overs | " + temName + " " +
                    equations.getRuns() + "-" + equations.getWickets();
            model.setInningsStatus(matchStatus);
            model.setLastFiveOvers("Last five overs: " + liveInning.getLastFiveOvers());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Bowler
        try {
            if (bowlers.size() > 1) {
                ScoreCardWrapperBowler bowl = bowlers.get(1);
                model.setCurrentBowler(bowl.getName());
                model.setBowlerFigure(bowl.getOvers() + "-" + bowl.getMaidens() + "-" +
                        bowl.getRunsConceded() + "-" + bowl.getWickets());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return model;
    }


    private List<ScoreCardWrapperBatsmen> getSingleLiveMatchBatsManList(List<LiveMatchBatsman> batsmanList) {
        List<ScoreCardWrapperBatsmen> batsmen = new ArrayList<>();
        try {
            ScoreCardWrapperBatsmen header = new ScoreCardWrapperBatsmen();
            header.setName("Batsman");
            header.setRun("R");
            header.setBall("B");
            header.setFours("4s");
            header.setSixes("6s");
            header.setStrikeRate("SR");
            batsmen.add(header);

            for (LiveMatchBatsman batsman : batsmanList) {
                ScoreCardWrapperBatsmen wrapperBatsmen = new ScoreCardWrapperBatsmen();
                wrapperBatsmen.setId(String.valueOf(batsman.getBatsmanId()));
                wrapperBatsmen.setName(batsman.getName());
                wrapperBatsmen.setRun(String.valueOf(batsman.getRuns()));
                wrapperBatsmen.setBall(String.valueOf(batsman.getBallsFaced()));
                wrapperBatsmen.setFours(String.valueOf(batsman.getFours()));
                wrapperBatsmen.setSixes(String.valueOf(batsman.getSixes()));
                wrapperBatsmen.setStrikeRate(batsman.getStrikeRate());

                batsmen.add(wrapperBatsmen);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return batsmen;
    }


    private List<ScoreCardWrapperBowler> getLiveMatchScoreBowlerList(List<LiveMatchBowler> bowlerList) {
        List<ScoreCardWrapperBowler> bowlers = new ArrayList<>();
        try {
            ScoreCardWrapperBowler header = new ScoreCardWrapperBowler();
            header.setName(Configuration.HEADER_TEXT_BOWLER);
            header.setOvers("O");
            header.setMaidens("M");
            header.setRunsConceded("R");
            header.setWickets("W");
            header.setEconomyRate("Econ");
            bowlers.add(header);

            for (LiveMatchBowler bowler : bowlerList) {
                ScoreCardWrapperBowler wrapperBowler = new ScoreCardWrapperBowler();
                wrapperBowler.setId(bowler.getBowlerId());
                wrapperBowler.setName(bowler.getName());
                wrapperBowler.setOvers(bowler.getOvers());
                wrapperBowler.setRunsConceded(String.valueOf(bowler.getRunsConceded()));
                wrapperBowler.setMaidens(String.valueOf(bowler.getMaidens()));
                wrapperBowler.setWickets(String.valueOf(bowler.getWickets()));
                wrapperBowler.setEconomyRate(bowler.getEcon());

                bowlers.add(wrapperBowler);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bowlers;
    }

    public void getSingleMatchScoreCardData(String matchId, String token) {
        cricApiInterface.getSingleMatchScoreCard(token, matchId).enqueue(new Callback<SingleMatchScoreCardModel>() {
            @Override
            public void onResponse(Call<SingleMatchScoreCardModel> call, Response<SingleMatchScoreCardModel> response) {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response.body().toString() + "]");
                if (response.isSuccessful()) {
                    ScoreCardLiveDetailsWrapper allWrapper = parseScoreAndLive(response.body());
                    onScorecardResponseListener.onScorecardResponse(allWrapper);
                } else {
                    onScorecardResponseListener.onScorecardResponse(null);
                }
            }

            @Override
            public void onFailure(Call<SingleMatchScoreCardModel> call, Throwable t) {
                Log.e(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                onScorecardResponseListener.onScorecardResponse(null);
            }
        });
    }

    private ScoreCardLiveDetailsWrapper parseScoreAndLive(SingleMatchScoreCardModel scoreCardModel) {
        ScoreCardLiveDetailsWrapper allWrapper = new ScoreCardLiveDetailsWrapper();
        allWrapper.setScoreCardWrapperModels(parseScorecard(scoreCardModel));
        allWrapper.setTeamNameA(scoreCardModel.getResponse().getTeama().getName());
        allWrapper.setTeamFlagA(scoreCardModel.getResponse().getTeama().getLogoUrl());
        allWrapper.setTeamNameScoreA(scoreCardModel.getResponse().getTeama().getScoresFull());
        allWrapper.setTeamNameB(scoreCardModel.getResponse().getTeamb().getName());
        allWrapper.setTeamFlagB(scoreCardModel.getResponse().getTeamb().getLogoUrl());
        allWrapper.setTeamNameScoreB(scoreCardModel.getResponse().getTeamb().getScoresFull());
        allWrapper.setMatchStatus(scoreCardModel.getResponse().getStatusStr().toUpperCase());
        allWrapper.setMatchResult(scoreCardModel.getResponse().getStatusNote());
        allWrapper.setTossInfo(scoreCardModel.getResponse().getToss().getText());
        allWrapper.setManOfTheMatch(scoreCardModel.getResponse().getManOfTheMatch().getName());
        allWrapper.setVenue(getVenueNameLocation(scoreCardModel.getResponse().getVenue()));
        allWrapper.setMatchStartTime(CricUtil.getDateTimeFromLong(scoreCardModel.getResponse().getTimestampStart()));
        return allWrapper;
    }

    private String getVenueNameLocation(Venue venue) {
        return venue.getName() + ", " + venue.getLocation();
    }

    public void getCommentariesByInnings(String matchId, String token, String inningsNo) {
        cricApiInterface.getCommentryByInnings(token, matchId, inningsNo).enqueue(new Callback<CommentaryModel>() {
            @Override
            public void onResponse(Call<CommentaryModel> call, Response<CommentaryModel> response) {
                Log.d(TAG, "getCommentariesByInnings() called with: call = [" + call + "], response = [" + response + "]");
                if (response.isSuccessful() && response.body() != null && commentaryResponseListener != null) {
                    List<Commentary> commentaryList = response.body().getResponse().getCommentaries();
                    commentaryResponseListener.onCommentaryResponse(commentaryList);
                }
            }

            @Override
            public void onFailure(Call<CommentaryModel> call, Throwable t) {
                Log.e(TAG, "getCommentariesByInnings() called with: call = [" + call + "], t = [" + t + "]");
                if (commentaryResponseListener != null) {
                    commentaryResponseListener.onCommentaryResponse(null);
                }
            }
        });
    }

    /**
     * Main method to parse Score Card data
     *
     * @param scoreCardModel
     * @return
     */
    private List<ScoreCardWrapperModel> parseScorecard(SingleMatchScoreCardModel scoreCardModel) {
        Log.i(TAG, "parseScorecard() called with: scoreCardModel = [" + scoreCardModel.toString() + "]");
        List<ScoreCardWrapperModel> wrapperModels = new ArrayList<>();

        List<ScoreCardInnings> innings = scoreCardModel.getResponse().getInnings();
        List<Player> playerList = scoreCardModel.getResponse().getPlayers();

        for (ScoreCardInnings inn : innings) {
            // Child
            List<Object> data = new ArrayList<>();
            data.addAll(getScorecardWrapperBatsmenList(inn.getBatsmen(), playerList));
            data.add(getScorecardWrapperExtraRuns(inn.getExtraRuns()));
            data.add(getScorecardWrapperTotalRuns(inn.getEquations()));
            data.add(getYetToBat(inn.getDidNotBatModels()));
            data.add(getFallOfWickets(inn.getFows()));
            data.addAll(getScorecardWrapperBowlerList(inn.getBowlers(), playerList));

            Log.d(TAG, "parseScorecard() dataSize: " + data.size());
            // Header
            String inningsName = inn.getName();
            String scoresFull = inn.getScoresFull();
            ScoreCardWrapperKeyValue kv = new ScoreCardWrapperKeyValue();
            kv.setTitle(inningsName);
            kv.setDetails(scoresFull);

            ScoreCardWrapperModel scoreCardWrapperModel = new ScoreCardWrapperModel(kv, data);
            wrapperModels.add(scoreCardWrapperModel);
        }
        return wrapperModels;
    }

    private ScoreCardWrapperKeyValue getYetToBat(List<DidNotBatModel> didNotBatModels) {
        ScoreCardWrapperKeyValue keyValue = new ScoreCardWrapperKeyValue();
        keyValue.setTitle("Yet to Bat");
        try {
            StringBuilder yetToBatName = new StringBuilder();
            for (int i = 0; i < didNotBatModels.size(); i++) {
                DidNotBatModel model = didNotBatModels.get(i);
                yetToBatName.append(model.getName());
                if (i < didNotBatModels.size() - 1) {
                    yetToBatName.append(", ");
                }
            }
            keyValue.setDetails(yetToBatName.toString());
        } catch (Exception e) {
            keyValue.setDetails("");
            e.printStackTrace();
        }
        return keyValue;
    }

    private ScoreCardWrapperKeyValue getFallOfWickets(List<ScoreCardFow> fows) {
        ScoreCardWrapperKeyValue keyValue = new ScoreCardWrapperKeyValue();
        keyValue.setTitle("Fall of Wickets");
        try {
            StringBuilder fallOfWickets = new StringBuilder();
            for (int i = 0; i < fows.size(); i++) {
                ScoreCardFow model = fows.get(i);
                fallOfWickets.append(model.getScoreAtDismissal())
                        .append("-").append(model.getNumber())
                        .append(" (").append(model.getName())
                        .append(", ").append(model.getOversAtDismissal())
                        .append(")");
                if (i < fows.size() - 1) {
                    fallOfWickets.append(", ");
                }
            }
            keyValue.setDetails(fallOfWickets.toString());
        } catch (Exception e) {
            keyValue.setDetails("");
            e.printStackTrace();
        }
        return keyValue;
    }

    private TotalScoreWrapper getScorecardWrapperTotalRuns(Equations equations) {
        TotalScoreWrapper keyValue = new TotalScoreWrapper();
        keyValue.setTitle("Total");
        keyValue.setDetails(equations.getRuns()
                + "/" + equations.getWickets()
                + " (" + equations.getOvers() + " ov) "
                + "RR " + equations.getRunrate());
        return keyValue;
    }

    private ScoreCardWrapperKeyValue getScorecardWrapperExtraRuns(ExtraRuns extraRuns) {
        ScoreCardWrapperKeyValue keyValue = new ScoreCardWrapperKeyValue();
        keyValue.setTitle("Extras");
        String penalty = extraRuns.getPenalty().equals("") ? "0" : extraRuns.getPenalty();
        keyValue.setDetails(extraRuns.getTotal()
                + " (b " + extraRuns.getByes()
                + ", " + "lb " + extraRuns.getLegbyes()
                + ", " + "no " + extraRuns.getNoballs()
                + ", " + "wd " + extraRuns.getWides()
                + ", " + "p " + penalty + ")");
        return keyValue;
    }

    private List<ScoreCardWrapperBatsmen> getScorecardWrapperBatsmenList(List<ScoreCardBatsman> batsmanList, List<Player> playerList) {
        List<ScoreCardWrapperBatsmen> batsmen = new ArrayList<>();
        ScoreCardWrapperBatsmen header = new ScoreCardWrapperBatsmen();
        header.setName("Batsman");
        header.setRun("R");
        header.setBall("B");
        header.setFours("4s");
        header.setSixes("6s");
        header.setStrikeRate("SR");
        batsmen.add(header);

        for (ScoreCardBatsman batsman : batsmanList) {
            ScoreCardWrapperBatsmen wrapperBatsmen = new ScoreCardWrapperBatsmen();
            wrapperBatsmen.setId(batsman.getBatsmanId());
            //wrapperBatsmen.setName(CricResponseParser.getPlayerName(batsman.getBatsmanId(), playerList));
            wrapperBatsmen.setName(batsman.getName());
            wrapperBatsmen.setRun(batsman.getRuns());
            wrapperBatsmen.setBall(batsman.getBallsFaced());
            wrapperBatsmen.setFours(batsman.getFours());
            wrapperBatsmen.setSixes(batsman.getSixes());
            wrapperBatsmen.setHowGotOut(batsman.getHowOut());
            wrapperBatsmen.setStrikeRate(batsman.getStrikeRate());

            batsmen.add(wrapperBatsmen);
        }
        return batsmen;
    }

    private List<ScoreCardWrapperBowler> getScorecardWrapperBowlerList(List<ScoreCardBowler> bowlerList, List<Player> playerList) {
        List<ScoreCardWrapperBowler> bowlers = new ArrayList<>();
        ScoreCardWrapperBowler header = new ScoreCardWrapperBowler();
        header.setName(Configuration.HEADER_TEXT_BOWLER);
        header.setOvers("O");
        header.setMaidens("M");
        header.setRunsConceded("R");
        header.setWickets("W");
        header.setEconomyRate("Econ");
        bowlers.add(header);


        for (ScoreCardBowler bowler : bowlerList) {
            ScoreCardWrapperBowler wrapperBowler = new ScoreCardWrapperBowler();
            wrapperBowler.setId(bowler.getBowlerId());
            //wrapperBowler.setName(CricResponseParser.getPlayerName(bowler.getBowlerId(), playerList));
            wrapperBowler.setName(bowler.getName());
            wrapperBowler.setOvers(bowler.getOvers());
            wrapperBowler.setRunsConceded(bowler.getRunsConceded());
            wrapperBowler.setMaidens(bowler.getMaidens());
            wrapperBowler.setWickets(bowler.getWickets());
            wrapperBowler.setNoBalls(bowler.getNoballs());
            wrapperBowler.setWides(bowler.getWides());
            wrapperBowler.setEconomyRate(bowler.getEcon());

            bowlers.add(wrapperBowler);
        }
        return bowlers;
    }


    public void setOnNewsResponseListener(OnNewsResponseListener onNewsResponseListener) {
        this.onNewsResponseListener = onNewsResponseListener;
    }

    public void setOnPhotoResponseListener(OnPhotoResponseListener onPhotoResponseListener) {
        this.onPhotoResponseListener = onPhotoResponseListener;
    }

    public void setOnVideoResponseListener(OnVideoResponseListener onVideoResponseListener) {
        this.onVideoResponseListener = onVideoResponseListener;
    }

    public void setOnFullNewsResponse(OnFullNewsResponse onFullNewsResponse) {
        this.onFullNewsResponse = onFullNewsResponse;
    }

    public void setOnPollResponseListener(OnPollResponseListener onPollResponseListener) {
        this.onPollResponseListener = onPollResponseListener;
    }

    public void setOnResultResponseListener(OnResultResponseListener onResultResponseListener) {
        this.onResultResponseListener = onResultResponseListener;
    }

    public void setOnScorecardResponseListener(OnScorecardResponseListener onScorecardResponseListener) {
        this.onScorecardResponseListener = onScorecardResponseListener;
    }

    public void setCommentaryResponseListener(CommentaryResponseListener commentaryResponseListener) {
        this.commentaryResponseListener = commentaryResponseListener;
    }

    public void setSingleLiveMatchDetailsListener(SingleLiveMatchDetailsListener singleLiveMatchDetailsListener) {
        this.singleLiveMatchDetailsListener = singleLiveMatchDetailsListener;
    }

    public void setTokenResponseListener(TokenResponseListener tokenResponseListener) {
        this.tokenResponseListener = tokenResponseListener;
    }
}
