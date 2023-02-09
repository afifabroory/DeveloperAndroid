package com.example.twoactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.twoactivities.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private final static String LOG_TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        // Send button onClickListener
        mainBinding.buttonMain.setOnClickListener(view -> {
            Log.d(LOG_TAG, "Send button clicked");

            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        });

        setContentView(mainBinding.getRoot());
    }
}