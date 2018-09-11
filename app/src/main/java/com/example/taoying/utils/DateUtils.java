package com.example.taoying.utils;

/**
 * Created by TaoYing on 2018/3/29.
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static SimpleDateFormat sdf = null;
    /*获取系统时间 格式为："yyyy/MM/dd "*/
    public static String getCurrentDate() {
        Date d = new Date();
        sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return sdf.format(d);
    }

    /*时间戳转换成字符窜*/
    public static String getDateToString(long time) {
        Date d = new Date(time);
        sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return sdf.format(d);
    }

    /*将字符串转为时间戳*/
    public static long getStringToDate(String time) {
        sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date();
        try{
            date = sdf.parse(time);
        } catch(ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static String getDiffToString(long diff) {
        long days = diff / (1000 * 60 * 60 * 24);
        long hours = (diff - days*(1000 * 60 * 60 * 24)) / (1000* 60 * 60);
        long minutes = (diff - days*(1000 * 60 * 60 * 24) - hours*(1000* 60 * 60)) / (1000* 60);
        long seconds = (diff - days*(1000 * 60 * 60 * 24) - hours*(1000* 60 * 60) - minutes*(1000 * 60)) / (1000);

        return "" + days + "天" + hours + "小时" + minutes + "分" + seconds + " 秒";
    }
    public static long CalcDiffLong(String date1,String date2){
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            if(date1.length() < 19)
                date1 += ":00";
            Date d1 = sdf.parse(date1);
            Date d2 = sdf.parse(date2);
//            Date d2 = new Date(System.currentTimeMillis());//你也可以获取当前时间
            long diff = d1.getTime() - d2.getTime();//这样得到的差值是微秒级别

            return diff;
        }
        catch (Exception e)
        {
            return 0;
        }
    }

    public static String CalcDiffStr(String date1,String date2){
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            Date d1 = sdf.parse(date1);
//            Date d2 = sdf.parse(date2);
            Date d2 = new Date(System.currentTimeMillis());//你也可以获取当前时间
            long diff = d1.getTime() - d2.getTime();//这样得到的差值是微秒级别
            long days = diff / (1000 * 60 * 60 * 24);
            long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
            long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);

            return ""+days+"天"+hours+"小时"+minutes+"分";
        }
        catch (Exception e)
        {
            return "error";
        }
    }

    /*
     * 毫秒转化时分秒毫秒
     */
    public static String formatTime(Long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        StringBuffer sb = new StringBuffer();
        if(day > 0) {
            sb.append(day+"天");
        }
        if(hour > 0) {
            sb.append(hour+"小时");
        }
        if(minute > 0) {
            sb.append(minute+"分");
        }
        if(second > 0) {
            sb.append(second+"秒");
        }
        if(milliSecond > 0) {
            sb.append(milliSecond+"毫秒");
        }
        return sb.toString();
    }
}
