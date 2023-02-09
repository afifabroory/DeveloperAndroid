package com.example.twoactivities0202;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.twoactivities0202.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private final static String LOG_TAG = MainActivity.class.getSimpleName();
    public final static String EXTRA_MESSAGE = "com.example.twoactivities0202.extra.MESSAGE";
    public final static int TEXT_REQUEST = 1; // Public constant for particular response type

    private ActivityMainBinding mainBinding;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mainBinding.textHeaderReply.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible", true);
            outState.putString("reply_message", mainBinding.textView2.getText().toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");

        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        // Send button onClickListener
        mainBinding.buttonMain.setOnClickListener(view -> {
            Log.d(LOG_TAG, "Send button clicked");
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra(EXTRA_MESSAGE, mainBinding.editText.getText().toString());

            // ToDo: Migrate activity intent to newer version
            // https://developer.android.com/training/basics/intents/result
            startActivityForResult(intent, TEXT_REQUEST);
        });

        setContentView(mainBinding.getRoot());

        if (savedInstanceState != null) {
            if (savedInstanceState.getBoolean("reply_visible", false)) {
                mainBinding.textView2.setVisibility(View.VISIBLE);
                mainBinding.textHeaderReply.setVisibility(View.VISIBLE);

                mainBinding.textView2.setText(savedInstanceState.getString("reply_message"));
            }
        }
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

    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                mainBinding.textView2.setText(data.getStringExtra(SecondActivity.EXTRA_REPLY));
                mainBinding.textView2.setVisibility(View.VISIBLE);
                mainBinding.textHeaderReply.setVisibility(View.VISIBLE);
            }
        }
    }
}