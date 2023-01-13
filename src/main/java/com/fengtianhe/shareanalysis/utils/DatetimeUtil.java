package com.fengtianhe.shareanalysis.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 冯天鹤
 * @date 2023/1/13.
 */
public class DatetimeUtil {
    public static String FORMAT_DATE_NO_SEPARATOR = "yyyyMMdd";

    public static String format(String format) {
        return format(format, new Date());
    }

    public static String format(String format, Date date) {
        return (new SimpleDateFormat(format)).format(date);
    }
}
