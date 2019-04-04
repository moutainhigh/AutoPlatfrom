package com.gs.common.bean;

import com.gs.common.util.DateFormatUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author xiao-kang
 * @date 2017/5/8
 */
public class HighchartsData {
    public  static final String[] strDay = new String[]{"01","02","03","04","05","06","07","08","09",
            "10","11","12","13","14","15","16","17","18","19","20",
            "21","22","23","24","25","26","27","28","29","30","31"};

    public  static final String[] strQuarter = new String[]{"第一季度","第二季度","第三季度","第四季度"};

    public  static final  String[] strMonth = new String[]{"01月","02月","03月","04月","05月","06月","07月","08月","09月",
            "10月","11月","12月"};

    public  static  String[] strYear;
    public  static String[] strWeek;

    public  static int weekLen;
    public  static  int yearLen;

    public  static double[] doubleYearOne;
    public  static double[] doubleYearTwo;

    public  static double[] doubleWeekOne;
    public  static double[] doubleWeekTwo;

    public  static double[] doubleMonthOne = new double[12];
    public  static double[] doubleMonthTwo = new double[12];

    public  static double[] doubleDayOne = new double[31];
    public  static double[] doubleDayTwo = new double[31];

    public  static double[] doubleQuarterOne = new double[4];
    public  static double[] doubleQuarterTwo = new double[4];

    public  static int len;

    public static String dateFormat(Date date, String type){
        String str = DateFormatUtil.defaultFormat(date);
        String time = null;
        if("day".equals(type)){
            time = str.substring(8,10);
        }else if("month".equals(type)){
            time = str.substring(5,8);
        }else if("quarter".equals(type)){
            time = str.substring(5,7);
        }else if("year".equals(type)){
            time = str.substring(0,4);
        }
        return time;
    }

    public static String dateFormat(Date date){
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    // 获取周报表数据
    public static void getWeek(String[] strs,double[] doubles,String species) {
        for (int j = 0; j < len; j++) {
            for (int k = 0; k < strs.length; k++) {
                if (strWeek[j].equals(strs[k])) {
                    if (species.equals("one")) {
                        doubleWeekOne[j] = doubles[k];
                    } else if (species.equals("two")) {
                        doubleWeekTwo[j] = doubles[k];
                    }
                }
            }
        }
    }

    // 获取年报表数据
    public static void getYear(String[] strs,double[] doubles,String species) {
        for (int j = 0; j < len; j++) {
            for (int k = 0; k < strs.length; k++) {
                if (strYear[j].equals(strs[k])) {
                    if (species.equals("one")) {
                        doubleYearOne[j] = doubles[k];
                    } else if (species.equals("two")) {
                        doubleYearTwo[j] = doubles[k];
                    }
                }
            }
        }
    }

    // 获取季度报表数据
    public static void getQuarter(String[] strs,double[] doubles,String species){
        for(int j = 0; j < len; j++) {
            for (int k = 0; k < strs.length; k++) {
                if (strs[k].equals("01") || strs[k].equals("02") || strs[k].equals("03")) {
                    if (species.equals("one")) {
                        doubleQuarterOne[0] = doubles[k];
                    }
                } else if (strs[k].equals("04") || strs[k].equals("05") || strs[k].equals("06")) {
                    if (species.equals("one")) {
                        doubleQuarterOne[1] = doubles[k];
                    } else if (species.equals("two")) {
                        doubleQuarterTwo[1] = doubles[k];
                    }
                } else if (strs[k].equals("07") || strs[k].equals("08") || strs[k].equals("09")) {
                    if (species.equals("one")) {
                        doubleQuarterOne[2] = doubles[k];
                    } else if (species.equals("two")) {
                        doubleQuarterTwo[2] = doubles[k];
                    }
                } else if (strs[k].equals("10") || strs[k].equals("11") || strs[k].equals("12")) {
                    if (species.equals("one")) {
                        doubleQuarterOne[3] = doubles[k];
                    } else if (species.equals("two")) {
                        doubleQuarterTwo[3] = doubles[k];
                    }
                }
            }

        }
    }

    // 获取月报表数据
    public static void getMonth(String[] strs,double[] doubles,String species) {
        for (int j = 0; j < len; j++) {
            for (int k = 0; k < strs.length; k++) {
                if (strMonth[j].equals(strs[k])) {
                    if (species.equals("one")) {
                        doubleMonthOne[j] = doubles[k];
                    } else if (species.equals("two")) {
                        doubleMonthTwo[j] = doubles[k];
                    }
                }
            }
        }
    }

    // 获取日报表数据
    public  static void getDay(String[] strs,double[] doubles,String species) {
        for (int j = 0; j < len; j++) {
            for (int k = 0; k < strs.length; k++) {
                if(strDay[j].equals(strs[k])){
                    if(species.equals("one")){
                        doubleDayOne[j] = doubles[k];
                    }else if(species.equals("two")){
                        doubleDayTwo[j] = doubles[k];
                    }
                }
            }
        }
    }

    /*
    *
    * 获取选中的时间是本年的第几周
    * */
    public static int getWeek(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekOfMonth = calendar.get(Calendar.WEEK_OF_YEAR);
        return weekOfMonth;
    }


    /*
    * 获取选择的开始年和结束年的所有
    * */
    public static void setStrYear(String start,String end){
        String strStartTime = start.substring(0,4);
        String strEndTime = end.substring(0,4);
        int startTime = Integer.valueOf(strStartTime);
        int endTime = Integer.valueOf(strEndTime);
        yearLen =  endTime - startTime+1;
        strYear = new String[yearLen];
        for(int i = 0; i < strYear.length; i++){
            strYear[i] = String.valueOf(startTime);
            startTime++;
        }
    }
    /*
       * 获取选择的开始周和结束周的所有
       * */
    public static void setStrWeek(String start,String end){
        int starkWeek =  getWeek(start);
        int endWeek = getWeek(end);
        weekLen =  endWeek - starkWeek+1;
        strWeek = new String[weekLen];
        for(int i = 0; i < strWeek.length; i++){
            strWeek[i] = "第"+ String.valueOf(starkWeek) + "周";
            starkWeek++;
        }

    }

    public static String stdayDate(){
        // 获取当月第一天和最后一天
        Calendar cale = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 获取前月的第一天
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return format.format(cale.getTime());
    }

    public static String lastDate(){
        // 获取前月的最后一天
        Calendar cale = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        return format.format(cale.getTime());
    }
}
