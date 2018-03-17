package com.alipapa.smp.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtil extends DateUtils {
    private static Logger logger = LoggerFactory.getLogger("DateUtil");
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final String FormatString = "yyyy-MM-dd HH:mm:ss";

    private DateUtil() {
    }

    public static String formatToStr(Date date) {
        return DateFormatUtils.format(date, "yyyyMMdd");
    }

    public static String formatToMonthStr(Date date) {
        return DateFormatUtils.format(date, "yyyyMM");
    }

    public static String formatToStrTime(Date date) {
        return DateFormatUtils.format(date, "yyyy-MM-dd hh:mm:ss");
    }

    public static String formatToStrTimeV1(Date date) {
        return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String formatToStrTimeV2(Date date) {
        return DateFormatUtils.format(date, "yyyyMMddHHmmssSSS");
    }

    public static String formatToHhMmSs(Date date) {
        return DateFormatUtils.format(date, "HH:mm:ss");
    }

    public static String formatToYMDTime(Date date) {
        if (date == null) {
            return "";
        }
        return DateFormatUtils.format(date, "yyyy-MM-dd");
    }

    public static String formatToyyyyMMddHH(Date date) {
        return DateFormatUtils.format(date, "yyyyMMddHH");
    }


    public static String formatToYMTime(Date date) {
        return DateFormatUtils.format(date, "yyyyMM");
    }

    /**
     * 获取日期多少月前或者后的日期
     *
     * @param date      日期
     * @param someMonth 离日期多少个月，整数是之后，负数是之前
     * @return
     */
    public static String getSomeMonthDate(Date date, int someMonth) {
        return getSomeMonthDate(date, someMonth, "yyyy-MM-dd");
    }

    public static String getCurrDay() {
        LocalDate today = LocalDate.now(); // -> 2014-12-24
        int d = today.getDayOfMonth();
        return String.valueOf(d);
    }

    public static String addMillisecondsToTimeStamp(Date date, int millsecond) {
        return String.valueOf(addMilliseconds(date, millsecond).getTime());
    }


    public static String getSomeMonthDate(Date date, int someMonth, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, someMonth);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        return DateFormatUtils.format(calendar, format);
    }

    public static Date getSomeMonthDateToTime(Date date, int someMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, someMonth);
        return calendar.getTime();
    }

    public static Date getSomeDayDateToTime(Date date, int someDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, someDay);
        return calendar.getTime();
    }


    public static String getSomeDay(Date date, int day, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);

        return DateFormatUtils.format(calendar, format);
    }

    public static Date getSomeDate(Date date, int day, String format) {
        String someDate = getSomeDay(date, day, format);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(someDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getSomeSecond(Date date, int second, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, second);

        return DateFormatUtils.format(calendar, format);
    }


    public static String formatDate(String date, String format) {
        //1502这样的数据需要加20-->201502
        if (StringUtils.isNotBlank(date) && StringUtils.isNotBlank(format)) {
            if (date.length() < format.length() && date.length() == 4) {
                date = "20" + date;
            }
        }
        Date formatDate = formatFromString(date, format);
        if (formatDate == null) {
            return "0";
        }
        return DateFormatUtils.format(formatDate, format);
    }

    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String dateStr, String format, boolean isUnixTimestamp) {
        try {
            if (StringUtils.isBlank(format)) {
                format = "yyyy-MM-dd HH:mm:ss";
            }

            SimpleDateFormat sdf = new SimpleDateFormat(format);

            long dateTime = sdf.parse(dateStr).getTime();
            if (isUnixTimestamp) {
                dateTime = dateTime / 1000;
            }

            return String.valueOf(dateTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @param format
     * @return DateFormat
     */
    public static SimpleDateFormat getDateFormat(String format) {
        try {
            if (StringUtils.isBlank(format)) {
                format = "yyyy-MM-dd";
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat;
        } catch (Exception ex) {
            logger.error("日期格式化错误", ex);
            return null;
        }
    }


    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(Date date, boolean isUnixTimestamp) {
        try {
            long dateTime = date.getTime();
            if (isUnixTimestamp) {
                dateTime = dateTime / 1000;
            }

            return String.valueOf(dateTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(long timestamp, String format) {
        if (StringUtils.isBlank(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.CHINA);

        return simpleDateFormat.format(new Date(timestamp));
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String timestampString, String format) {
        if (StringUtils.isBlank(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        if (StringUtils.isBlank(timestampString) || !NumberUtils.isNumber(timestampString)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.CHINA);
        //13位的是Unix时间戳,需要乘以1000
        Long timestamp = Long.parseLong(timestampString);
        if (timestampString.length() == 10) {
            timestamp = timestamp * 1000;
        }
        String date = simpleDateFormat.format(new Date(timestamp));

        return date;
    }

    /*
     * 将时间戳转换为时间
     */
    public static Date stampToDate(String timestampString) {
        if (StringUtils.isBlank(timestampString) || !NumberUtils.isNumber(timestampString)) {
            return null;
        }
        //13位的是Unix时间戳,需要乘以1000
        Long timestamp = Long.parseLong(timestampString);
        if (timestampString.length() == 10) {
            timestamp = timestamp * 1000;
        }

        return new Date(timestamp);
    }


    /**
     * 判断当时间跟传入时间是否在someday内
     *
     * @param thisDay 需要判定的时间
     * @param someDay 距现在的时间
     * @return
     */
    public static boolean isBeforeNowSomeDay(Date thisDay, int someDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(thisDay);
        calendar.add(Calendar.DATE, someDay);

        return calendar.getTime().getTime() > (System.currentTimeMillis());
    }

    public static Date formatFromString(String dateStr, String format) {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        try {
            return getDateFormat(format).parse(dateStr);
        } catch (Exception ex) {
            logger.error("转换时间异常", ex);
        }
        return null;
    }


    public static boolean isNowDateBetween(String startDateStr, String endDateStr) {
        try {
            String format = "yyyy-MM-dd HH:mm:ss";
            Date nowDate = new Date();
            if (StringUtils.isBlank(startDateStr) && StringUtils.isNotBlank(endDateStr)) {
                if (format.length() != endDateStr.length()) {
                    format = "yyyy-MM-dd";
                }
                Date endDate = getDateFormat(format).parse(endDateStr);

                return endDate.getTime() >= nowDate.getTime();
            }

            if (StringUtils.isNotBlank(startDateStr) && StringUtils.isBlank(endDateStr)) {
                if (format.length() != startDateStr.length()) {
                    format = "yyyy-MM-dd";
                }
                Date startDate = getDateFormat(format).parse(startDateStr);

                return startDate.getTime() <= nowDate.getTime();
            }

            if (format.length() != startDateStr.length() || format.length() != endDateStr.length()) {
                format = "yyyy-MM-dd";
            }
            Date startDate = getDateFormat(format).parse(startDateStr);
            Date endDate = getDateFormat(format).parse(endDateStr);

            return (endDate.getTime() >= nowDate.getTime() && startDate.getTime() <= nowDate.getTime());
        } catch (Exception ex) {
            logger.error("判断当前时间是否在这之间异常", ex);
        }

        return false;
    }


    public static String getFormatedStringDate() {
        return getFormatedStringDate(null);
    }


    public static String getFormatedStringDate(String formatter) {
        DateTimeFormatter dateTimeFormatter = null;
        if (StringUtils.isBlank(formatter)) {
            dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        } else {
            dateTimeFormatter = DateTimeFormatter.ofPattern(formatter);
        }
        LocalDateTime date = LocalDateTime.now();
        //时间转为字符串
        return date.format(dateTimeFormatter);
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     *
     * @param date1
     * @param date2
     * @return int
     */
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
        return days;
    }

    //两个时间差的秒数
    public static int differentSecsByMillisecond(Date date1, Date date2) {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000));
        return days;
    }

    //两个UNIX时间差的秒数
    public static int differentSecsByTimeStamp(Long timeBefore, Long timeAfter) {
        int secs = (int) ((timeAfter - timeBefore) / (1000));
        return secs;
    }


    /**
     * 取得昨天的日期
     *
     * @return
     */
    public static String getYesterDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1); //得到前一天
        Date date = calendar.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }


    /**
     * 取得五天前的日期
     *
     * @return
     */
    public static String getFiveDayAgo(Date localDate) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(localDate);
        calendar.add(calendar.DATE, -5);
        Date date = calendar.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }


    /**
     * 某天的零点
     *
     * @param day
     * @return
     */
    public static Date getDayStartTime(Date day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }


    /***
     * 当前时间距离今天结束的秒数间隔
     * @return
     */
    public static int getSecondBeforeDayEnd() {
        long currentMillis = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long endMillis = calendar.getTimeInMillis();
        int secs = (int) ((endMillis - currentMillis) / (1000));
        return secs;
    }

    /**
     * 几天前
     *
     * @param days
     * @return
     */
    public static Date addDays(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, days); //得到前一天
        return calendar.getTime();
    }


    /**
     * 得到当前年份
     *
     * @return
     */
    public static int getYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }


    /**
     * 得到当前时间的小时
     *
     * @return
     */
    public static int getHour() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }


    /**
     * 判断时间是否在某个时间段以内
     *
     * @param dateString 需要判定的时间
     * @param format     时间格式
     * @param DateType   时间单位
     * @param amount     距现在的时间
     * @return
     */
    public static boolean compareDateByString(String dateString, String format, int DateType, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(DateType, amount);
        Date halfYearDate = calendar.getTime();
        return DateUtil.formatFromString(dateString, format).getTime() >= halfYearDate.getTime();
    }

    /**
     * 判断当时间跟传入时间是否在someday内
     *
     * @param thisDay  需要判定的时间
     * @param DateType 时间单位
     * @param someTime 距现在的时间
     * @return
     */
    public static boolean isBeforeNowSomeTime(Object thisDay, int DateType, int someTime) {
        if (thisDay == null) {
            return false;
        }
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String nowDateString = formatter.format(new Date());
            Date nowDate = formatter.parse(nowDateString);
            Calendar calendar = Calendar.getInstance();
            if (thisDay instanceof String) {
                Date thisDate = formatter.parse((String) thisDay);
                calendar.setTime(thisDate);
                calendar.add(DateType, someTime);
            } else if (thisDay instanceof Date) {
                String thisDayString = formatter.format(thisDay);
                calendar.setTime(formatter.parse(thisDayString));
                calendar.add(DateType, someTime);
            }
            return calendar.getTime().getTime() >= (nowDate.getTime());
        } catch (ParseException e) {
            logger.error("date parse exception,date:{}", thisDay);
            return false;
        }
    }

    /**
     * 获取和当前时间指定长度的时间
     *
     * @param DateType 时间单位
     * @param amount   距现在的时间
     * @return
     */
    public static String getDateStringByNow(int DateType, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(DateType, amount);
        Date oldDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(oldDate);
    }


    public static String getDateStrByString(String dateStr, String format) {
        try {
            SimpleDateFormat dateFormat = getDateFormat(format);
            Date date = dateFormat.parse(dateStr);
            return dateFormat.format(date);
        } catch (Exception ex) {
            logger.error("转换时间异常", ex);
        }
        return null;
    }

    /**
     * 获取日期多少月前或者后的日期
     *
     * @param date      日期
     * @param someMonth 离日期多少个月，整数是之后，负数是之前
     * @return
     */
    public static Date getDateBySomeMonth(Date date, int someMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, someMonth);
        return calendar.getTime();
    }

    public static Date parseObjToDate(Object obj) throws ParseException {
        if (obj == null) {
            return null;
        }
        String dateStr = String.valueOf(obj);
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        return dateFormat.parse(dateStr);
    }

    public static boolean isTheSameDay(Date date1, Date date2) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date1String = dateFormat.format(date1);
        String date2String = dateFormat.format(date2);
        return date1String.equals(date2String);
    }

}
