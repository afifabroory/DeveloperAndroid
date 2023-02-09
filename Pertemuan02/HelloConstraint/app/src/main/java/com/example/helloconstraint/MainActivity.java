package com.example.helloconstraint;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.helloconstraint.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private int mCount = 0;
    private ActivityMainBinding binding;
    private boolean zeroFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // findViewById Replacement
        // https://developer.android.com/topic/libraries/view-binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot()); // Set Activity (UI) yang akan digunakan.
    }

    private void setShowCountText() {
        binding.showCount.setText(Integer.toString(mCount));
    }

    private void changeCountButton() {
        binding.buttonCount.setBackgroundTintList(ColorStateList.valueOf((mCount%2 == 0) ? 0xFF3700B3 : 0xFF018786));
    }

    public void showToast(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void countUp(View view) {
        mCount++;

        if (mCount > 0 && zeroFlag) {
            binding.buttonZero.setBackgroundTintList(ColorStateList.valueOf(0xFFCC0000));
            zeroFlag = false;
        }
        changeCountButton();
        setShowCountText();
    }

    public void countReset(View view) {
        mCount = 0;

        if (!zeroFlag) {
            view.setBackgroundTintList(ColorStateList.valueOf(0xFFAAAAAA));
            zeroFlag = true;
        }
        changeCountButton();
        setShowCountText();
    }
}