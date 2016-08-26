package com.example.le.custommanagerdemo_theme.model;

import android.support.multidex.MultiDexApplication;

import com.example.le.custommanagerdemo_theme.network.APIServer;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Le on 8/5/2016.
 */
public class Application extends MultiDexApplication {
    public static Retrofit mRetrofit;
    public static final String BASE_API = "http://1832d2e2.ngrok.io/";
    public static APIServer APIServer;


      public void onCreate() {
        super.onCreate();
          mRetrofit = new Retrofit.Builder().addConverterFactory(ScalarsConverterFactory.create()).baseUrl(BASE_API).addConverterFactory(GsonConverterFactory.create()).build();
          APIServer = mRetrofit.create(APIServer.class);
    }
}
