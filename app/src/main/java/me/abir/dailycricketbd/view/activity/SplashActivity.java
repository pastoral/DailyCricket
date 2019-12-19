package me.abir.dailycricketbd.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;

import me.abir.dailycricketbd.BaseActivity;
import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.interfaces.TokenResponseListener;
import me.abir.dailycricketbd.rest.CricApiResponseHandler;
import me.abir.dailycricketbd.util.Configuration;
import me.abir.dailycricketbd.util.LocaleManager;

/**
 * Created by Abir on 27-Apr-18.
 */
public class SplashActivity extends BaseActivity implements TokenResponseListener {
    private static final String TAG = "SplashActivity";
    public static final int SPLASH_DELAY = 2000;
    private CricApiResponseHandler responseHandler;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
        getLiveScoreToken();
        setSplashDelay();
    }

    private void init() {
        mSharedPreferences = getSharedPreferences(
                Configuration.APP_SHARED_PREF, MODE_PRIVATE
        );
    }

    private void setSplashDelay() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setLandingScreen();
                Log.i(TAG, "setSplashDelay() Delay done");
            }
        }, SPLASH_DELAY);
        Log.d(TAG, "setSplashDelay() end");
    }

    private void getLiveScoreToken() {
        responseHandler = new CricApiResponseHandler(LocaleManager.getLanguage(this));
        responseHandler.setTokenResponseListener(this);
        responseHandler.getToken();
    }

    @Override
    public void onTokenResponse(String token) {
        if (token != null) {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString(Configuration.PREF_TOKEN, token);
            editor.apply();
        }
    }

    private void setLandingScreen() {
        Intent intent = new Intent(this, BottomNavActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (responseHandler != null)
            responseHandler.setTokenResponseListener(null);
    }
}
