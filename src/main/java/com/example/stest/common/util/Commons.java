package com.example.stest.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Commons {
    //计算年龄方法
    public static String getAge(String birthDay,String endDay) throws Exception {
        Date fromDate = null;
        try {
            fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthDay);
        } catch (Exception e) {
            fromDate = new SimpleDateFormat("yyyy/MM/dd").parse(birthDay);
        }
        Date toDate = null;
        try {
            toDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDay);
        } catch (Exception e) {
            toDate = new SimpleDateFormat("yyyy/MM/dd").parse(endDay);
        }
        if(fromDate!=null && toDate!=null) {
            Calendar cal = Calendar.getInstance();
            //设置出生日期
            cal.setTime(fromDate);

            int yearBirth = cal.get(Calendar.YEAR);
            int monthBirth = cal.get(Calendar.MONTH);
            int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

            //设置到哪天计算出年龄
            cal.setTime(toDate);
            if (cal.before(birthDay)) {
                throw new IllegalArgumentException(
                        "The birthDay is before Now.It's unbelievable!");
            }
            int yearNow = cal.get(Calendar.YEAR);
            int monthNow = cal.get(Calendar.MONTH);
            int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

            int age = yearNow - yearBirth;

            if (monthNow <= monthBirth) {
                if (monthNow == monthBirth) {
                    if (dayOfMonthNow < dayOfMonthBirth) age--;
                }else{
                    age--;
                }
            }
            return Integer.toString(age);
        }else {
            return null;
        }

    }
}
