package me.abir.dailycricketbd.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;

import java.util.Locale;

public class LocaleManager {

    public static Context setLocale(Context c) {
        Context con = setNewLocale(c, getLanguage(c));
        return con;
    }

    public static Context setNewLocale(Context c, String language) {
        persistLanguage(c, language);
        return updateResources(c, language);
    }

    public static String getLanguage(Context c) {
        try {
            SharedPreferences sharedPrefs = c.getSharedPreferences(
                    Configuration.APP_SHARED_PREF,
                    Context.MODE_PRIVATE
            );
            return sharedPrefs.getString(Configuration.PREF_APP_LANGUAGE, "en");
        } catch (NullPointerException e) {
            e.printStackTrace();
            return "en";
        }
    }

    public static void persistLanguage(Context c, String language) {
        SharedPreferences sharedPreferences = c.getSharedPreferences(
                Configuration.APP_SHARED_PREF,
                Context.MODE_PRIVATE
        );
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Configuration.PREF_APP_LANGUAGE, language);
        editor.apply();
    }

    private static Context updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources res = context.getResources();
        android.content.res.Configuration config = new android.content.res.Configuration(
                res.getConfiguration()
        );
        if (Build.VERSION.SDK_INT >= 17) {
            config.setLocale(locale);
            context = context.createConfigurationContext(config);
        } else {
            config.locale = locale;
            res.updateConfiguration(config, res.getDisplayMetrics());
        }
        return context;
    }
}
