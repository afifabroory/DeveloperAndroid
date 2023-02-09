package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "Hello World from onCreate()!");

        // For testing purpose
        double n;
        try {
            n = 20/0;
        } catch (Exception e) {
            Log.e("MainActivity", "Pembagi tidak boleh 0");
        }
    }
}