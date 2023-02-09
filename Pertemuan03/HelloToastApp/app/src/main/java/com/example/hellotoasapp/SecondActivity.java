package com.example.hellotoasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        int data = intent.getIntExtra(MainActivity.EXTRA_COUNT, 0);

        textView = (TextView) findViewById(R.id.show_count_second);
        textView.setText(Integer.toString(data));
    }
}