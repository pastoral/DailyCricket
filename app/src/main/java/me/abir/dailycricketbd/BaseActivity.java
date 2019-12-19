package me.abir.dailycricketbd;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsServiceConnection;
import androidx.browser.customtabs.CustomTabsSession;

import me.abir.dailycricketbd.util.LocaleManager;

public abstract class BaseActivity extends AppCompatActivity {

    private CustomTabsClient mClient;
    private CustomTabsSession mCustomTabsSession;
    private CustomTabsIntent.Builder builder;
    private boolean warmedUp = false;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.setLocale(base)); // Language resetting
    }

    protected void prepareCustomTab(final String url) {
        builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(getResources().getColor(R.color.colorPrimary));
        builder.setShowTitle(true);
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_dashboard_black_24dp);
        PendingIntent pendingIntent = createPendingShareIntent(url);
        builder.setActionButton(icon, getString(R.string.app_name), pendingIntent, true);
        builder.addMenuItem("Share this page", pendingIntent);
        CustomTabsServiceConnection mCustomTabsServiceConnection = new CustomTabsServiceConnection() {
            @Override
            public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
                //Pre-warming
                mClient = customTabsClient;
                mClient.warmup(0L);
                mCustomTabsSession = mClient.newSession(null);
                mCustomTabsSession.mayLaunchUrl(Uri.parse(url), null, null);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mClient = null;
            }
        };
        warmedUp = CustomTabsClient.bindCustomTabsService(this,
                "com.android.chrome", mCustomTabsServiceConnection);

        builder.build().launchUrl(this, Uri.parse(url));
    }

    private PendingIntent createPendingShareIntent(String url) {
        Intent actionIntent = new Intent(Intent.ACTION_SEND);
        actionIntent.setType("text/plain");
        actionIntent.putExtra(Intent.EXTRA_TEXT, url);
        return PendingIntent.getActivity(
                getApplicationContext(), 0, actionIntent, 0);
    }

}
