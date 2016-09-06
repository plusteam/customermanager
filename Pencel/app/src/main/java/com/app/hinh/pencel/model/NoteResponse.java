package com.app.hinh.pencel.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hinh1 on 9/5/2016.
 */
public class NoteResponse  {
    @SerializedName("data")
    @Expose
    private List<CustomerNotification> data = new ArrayList<CustomerNotification>();

    /**
     *
     * @return
     *     The data
     */

    public List<CustomerNotification> getData() {
        return data;
    }

    /**
     *
     *
     * @param data
     *     The data
     */

    public void setData(List<CustomerNotification> data) {
        this.data = data;
    }
 }
