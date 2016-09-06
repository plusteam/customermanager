package com.app.hinh.pencel.service;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;

import com.app.hinh.pencel.broadcast.NotificationReceiver;
import com.app.hinh.pencel.util.StringUtil;

public class NotificationService extends Service {
    private NotificationManager notificationManager;
    private String[] dateTime; //yyyy-mm-dd hh:mm:ss
    private IntentFilter intentFilter;
    private Runnable mRunnable;
    private NotificationReceiver broadcastReceiver;
    private Handler handler;
    private StringUtil stringUtil;
    private String date;
    private String time;


    public NotificationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        broadcastReceiver = new NotificationReceiver();
        intentFilter = new IntentFilter();
        stringUtil = new StringUtil();
        intentFilter.addAction("CUSTOMER_NOTIFICATION");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mRunnable = new Runnable() {
            @Override
            public void run() {

            }
        };

        handler.postDelayed(mRunnable, 1000);
        return START_STICKY;
    }

    public String getNearlyTime(){
        String nearlyTime= "";


        return nearlyTime;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
