package com.app.hinh.pencel.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hinh1 on 9/5/2016.
 */
public class CustomerNotification {
    @SerializedName("customer_id")
    @Expose
    private int customerID;
    @SerializedName("datetime")
    @Expose
    private String dateTime;
    @SerializedName("note")
    @Expose
    private String note;

    public CustomerNotification() {
    }

    public CustomerNotification(int customerID, String dateTime, String note) {
        this.customerID = customerID;
        this.dateTime = dateTime;
        this.note = note;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
