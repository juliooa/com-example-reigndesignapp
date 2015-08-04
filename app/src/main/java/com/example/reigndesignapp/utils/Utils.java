package com.example.reigndesignapp.utils;

import android.app.Activity;
import android.util.DisplayMetrics;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by JulioAndres on 8/3/15.
 */
public class Utils {

    public static int dpToPx(int dp, Activity act) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        act.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        float logicalDensity = outMetrics.density;
        return (int) Math.ceil(dp * logicalDensity);
    }

    public static String getTimePassed(String post_date) {

        Calendar cal = Calendar.getInstance();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH);
            format.setTimeZone(TimeZone.getTimeZone("GMT"));
            Date date_stamp = format.parse(post_date);

            Calendar c = Calendar.getInstance();
            c.setTime(date_stamp);
            long subs = Math.abs(cal.getTimeInMillis() - c.getTimeInMillis());
            Calendar subCalendar = (Calendar) cal.clone();
            subCalendar.setTimeInMillis(subs);

            String time_passed;

            long minutes=(subs / (60 * 1000));
            time_passed=minutes + " m";
            if(minutes >= 60)
                time_passed = (subs/ (60 * 60 * 1000))+ "h";

            return time_passed;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return post_date;
    }
}
