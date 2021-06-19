package com.example.stackcodes.boss.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.stackcodes.boss.R;

public class Splash_Activity extends AppCompatActivity {
    private final int SPLASH_TIMEOUT = 2000;

    @Override
    protected void onStart() {

        new Handler ().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent (Splash_Activity.this, MainActivity.class));
                finish();
            }
        }, SPLASH_TIMEOUT);
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_splash);
    }
}
