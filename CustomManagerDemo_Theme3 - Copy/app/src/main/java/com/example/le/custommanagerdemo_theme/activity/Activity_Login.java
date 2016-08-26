package com.example.le.custommanagerdemo_theme.activity;

import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.le.custommanagerdemo_theme.R;

public class Activity_Login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wellcome_layout);
        MultiDex.install(this);
    }

}
