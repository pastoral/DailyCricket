package me.abir.dailycricketbd.rest;

import me.abir.dailycricketbd.model.Token;
import me.abir.dailycricketbd.model.commentry.CommentaryModel;
import me.abir.dailycricketbd.model.featured_news.FeaturedNews;
import me.abir.dailycricketbd.model.full_news_module.FullNewsModel;
import me.abir.dailycricketbd.model.image_module.ImageModel;
import me.abir.dailycricketbd.model.latest_news.LatestNews;
import me.abir.dailycricketbd.model.poll.PollModel;
import me.abir.dailycricketbd.model.results_module.ResultsModel;
import me.abir.dailycricketbd.model.single_live_match.SingleLiveMatchModel;
import me.abir.dailycricketbd.model.single_match_info.SingleMatchInfoModel;
import me.abir.dailycricketbd.model.single_match_scorecard.SingleMatchScoreCardModel;
import me.abir.dailycricketbd.model.video_moule.VideoModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Abir on 13-Oct-17.
 */

public interface CricBdApiInterface {

    String BASE_CRICKET_URL = "https://live.dailycricket.com.bd/api/v1/";

    @GET("token")
    Call<Token> getLiveScoreToken();

    @GET("complete-matches")
    Call<ResultsModel> getCompletedMatchesInfo(@Query("token") String token, @Query("current_page") int currentPage);

    @GET("live-score")
    Call<ResultsModel> getLiveMatchesInfo(@Query("token") String token, @Query("current_page") int currentPage);

    @GET("recent-matches")
    Call<ResultsModel> getFixtureInfo(@Query("token") String token, @Query("current_page") int currentPage);

    @GET("info")
    Call<SingleMatchInfoModel> getSingleMatchInfo(@Query("token") String token, @Query("match_id") String matchId);

    @GET("live")
    Call<SingleLiveMatchModel> getSingleLiveMatch(@Query("token") String token, @Query("match_id") String matchId);

    @GET("scorecard")
    Call<SingleMatchScoreCardModel> getSingleMatchScoreCard(@Query("token") String token, @Query("match_id") String matchId);

    @GET("squad")
    Call<SingleMatchInfoModel> getSquadInfo(@Query("token") String token, @Query("match_id") String matchId);

    @GET("commentary")
    Call<CommentaryModel> getCommentryByInnings(@Query("token") String token, @Query("match_id")
            String matchId, @Query("number") String inningsNo);
}
