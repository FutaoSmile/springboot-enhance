package com.futao.springboot.enhance.framework.api.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 *
 * futaosmile@gmail.com
 * @date 2021/4/21
 */
@Slf4j
public class TimeUtils {

    public static final String DEFAULT_PATTERN = "yyyy-MM-dd";
    public static final String FORMAT_PATTERN_YYYY = "yyyy";
    public static final String FORMAT_PATTERN_YYYY_MM = "yyyy-MM";
    public static final String FORMAT_PATTERN_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String FORMAT_PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_PATTERN_YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss:SSS";
    public static final String FORMAT_SOLIDUS_YYYY_MM_DD = "yyyy/MM/dd";

    /**
     * 当前日期时间
     *
     * @return 当前日期时间
     */
    public static LocalDateTime currentDateTime() {
        return LocalDateTime.now(ZoneOffset.ofHours(8));
    }

    /**
     * 当前日期
     *
     * @return 当前日期
     */
    public static LocalDate currentDate() {
        return currentDateTime().toLocalDate();
    }

    /**
     * 当前时间
     *
     * @return 当前时间
     */
    public static LocalTime currentTime() {
        return currentDateTime().toLocalTime();
    }

    /**
     * 转时间戳
     *
     * @param dateTime 时间
     * @return 时间戳
     */
    public static long toTimestamp(LocalDateTime dateTime) {
        return dateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }

    /**
     * 转时间戳
     *
     * @param localDate 时间戳
     * @return
     */
    public static long toTimestamp(LocalDate localDate) {
        return localDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli();
    }

    private TimeUtils() {
    }

    /**
     * 将时间戳 按照 给定格式转化
     * @param timeStamp 时间戳
     * @param format 格式  例如 yyyy-MM-dd
     */
    public static String getParseData(Long timeStamp, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date(timeStamp);
        return sdf.format(date);
    }

    /**
     * 获取与指定日期相差指定月份的日期,offset为负数时,减掉月份
     *
     * @param dateTransFer   yyyy-MM-dd
     * @param format yyyy-MM-dd
     * @param offset 正整数
     * @return yyyy-MM-dd
     */
    public static String getAddMonthDate(String dateTransFer, String format, int offset) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(dateTransFer);
        } catch (ParseException e) {
            log.warn(e.getMessage());
        }
        // 日历对象
        Calendar calendar = Calendar.getInstance();
        // 设置当前日期
        calendar.setTime(date);
        // 增加offset个月
        calendar.add(Calendar.MONTH, offset);
        return sdf.format(calendar.getTime());
    }
}
