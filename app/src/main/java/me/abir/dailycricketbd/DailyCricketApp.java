package me.abir.dailycricketbd;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.multidex.MultiDexApplication;

import com.google.android.gms.ads.MobileAds;

import java.util.UUID;

import me.abir.dailycricketbd.util.Configuration;
import me.abir.dailycricketbd.util.LocaleManager;

public class DailyCricketApp extends MultiDexApplication {

    private static String uniqueID = null;

    @Override
    public void onCreate() {
        super.onCreate();
        MobileAds.initialize(this, getString(R.string.ad_app_id));
        getUniqueID(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.setLocale(base)); // Language resetting
    }

    @Override
    public void onConfigurationChanged(android.content.res.Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleManager.setLocale(this); // Language resetting
    }


    public synchronized static String getUniqueID(Context context) {
        if (uniqueID == null) {
            SharedPreferences sharedPrefs = context.getSharedPreferences(
                    Configuration.APP_SHARED_PREF, Context.MODE_PRIVATE);
            uniqueID = sharedPrefs.getString(Configuration.PREF_UNIQUE_ID, null);
            if (uniqueID == null) {
                uniqueID = UUID.randomUUID().toString();
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString(Configuration.PREF_UNIQUE_ID, uniqueID);
                editor.apply();
            }
        }
        return uniqueID;
    }
}
