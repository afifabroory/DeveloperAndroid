package com.example.hellocompat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;

import com.example.hellocompat.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mainBinding;
    private String[] mColorArray = {"red", "pink", "purple", "deep_purple",
            "indigo", "blue", "light_blue", "cyan", "teal", "green",
            "light_green", "lime", "yellow", "amber", "orange", "deep_orange",
            "brown", "grey", "blue_grey", "black" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        mainBinding.colorButton.setOnClickListener(v -> {
            Random rand = new Random();

            String colorName = mColorArray[rand.nextInt(20)];
            int resourceName = getResources().getIdentifier(colorName, "color", getApplicationContext().getPackageName());
            int colorRes = ContextCompat.getColor(this, resourceName);
            mainBinding.text.setTextColor(colorRes);
        });

        if (savedInstanceState != null) {
            mainBinding.text.setTextColor(savedInstanceState.getInt("color"));
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("color", mainBinding.text.getCurrentTextColor());
    }
}