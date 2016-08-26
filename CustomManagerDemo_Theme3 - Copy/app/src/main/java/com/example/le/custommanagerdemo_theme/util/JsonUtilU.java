package com.example.le.custommanagerdemo_theme.util;

import android.util.Log;

import com.example.le.custommanagerdemo_theme.model.CustomerU;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class JsonUtilU {
    private CustomerU columns;
    private String id;
    public JsonUtilU(String id) {
        columns = new CustomerU();
        this.id=id;

    }

    public String getJson(ArrayList<CustomerU> arrColumns) {
        String jsonS = "";
        Log.d("Kinh",this.id);
        for (int i = 0; i < arrColumns.size(); i++) {

            columns.setAccountID(this.id);
            Log.d("accountID",columns.getAccountID());
            columns.setName(arrColumns.get(i).getName());
            columns.setAddress(arrColumns.get(i).getAddress());
            columns.setPhoneNumber(arrColumns.get(i).getPhoneNumber());
            columns.setCompany(arrColumns.get(i).getCompany());
            columns.setProject(arrColumns.get(i).getProject());
            columns.setEmail(arrColumns.get(i).getEmail());
            columns.setAgency(arrColumns.get(i).getAgency());
            columns.setDemand(arrColumns.get(i).getDemand());
            columns.setFirstContact(arrColumns.get(i).getFirstContact());
            columns.setNextContact(arrColumns.get(i).getNextContact());
            columns.setRating(arrColumns.get(i).getRating());
            columns.setCustomerType(arrColumns.get(i).getCustomerType());
            columns.setNote(arrColumns.get(i).getNote());
            columns.setOther(arrColumns.get(i).getOther());
        }

        Gson gson = new Gson();
        Type type = new TypeToken<List<CustomerU>>() {
        }.getType();
        String json = gson.toJson(arrColumns, type);
        List<CustomerU> fromJson = gson.fromJson(json, type);
        for (CustomerU columns : fromJson) {
            jsonS += columns;
        }
        jsonS = "{\"data\":" + json + "}";
        return jsonS;
    }
}

