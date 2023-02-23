package com.seeksolution.ottplateformdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //hide the Action Bar
        getSupportActionBar().hide();

        //Handler
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Retrofit Api call

                Intent intent = new Intent(SplashScreen.this,Register.class);
                startActivity(intent);
                finish();

            }
        },5000); //5 Thousand Milli-seconds means => 5 seconds.


    }
}