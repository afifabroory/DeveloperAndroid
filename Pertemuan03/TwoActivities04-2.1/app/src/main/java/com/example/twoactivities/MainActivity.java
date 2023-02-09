package com.example.twoactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.twoactivities.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private final static String LOG_TAG = MainActivity.class.getSimpleName();
    public final static String EXTRA_MESSAGE = "com.example.twoactivities.extra.MESSAGE";
    public final static int TEXT_REQUEST = 1; // Public constant for particular response type

    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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