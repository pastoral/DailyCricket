package me.abir.dailycricketbd.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Abir on 24-Nov-17.
 */

public class CricUtil {

    public static String changeDateFormat(String currentFormat, String requiredFormat, String dateString) {
        String result = "";
        if (dateString == null) {
            return result;
        }
        SimpleDateFormat formatterOld = new SimpleDateFormat(currentFormat, Locale.ENGLISH);
        SimpleDateFormat formatterNew = new SimpleDateFormat(requiredFormat, Locale.ENGLISH);
        Date date = null;
        try {
            date = formatterOld.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) {
            result = formatterNew.format(date);
        }
        return result;
    }

    public static String getDateTimeFromLong(long timeStamp) {
        try {
            Date date = new Date(timeStamp * 1000);
            Format format = new SimpleDateFormat("E dd-MMM-yyyy, h:mm a, z", Locale.ENGLISH);
            return format.format(date);
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    public static String getTimeFromLong(long timeStamp) {
        try {
            Date date = new Date(timeStamp * 1000);
            Format format = new SimpleDateFormat("h:mm a, z", Locale.ENGLISH);
            return format.format(date);
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }


    public static SpannableString getTitle(String boldText, String normalText) {
        SpannableString str = new SpannableString(boldText + normalText);
        str.setSpan(
                new StyleSpan(Typeface.NORMAL),
                0,
                boldText.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        return str;
    }

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static String getAccessToken(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(Configuration.APP_SHARED_PREF,
                Context.MODE_PRIVATE);
        return preferences.getString(Configuration.PREF_TOKEN, "");
    }
}
