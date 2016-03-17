package com.organics.dockercontrol.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wanghc on 2016/3/15.
 */
public class DateUtils {
    private DateUtils() {
    }

    public static Date formatDockerDate(final String dateRead) {
        // 2016-03-14T11:31:28.114247976+08:00
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            return dateFormat.parse(dateRead.substring(0, dateRead.lastIndexOf(".")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static String formatFileNameDate(final Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public static String formatStatsDate(final Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
}
