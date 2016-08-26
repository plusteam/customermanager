package com.example.le.custommanagerdemo_theme.network;

import android.util.Log;

import com.example.le.custommanagerdemo_theme.model.Application;
import com.example.le.custommanagerdemo_theme.model.CustomerResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Dung-DamDe-DeTien on 13/08/2016.
 */
public class CustomerAPI {
    public static void getCustomer(Callback<CustomerResponse> callback, int id) {
        Call<CustomerResponse> callGetCus = Application.APIServer.getCurtomers(id);
        callGetCus.enqueue(callback);
    }

    public static void sendCustomer(String message){
        Call<String> call = Application.APIServer.sendCustomer(message);

        try {
            Response<String> response = call.execute();
            String value = response.body();
            Log.d("aa",value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
