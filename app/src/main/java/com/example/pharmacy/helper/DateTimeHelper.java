package com.example.pharmacy.helper;

import android.text.TextUtils;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@SuppressWarnings({"unused", "WeakerAccess"})
public class DateTimeHelper {

    public static final String LOCAL_DATE_TIME_FORMAT = "dd/MM/yyyy hh:mm a";
    public static final String LOCAL_DATE_TIME_DISPLAY_FORMAT = "dd MMM, yyyy hh:mm a";

    public static final String LOCAL_DATE_FORMAT = "dd/MM/yyyy";
    public static final String LOCAL_DATE_DISPLAY_FORMAT = "dd MMM, yyyy";
    public static final String LOCAL_TIME_FORMAT = "hh:mm a";
    private static final String TAG = DateTimeHelper.class.getSimpleName();

    public static Date getDateFromString(String strDate, String dateFormat) {
        return getDateFromString(strDate, dateFormat, Locale.US);
    }

    public static Date getDateFromString(String strDate, String dateFormat, Locale locale) {
        Log.d(TAG, "getDateFromString() called with: strDate = [" + strDate + "], dateFormat = [" + dateFormat + "], locale = [" + locale + "]");
        if (TextUtils.isEmpty(strDate) || TextUtils.isEmpty(dateFormat)) {
            return null;
        }

        if (locale == null) {
            locale = Locale.US;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, locale);
        try {
            String cleanedDate = strDate.replace("T", " ");
            if (cleanedDate.contains(".")) {
                cleanedDate = cleanedDate.substring(0, cleanedDate.indexOf("."));
            }
            Log.d(TAG, "getDateFromString: Remove T " + cleanedDate);
            return sdf.parse(cleanedDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String formatDate(Date date, String format, Locale locale) {
        if (date == null || TextUtils.isEmpty(format)) {
            return "";
        }
        if (locale == null) {
            locale = Locale.US;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
        return sdf.format(date);
    }

    public static String formatDate(Date date, String format) {
        return formatDate(date, format, Locale.US);
    }

    public static String convertDateFormat(String sourceDate, String sourceFormat, String destinationFormat) {
        Date date = getDateFromString(sourceDate, sourceFormat);
        if (date != null) {
            return formatDate(date, destinationFormat);
        }
        return "";
    }

    public static boolean isAfterToday(String targetDate, String dateFormat) {
        return isAfterToday(getDateFromString(targetDate, dateFormat));
    }

    public static boolean isAfterToday(Date targetDate) {
        return targetDate != null && new Date().compareTo(targetDate) < 0;
    }

    public static boolean isSameDay(Date day1, Date day2) {

        if (day1 != null && day2 != null) {
            Calendar c1 = Calendar.getInstance();
            c1.setTime(day1);

            Calendar c2 = Calendar.getInstance();
            c2.setTime(day2);

            int day1Day = c1.get(Calendar.DAY_OF_MONTH);
            int day1Month = c1.get(Calendar.MONTH);
            int day1Year = c1.get(Calendar.YEAR);

            int day2Day = c2.get(Calendar.DAY_OF_MONTH);
            int day2Month = c2.get(Calendar.MONTH);
            int day2Year = c2.get(Calendar.YEAR);

            return (day1Day == day2Day) && (day1Month == day2Month) && (day1Year == day2Year);

        }

        return false;
    }

}
