package com.example.javaapplication.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.javaapplication.MainActivity;
import com.example.javaapplication.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences editor = this.getSharedPreferences("Credential", Context.MODE_PRIVATE);
        Boolean isFirstTime = editor.getBoolean("first_time",true);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(isFirstTime) {
                    Intent mainIntent = new Intent(SplashScreen.this, Registration.class);
                    startActivity(mainIntent);
                }else{
                    Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(mainIntent);
                }
                finish();
            }
        }, 3000);
    }
}