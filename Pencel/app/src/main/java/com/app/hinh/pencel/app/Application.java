package com.app.hinh.pencel.app;

import android.support.multidex.MultiDexApplication;

import com.app.hinh.pencel.network.APIServer;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Le on 8/5/2016.
 */
public class Application extends MultiDexApplication {
    public static Retrofit mRetrofit;
    public static final String BASE_API = "http://9c43352d.ngrok.io/";
    public static APIServer APIServer;


    @Override
    public void onCreate() {
        super.onCreate();
        mRetrofit = new Retrofit.Builder().addConverterFactory(ScalarsConverterFactory.create()).baseUrl(BASE_API).addConverterFactory(GsonConverterFactory.create()).build();
        APIServer = mRetrofit.create(APIServer.class);
    }
}
