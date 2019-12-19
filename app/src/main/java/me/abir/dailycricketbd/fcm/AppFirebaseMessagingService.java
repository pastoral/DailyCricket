package me.abir.dailycricketbd.fcm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.model.fcm_models.RemotePushModel;
import me.abir.dailycricketbd.model.results_module.ResultsModelWrapper;
import me.abir.dailycricketbd.util.Configuration;
import me.abir.dailycricketbd.view.activity.BottomNavActivity;
import me.abir.dailycricketbd.view.activity.FullNewsActivity;
import me.abir.dailycricketbd.view.activity.SingleMatchActivity;

public class AppFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "AppFirebaseMessagingSer";
    public static final String NOTIFICATION_CHANNEL_ID = "daily_cricket_channel";

    @Override
    public void onNewToken(String token) {
        storeFCMToken(token);
    }

    private void storeFCMToken(String token) {
        SharedPreferences sharedPrefs = getSharedPreferences(
                Configuration.APP_SHARED_PREF, Context.MODE_PRIVATE
        );
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(Configuration.PREF_FCM_TOKEN, token);
        editor.putBoolean(Configuration.PREF_IS_FCM_TOKEN_SENT_TO_SERVER, false);
        editor.commit();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        try {
            Map<String, String> dataMap = remoteMessage.getData();
            Log.i(TAG, "onMessageReceived() data: " + dataMap.toString());

            RemotePushModel pushModel = new RemotePushModel();
            pushModel.setId(dataMap.get("id"));
            pushModel.setTitle(dataMap.get("title"));
            pushModel.setMessage(dataMap.get("message"));
            pushModel.setPage(dataMap.get("page"));

            switch (pushModel.getPage()) {
                case "featured-news":
                    pushModel.setSlug(dataMap.get("slug"));
                    break;
                case "match-details":
                    pushModel.setMatchId(dataMap.get("match_id"));
                    pushModel.setMatchStatus(dataMap.get("match_status"));
                    break;
                case "home":
                    break;
                case "web":
                    pushModel.setLink(dataMap.get("link"));
                    break;
            }
            createNotification(pushModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createNotificationChannel(NotificationManager mNotificationManager) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
                    "Push Notification",
                    NotificationManager.IMPORTANCE_HIGH
            );
            notificationChannel.setDescription("");
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void createNotification(RemotePushModel pushModel) {
        try {
            Intent resultIntent = null;

            switch (pushModel.getPage()) {
                case "featured-news":
                    resultIntent = new Intent(this, FullNewsActivity.class);
                    resultIntent.putExtra(Configuration.NEWS_SLUG_TAG, pushModel.getSlug());
                    break;
                case "match-details":
                    ResultsModelWrapper wrapper = new ResultsModelWrapper();
                    wrapper.setMatchId(pushModel.getMatchId());
                    wrapper.setMatchStatus(pushModel.getMatchStatus());
                    wrapper.setTournamentName("");
                    resultIntent = new Intent(this, SingleMatchActivity.class);
                    resultIntent.putExtra(Configuration.KEY_MATCH_INFO_ARG, wrapper);
                    resultIntent.putExtra(
                            Configuration.KEY_MATCH_IS_LIVE,
                            pushModel.getMatchStatus().toUpperCase().equals("LIVE")
                    );
                    break;
                case "home":
                    resultIntent = new Intent(this, BottomNavActivity.class);
                    break;
                case "web":
                    resultIntent = new Intent(this, BottomNavActivity.class);
                    resultIntent.putExtra(Configuration.KEY_WEB_LINK, pushModel.getLink());
                    break;
            }

            Intent parentIntent = new Intent(this, BottomNavActivity.class);

            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
            // Adds the back stack
            stackBuilder.addParentStack(BottomNavActivity.class);
            // Adds the Intent to the top of the stack
            if (!pushModel.getPage().equals("home") && !pushModel.getPage().equals("web")) {
                stackBuilder.addNextIntent(parentIntent);
            }
            stackBuilder.addNextIntent(resultIntent);
            // Gets a PendingIntent containing the entire back stack
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                    0, PendingIntent.FLAG_UPDATE_CURRENT
            );

            NotificationCompat.Builder builder = new NotificationCompat.Builder(
                    this, NOTIFICATION_CHANNEL_ID
            );
            builder.setSmallIcon(R.drawable.ic_push_icon_3);
            builder.setWhen(System.currentTimeMillis());
            builder.setContentTitle(pushModel.getTitle());
            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),
                    R.mipmap.app_icon));
            builder.setColor(Color.GREEN);
            builder.setContentText(pushModel.getMessage());
            builder.setContentIntent(resultPendingIntent);
            builder.setPriority(NotificationCompat.PRIORITY_MAX);
            builder.setAutoCancel(true);

            NotificationManager notificationManager = (NotificationManager)
                    getSystemService(Context.NOTIFICATION_SERVICE);
            createNotificationChannel(notificationManager);
            notificationManager.notify((int) System.currentTimeMillis(), builder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
