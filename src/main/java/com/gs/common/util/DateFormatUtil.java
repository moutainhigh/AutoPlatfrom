package com.gs.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author WangGenshen
 * @date 5/16/16
 */
public class DateFormatUtil {

    public static final String DEFAULT_PATTERN = "yyyy年MM月dd日 HH:mm:ss";

    public static Map<String, ThreadLocal<DateFormat>> dfMap = new HashMap<String, ThreadLocal<DateFormat>>();

    public static DateFormat getDateFormat(final String pattern) {
        ThreadLocal<DateFormat> dfThreadLocal = dfMap.get(pattern);
        if (dfThreadLocal == null) {
            synchronized (DateFormatUtil.class) {
                dfThreadLocal = new ThreadLocal<DateFormat>() {

                    @Override
                    protected DateFormat initialValue() {
                        return new SimpleDateFormat(pattern);
                    }
                };
                dfMap.put(pattern, dfThreadLocal);
            }
        }
        return dfThreadLocal.get();
    }

    public static String format(Calendar cal, String pattern) {
        return getDateFormat(pattern).format(cal.getTime());
    }

    public static String format(Long millis, String pattern) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        return format(cal, pattern);
    }

    public static String format(Date date, String pattern) {
        return getDateFormat(pattern).format(date);
    }

    public static String defaultFormat(Date date) {
        return format(date, DEFAULT_PATTERN);
    }

    public static String defaultFormat(Calendar cal) {
        return format(cal, DEFAULT_PATTERN);
    }

    public static String defaultFormat(long millis) {
        return format(millis, DEFAULT_PATTERN);
    }

    /**
     * 将时间字符串转换为Date类型
     * @param dateStr 时间类型的字符串
     * @param formatStr 格式化的字符串 如 yyyy-MM-dd HH:mm:ss
     * @return Date
     */
    public static Date StrToDate(String dateStr, String formatStr) {
        Date date = null;
        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern(formatStr);
        try {
            date = formater.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
