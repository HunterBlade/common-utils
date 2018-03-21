package com.comn;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.apache.commons.lang3.time.DateUtils.MILLIS_PER_DAY;

/**
 * 日期辅助工具类<br>
 * 常用的日期方法参考：commons-lang3包中的DateUtils、DateFormatUtils类
 */
public class DateHelper {

    private static final String DATE_PATTERN = "yyyy-MM-dd"; //日期格式（不带时间）

    private static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss"; //日期格式（带时间）

    private static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    private static String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 获取指定日期的中文星期数
     *
     * @param date 指定日期
     * @return 星期数，如：星期一
     */
    public static String getWeekStr(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(7);
        --week;
        String weekStr = "";
        switch (week) {
            case 0:
                weekStr = "星期日";
                break;
            case 1:
                weekStr = "星期一";
                break;
            case 2:
                weekStr = "星期二";
                break;
            case 3:
                weekStr = "星期三";
                break;
            case 4:
                weekStr = "星期四";
                break;
            case 5:
                weekStr = "星期五";
                break;
            case 6:
                weekStr = "星期六";
        }
        return weekStr;
    }

    /**
     * 获取两个日期相隔的天数
     *
     * @param beginDate
     * @param endDate
     * @return 相差的天数，如果大的日期在前面则返回的日期为负数
     */
    public static int getDayDiff(Date beginDate, Date endDate) {
        if (beginDate == null || endDate == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        long time1 = beginDate.getTime();
        long time2 = endDate.getTime();

        long diff = time2 - time1;
        Long longValue = new Long(diff / MILLIS_PER_DAY);
        return longValue.intValue();
    }

}
