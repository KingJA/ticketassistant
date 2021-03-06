package com.kingja.ticketassistant.util;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Description:TODO
 * Create Time:2018/7/9 11:21
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DateUtil {
    private static final int MILLIS_DAY = 1000 * 60 * 60 * 24;
    private static final int MILLIS_HOUR = 1000 * 60 * 60;
    private static final int MILLIS_MIN = 1000 * 60;
    private static final String FORMAT_DATE_HOUR_MIN_SEC ="yyyy-MM-dd HH:mm:ss";

    public static String getDateString(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(d);
    }

    public static String getDateString(long time, String fromat) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat(fromat);
        return sf.format(d);
    }

    public static Date str2Date(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    public static String date2Str(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(date);
    }

    public static String getShortDate(String longDate) {
        if (TextUtils.isEmpty(longDate)) {
            return "";
        }
        return date2Str(str2Date(longDate));
    }

    public static long getMillSeconds(String strDate) {
        return getMillSeconds(strDate, "yyyy-MM-dd");
    }

    public static long getMillSeconds(String strDate, String format) {
//        yyyy-MM-dd HH:mm:ss
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = formatter.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static long getYearMillSeconds(int offset) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, offset);
        return c.getTime().getTime();
    }


}
