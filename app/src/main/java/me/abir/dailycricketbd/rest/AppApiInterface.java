package me.abir.dailycricketbd.rest;

import me.abir.dailycricketbd.model.fcm_models.FCMTokenResponseModel;
import me.abir.dailycricketbd.model.fcm_models.FCMTokenSendRequestModel;
import me.abir.dailycricketbd.model.featured_news_v2.FeaturedArticleModel;
import me.abir.dailycricketbd.model.full_news_v2.FullNewsModel;
import me.abir.dailycricketbd.model.image_module_v2.PhotosModel;
import me.abir.dailycricketbd.model.latest_news_v2.LatestArticleModel;
import me.abir.dailycricketbd.model.poll_v2.PollQuestionModel;
import me.abir.dailycricketbd.model.poll_v2.PollVoteResponse;
import me.abir.dailycricketbd.model.video_module_v2.VideoModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AppApiInterface {

    // Website URL: https://www.dailycricket.com.bd/en

    String BASE_APP_URL_EN = "https://admin.dailycricket.com.bd/api/en/v1/";

    String BASE_APP_URL_BN = "https://admin.dailycricket.com.bd/api/bn/v1/";

    String BASE_APP_URL_PUSH = "https://admin.dailycricket.com.bd/api/v1/";

    @GET("latest-article")
    Call<LatestArticleModel> getLatestArticle(@Query("page") int pageNo);

    @GET("latest-article")
    Call<LatestArticleModel> getLatestArticle(@Query("page") int pageNo, @Query("per_page") int val);

    @GET("featured-article")
    Call<FeaturedArticleModel> getFeaturedArticle();

    @GET("photos")
    Call<PhotosModel> getImages(@Query("page") int pageNo);

    @GET("videos")
    Call<VideoModel> getVideos(@Query("page") int pageNo);

    @GET("news/{slug}")
    Call<FullNewsModel> getFullNews(@Path("slug") String param);

    @GET("poll")
    Call<PollQuestionModel> getPoll();

    @PATCH("poll-vote/{option_id}")
    @FormUrlEncoded
    Call<PollVoteResponse> updatePoll(
            @Path("option_id") String id,
            @Field("poll_id") String pollId,
            @Field("mac_address") String uniqueDeviceId
    );

    @POST("push-notification")
    Call<FCMTokenResponseModel> sendFCMTokenToServer(
            @Header("Content-Type") String contentType,     // application/json
            @Header("X-Requested-With") String requestType, // XMLHttpRequest
            @Body FCMTokenSendRequestModel requestModel
    );
}
