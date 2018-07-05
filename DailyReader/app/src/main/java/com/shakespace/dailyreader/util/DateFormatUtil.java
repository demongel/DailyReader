package com.shakespace.dailyreader.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by shakespace on 2018/3/26.
 */

public class DateFormatUtil {

    // 知乎api  传入20180328 得到的是20180327的结果 要把当前的加上一天
    public static String formatZhihuDailyDateLongToString(long date) {
        String sDate;
        Date d = new Date(date + 24*60*60*1000);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        sDate = format.format(d);

        return sDate;
    }

    // 传入date 返回普通时间
    public static long formatZhihuDailyDateStringToLong(String date) {
        Date d = null;
        try {
            d = new SimpleDateFormat("yyyyMMdd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d == null ? 0 : d.getTime();
    }

    // FIXME 时间的逻辑还需要优化一下
    // 知乎的api 查询0330 得到的是0329，从数据库读取时需要往前一天
    public static String getZhihuPreviousDay(String date){
        long time = DateFormatUtil.formatZhihuDailyDateStringToLong(date);
        return DateFormatUtil.formatZhihuDailyDateLongToString(time-36*60*60*1000);
    }
}
