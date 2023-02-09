package com.example.twoactivities0202;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.twoactivities0202.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
    private final static String LOG_TAG = SecondActivity.class.getSimpleName();
    public final static String EXTRA_REPLY = "com.example.twoactivities0202.extra.REPLY";

    private ActivitySecondBinding secondBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");

        secondBinding = ActivitySecondBinding.inflate(getLayoutInflater());

        secondBinding.buttonMain.setOnClickListener(view -> {
            Log.d(LOG_TAG, "Reply button clicked");

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(EXTRA_REPLY, secondBinding.editText.getText().toString());

            setResult(RESULT_OK, intent);
            Log.d(LOG_TAG, "End SecondActivity");
            finish();
        });

        setContentView(secondBinding.getRoot());

        Intent intent = getIntent();
        secondBinding.textView2.setText(intent.getStringExtra(MainActivity.EXTRA_MESSAGE));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }
}