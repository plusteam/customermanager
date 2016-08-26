package com.example.le.custommanagerdemo_theme.common;

import java.util.ArrayList;

/**
 * Created by Henry on 05-Aug-16.
 */
public class ConstantU {
    public static final String COLUMN_NAME = "Họ và tên";
    public static final String COLUMN_ADDRESS = "Địa chỉ";
    public static final String COLUMN_PHONE_NUMBER = "Số điện thoại";
    public static final String COLUMN_COMPANY = "Công ty";
    public static final String COLUMN_PROJECT = "Dự án";
    public static final String COLUMN_EMAIL = "Email";
    public static final String COLUMN_FIRST_CONTACT = "Liên hệ lần đầu";
    public static final String COLUMN_NEXT_CONTACT = "Liên hệ lần tiếp theo";
    public static final String COLUMN_RATING = "Đánh giá";
    public static final String COLUMN_CUSTOMER_TYPE = "Loại khách hàng";
    public static final String COLUMN_DEMAND = "Nhu cầu";
    public static final String COLUMN_AGENCY = "Nguồn khách";
    public static final String COLUMN_NOTE = "Ghi chú";
    public static final String COLUMN_OTHER = "Khác";

    public ArrayList<String> columns() {
        ArrayList<String> arrColumns = new ArrayList<>();
        arrColumns.add(COLUMN_NAME);
        arrColumns.add(COLUMN_ADDRESS);
        arrColumns.add(COLUMN_PHONE_NUMBER);
        arrColumns.add(COLUMN_COMPANY);
        arrColumns.add(COLUMN_PROJECT);
        arrColumns.add(COLUMN_EMAIL);
        arrColumns.add(COLUMN_FIRST_CONTACT);
        arrColumns.add(COLUMN_NEXT_CONTACT);
        arrColumns.add(COLUMN_RATING);
        arrColumns.add(COLUMN_CUSTOMER_TYPE);
        arrColumns.add(COLUMN_DEMAND);
        arrColumns.add(COLUMN_AGENCY);
        arrColumns.add(COLUMN_NOTE);
        arrColumns.add(COLUMN_OTHER);
        return arrColumns;
    }

}
