package com.innovate.modules.innovate.utils;

import org.joda.time.DateTime;
import org.joda.time.Months;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author:tz
 * @create:2018-11-13
 * @description:月份差
 **/
public class subtractionMonthUtil {
    public static int subMonth(String startTime,String endTime) {
        //System.out.println(startTime);
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime start = formatter.parseDateTime(TimeStampToDate(startTime));
        DateTime end = formatter.parseDateTime(TimeStampToDate(endTime));
        int months = Months.monthsBetween(start, end).getMonths();
        return months;
    }

    /**
     * 时间戳转换成日期格式字符串
     * @param seconds
     * @return
     */
    public static String TimeStampToDate(String seconds){

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String a =df.format(new Date(seconds));
        //System.out.println(a);
        return a;

    }

/*    public static void main(String[] args){

        int month = subMonth("Thu Sep 08 19:33:45 CST 2018","Thu Nov 08 19:33:45 CST 2018");
        System.out.println(month);
    }*/
}
