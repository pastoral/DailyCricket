package me.abir.dailycricketbd.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import me.abir.dailycricketbd.model.single_live_match.CurrentPartnership;
import me.abir.dailycricketbd.model.single_match_scorecard.ManOfTheMatch;
import me.abir.dailycricketbd.rest.deserializer.CurrentPartnershipDeserializer;
import me.abir.dailycricketbd.rest.deserializer.ManOfTheMatchDeserializer;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Abir on 13-Oct-17.
 */

public class CricRetroClient {
    private static final String TAG = "CricRetroClient";
    private static CricRetroClient instance;
    private static Retrofit retrofitCric = null;
    private static Retrofit retrofitAppEn = null;
    private static Retrofit retrofitAppBn = null;
    private static Retrofit retrofitAppFCM = null;

    private CricRetroClient() {
    }

    public static CricRetroClient getInstance() {
        if (instance == null) {
            instance = new CricRetroClient();

            retrofitCric = new Retrofit.Builder()
                    .baseUrl(CricBdApiInterface.BASE_CRICKET_URL)
                    .client(getOkClient())
                    .addConverterFactory(GsonConverterFactory.create(getGsonClient()))
                    .build();

            retrofitAppEn = new Retrofit.Builder()
                    .baseUrl(AppApiInterface.BASE_APP_URL_EN)
                    .client(getOkClient())
                    .addConverterFactory(GsonConverterFactory.create(getGsonClient()))
                    .build();

            retrofitAppBn = new Retrofit.Builder()
                    .baseUrl(AppApiInterface.BASE_APP_URL_BN)
                    .client(getOkClient())
                    .addConverterFactory(GsonConverterFactory.create(getGsonClient()))
                    .build();

            retrofitAppFCM = new Retrofit.Builder()
                    .baseUrl(AppApiInterface.BASE_APP_URL_PUSH)
                    .client(getOkClient())
                    .addConverterFactory(GsonConverterFactory.create(getGsonClient()))
                    .build();
        }
        return instance;
    }

    public Retrofit getCricketClient() {
        return retrofitCric;
    }

    public Retrofit getAppClient() {
        return retrofitAppEn;
    }

    public Retrofit getAppClientBn() {
        return retrofitAppBn;
    }

    public Retrofit getAppClientFCM() {
        return retrofitAppFCM;
    }

    private static OkHttpClient getOkClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(15L, TimeUnit.SECONDS)
                .readTimeout(15L, TimeUnit.SECONDS)
                .writeTimeout(15L, TimeUnit.SECONDS);


        return client.build();
    }

    private static Gson getGsonClient() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(ManOfTheMatch.class, new ManOfTheMatchDeserializer());
        builder.registerTypeAdapter(CurrentPartnership.class, new CurrentPartnershipDeserializer());
        return builder.create();
    }

}
