package com.example.le.custommanagerdemo_theme.network;

import com.example.le.custommanagerdemo_theme.model.AccountResponse;
import com.example.le.custommanagerdemo_theme.model.CustomerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by hinh1 on 8/5/2016.
 */
public interface APIServer {


    //Account
    @GET("accounts/{email}/")
    Call<AccountResponse> getAccount(@Path("email") String email);

    @Headers("Content-Type: text/plain")
    @POST("create/account/")
    Call<String> send(@Body String account);
    //customer
    @GET("customersmanager/{id}/")
    Call<CustomerResponse> getCurtomers(@Path("id") int accountId);


    @Headers({"Content-Type: text/plain"
           }
    )
    @POST("create/customer/")
    Call<String> sendCustomer(@Body String account);


}
