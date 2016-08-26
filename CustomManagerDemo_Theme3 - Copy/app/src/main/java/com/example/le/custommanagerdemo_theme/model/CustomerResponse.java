
package com.example.le.custommanagerdemo_theme.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class CustomerResponse {

    @SerializedName("data")
    @Expose
    private List<Customer> data = new ArrayList<Customer>();

    /**
     * 
     * @return
     *     The data
     */
    public List<Customer> getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    public void setData(List<Customer> data) {
        this.data = data;
    }

}
