package com.example.implicitintentreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.implicitintentreceiver.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        Intent intent = getIntent();
        Uri uri = intent.getData();

        if (uri != null) {
            String uriString = "URI: " + uri.toString();
            mainBinding.textUriMessage.setText(uriString);
        }
    }
}