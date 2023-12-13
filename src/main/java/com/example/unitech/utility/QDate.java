package com.example.unitech.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class QDate {
    public static String getCurrentYear() {
        String st = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        if (st.length() == 1) {
            st = "0" + st;
        }
        return st;
    }

    public static String getCurrentHour() {
        String st = String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
        if (st.length() == 1) {
            st = "0" + st;
        }
        return st;
    }

    public static String getCurrentMinute() {
        String st = String.valueOf(Calendar.getInstance().get(Calendar.MINUTE));
        if (st.length() == 1) {
            st = "0" + st;
        }
        return st;
    }

    public static String getCurrentMillisecond() {
        String st = String.valueOf(Calendar.getInstance().get(Calendar.MILLISECOND));

        String line = "";
        for (int i = st.length() + 1; i <= 4; i++) {
            line += "0";
        }
        line += st;
        return line;
    }

    public static String getCurrentSecond() {
        String st = String.valueOf(Calendar.getInstance().get(Calendar.SECOND));
        if (st.length() == 1) {
            st = "0" + st;
        }
        return st;
    }

    public static String getCurrentMonth() {
        String stMonth = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
        if (stMonth.length() == 1) {
            stMonth = "0" + stMonth;
        }
        return stMonth;
    }

    public static String getCurrentDay() {
        String stDay = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        if (stDay.length() == 1) {
            stDay = "0" + stDay;
        }

        return stDay;
    }

    public static String getCurrentDate() {
        Date date = new Date();
        return convertDateToString(date);
    }

    public static Long getCurrentDateLong() {
        Date date = new Date();
        return Long.parseLong(convertDateToString(date));
    }

    public static String getCurrentTime() {
        String st = getCurrentHour() + getCurrentMinute() + getCurrentSecond();
        return st;
    }

    public static Long getCurrentTimeLong() {
        String st = getCurrentHour() + getCurrentMinute() + getCurrentSecond();
        return Long.parseLong(st);
    }

    public static Long getDateTimeLong() {
        String st = getCurrentDate() + getCurrentHour() + getCurrentMinute() + getCurrentSecond();
        return Long.parseLong(st);
    }

    public static String convertDateToString(Date dt) {
        String stYear = Integer.toString(dt.getYear() + 1900);
        String stMonth = Integer.toString(dt.getMonth() + 1);
        if (stMonth.length() == 1) {
            stMonth = "0" + stMonth;
        }
        String stDay = Integer.toString(dt.getDate());
        if (stDay.length() == 1) {
            stDay = "0" + stDay;
        }

        return stYear + stMonth + stDay;
    }

    public static String convertTimeToString(Date dt) {
        String hours = Integer.toString(dt.getHours());
        String minutes = Integer.toString(dt.getMinutes());
        String seconds = Integer.toString(dt.getSeconds());

        hours = hours.length() == 1 ? "0" + hours : hours;
        minutes = minutes.length() == 1 ? "0" + minutes : minutes;
        seconds = seconds.length() == 1 ? "0" + seconds : seconds;

        return hours + minutes + seconds;
    }

    public static String convertToDateString(String st) {
        String r = "";
        try {
            r = st.substring(6, st.length()) + "." + st.substring(4, 6) + "." + st.substring(0, 4);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    public static String convertToTimeString(String st) {
        String r = "";
        try {
            r = st.substring(0, 2) + ":" + st.substring(2, 4) + ":" + st.substring(4, 6);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    public static Date convertStringToDate(String st) {
        Date date1 = null;
        String separator = "/";

        try {
            String str_date = st.substring(6, 8) + separator + st.substring(4, 6) + separator + st.substring(0, 4);
            DateFormat formatter;

            formatter = new SimpleDateFormat("dd/MM/yyyy");
            date1 = (Date) formatter.parse(str_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;

    }

    public static Date convertStringToDate(String st, String time) {
        Date date1 = null;
        String separator = "/";
        String separatorTime = ":";

        try {
            String str_date = st.substring(6, 8) + separator + st.substring(4, 6) + separator + st.substring(0, 4);

            String hour = time.substring(0, 2);
            String minute = time.substring(2, 4);
            String second = time.substring(4, 6);
            String tm = hour + separatorTime + minute + separatorTime + second;

            DateFormat formatter;
            formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            date1 = (Date) formatter.parse(str_date + " " + tm);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;

    }

    public static Date convertStringToTime(String st) {
        Date date = new Date();
        String hour = st.substring(0, 2);
        String minute = st.substring(2, 4);
        String second = st.substring(4, 6);

        date.setHours(Integer.valueOf(hour));
        date.setMinutes(Integer.valueOf(minute));
        date.setSeconds(Integer.valueOf(second));

        return date;

    }

    public static Date add(Date date, int days) {
        Calendar c;
        c = new GregorianCalendar();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        Date d = c.getTime();
        return d;
    }

    public static Date addDay(Date date, int days) {
        Calendar c;
        c = new GregorianCalendar();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        Date d = c.getTime();
        return d;
    }

    public static Date addHour(Date date, int hours) {
        Calendar c;
        c = new GregorianCalendar();
        c.setTime(date);
        c.add(Calendar.HOUR, hours);
        Date d = c.getTime();
        return d;
    }

    public static Date addMinute(Date date, int minutes) {
        Calendar c;
        c = new GregorianCalendar();
        c.setTime(date);
        c.add(Calendar.MINUTE, minutes);
        Date d = c.getTime();
        return d;
    }

    public static Date addSecond(Date date, int seconds) {
        Calendar c;
        c = new GregorianCalendar();
        c.setTime(date);
        c.add(Calendar.SECOND, seconds);
        Date d = c.getTime();
        return d;
    }

    public static Date addMonth(Date date, int days) {
        Calendar c;
        c = new GregorianCalendar();
        c.setTime(date);
        c.add(Calendar.MONTH, days);
        Date d = c.getTime();
        return d;
    }

    public static Date addYear(Date date, int days) {
        Calendar c;
        c = new GregorianCalendar();
        c.setTime(date);
        c.add(Calendar.YEAR, days);
        Date d = c.getTime();
        return d;
    }

    public static Date add(String date, int days) {
        return add(QDate.convertStringToDate(date), days);
    }

    public static boolean isLessThanToday(String date) {
        Date compDate = QDate.convertStringToDate(date);
        Date today = QDate.convertStringToDate(QDate.getCurrentDate());
        return today.after(compDate);
    }

    public static boolean isGreateThanToday(String date) {
        Date compDate = QDate.convertStringToDate(date);
        Date today = QDate.convertStringToDate(QDate.getCurrentDate());
        return compDate.after(today);
    }

    public static boolean isToday(String date) {
        Date compDate = QDate.convertStringToDate(date);
        Date today = QDate.convertStringToDate(QDate.getCurrentDate());
        return compDate.equals(today);
    }

    public static long getDifferenceInDay(Date date1, Date date2) {
        TimeUnit timeUnit = TimeUnit.DAYS;
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    public static long getDifferenceInHours(Date date1, Date date2) {
        TimeUnit timeUnit = TimeUnit.HOURS;
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    public static long getDifferenceInMinutes(Date date1, Date date2) {
        TimeUnit timeUnit = TimeUnit.MINUTES;
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    public static long getDifferenceInSeconds(Date date1, Date date2) {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }
}
