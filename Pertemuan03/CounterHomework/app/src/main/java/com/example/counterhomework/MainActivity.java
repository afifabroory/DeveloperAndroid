package com.example.counterhomework;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.counterhomework.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mainBinding;
    private int mCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(mainBinding.getRoot());
        mainBinding.btnCounter.setOnClickListener(v -> {
            mCounter++;
            mainBinding.counter.setText(String.format("%s", mCounter));
        });

        if (savedInstanceState != null) {
            mCounter = savedInstanceState.getInt("mCounter");
            mainBinding.counter.setText(String.format("%s", mCounter));
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("mCounter", mCounter);
    }
}