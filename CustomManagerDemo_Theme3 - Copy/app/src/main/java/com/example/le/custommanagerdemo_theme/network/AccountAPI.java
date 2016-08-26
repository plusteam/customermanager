package com.example.le.custommanagerdemo_theme.network;

import com.example.le.custommanagerdemo_theme.model.AccountResponse;
import com.example.le.custommanagerdemo_theme.model.Application;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hinh1 on 8/6/2016.
 */
public class AccountAPI {



    public static void sendAccount(String message) {

        Call<String> call = Application.APIServer.send(message);

        try {
            Response<String> response = call.execute();
            String value = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }

/*
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("Login","OnResponse:"+response.body());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }


        });*/
    }



    public static void getAccount(Callback<AccountResponse> callback, String email) {
        Call<AccountResponse> callGetCus = Application.APIServer.getAccount(email);
        callGetCus.enqueue(callback);

    }
}
