package com.app.hinh.pencel.util;

/**
 * Created by hinh1 on 9/5/2016.
 */
public class StringUtil {
    private String date;
    private String time;

    public String getDate() {

        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public StringUtil() {

    }

    public void dateTimeUtil(String dateTime) {
        dateTime = dateTime.trim();
        int i = 0;
        int spaceIndex = 0;

        while (i < dateTime.length()) {
            String temp = String.valueOf(dateTime.charAt(i));
            if (temp.equals(" ")) {
                spaceIndex = i;
            }
            i++;
        }
        date = dateTime.substring(0, spaceIndex - 1);
        time = dateTime.substring(spaceIndex + 1, dateTime.length() - 1);


    }
}
