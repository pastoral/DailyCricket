package me.abir.dailycricketbd.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.util.Configuration;
import me.abir.dailycricketbd.util.LocaleManager;

/**
 * Created by Abir on 06-Nov-17.
 */

public class VideoPlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private static final String TAG = "VideoPlayerActivity";
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    // YouTube player view
    private YouTubePlayerView youTubeView;
    private String videoCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        boolean hasExtra = getIntent().hasExtra(Configuration.VIDEO_ID_TAG);
        if (hasExtra) {
            videoCode = getIntent().getStringExtra(Configuration.VIDEO_ID_TAG);
            Log.d(TAG, "onCreate() id: " + videoCode);
            youTubeView = findViewById(R.id.youtube_view);
            // Initializing video player with developer key
            youTubeView.initialize(Configuration.YOUTUBE_PLAYER_API_KEY, this);
        } else {
            Log.e(TAG, "onCreate() Finishing...");
            finish();
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.setLocale(base)); // Language resetting
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                        boolean wasRestored) {
        if (!wasRestored) {
            // loadVideo() will auto play video
            // Use cueVideo() method, if you don't want to play it automatically
            player.loadVideo(videoCode);
            //player.cueVideo(Config.YOUTUBE_VIDEO_CODE);

            // Hiding player controls
            //player.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
            player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Configuration.YOUTUBE_PLAYER_API_KEY, this);
        }
    }

    private YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }

    @Override
    protected void onDestroy() {
        youTubeView.destroyDrawingCache();
        youTubeView = null;
        super.onDestroy();
    }
}
