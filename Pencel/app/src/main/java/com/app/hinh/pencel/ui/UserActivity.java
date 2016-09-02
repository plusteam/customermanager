package com.app.hinh.pencel.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.app.hinh.pencel.R;

/**
 * Created by Le on 8/5/2016.
 */
public class UserActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.user_layout);

    }

    public void toMainActivity(View view){
        /*startActivity(new Intent( UserActivity.this,MainActivity.class));
        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_in_bottom);*/
        finish();
    }

}
