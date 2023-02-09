package com.example.implicitintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.implicitintent.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private final String LOG_TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        mainBinding.btnUrl.setOnClickListener(v -> {
            String url = mainBinding.url.getText().toString();
            Uri webpage = Uri.parse(url);

            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

            if (intent.resolveActivity(getPackageManager()) != null)
                startActivity(intent);
            else
                Log.d(LOG_TAG, "Can't handle open a webpage");
        });

        mainBinding.btnLocation.setOnClickListener(v -> {
            String loc = mainBinding.location.getText().toString();
            Uri geoLoc = Uri.parse("geo:0,0?q=" + loc);

            Intent intent = new Intent(Intent.ACTION_VIEW, geoLoc);

            if (intent.resolveActivity(getPackageManager()) != null)
                startActivity(intent);
            else
                Log.d(LOG_TAG, "Can't handle open a location");
        });

        mainBinding.btnText.setOnClickListener(v -> {
            String text = mainBinding.text.getText().toString();
            final String mimeType = "text/plain";

            new ShareCompat.IntentBuilder(this)
                    .setType(mimeType)
                    .setChooserTitle(R.string.share_text_with)
                    .setText(text)
                    .startChooser();
        });
    }
}