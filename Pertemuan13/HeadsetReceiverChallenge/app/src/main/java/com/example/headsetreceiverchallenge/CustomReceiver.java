package com.example.headsetreceiverchallenge;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {
    private boolean headsetPlugged = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();

        if (intentAction != null) {
            String toastMessage = "Unknown intent action";
            switch (intentAction) {
                case Intent.ACTION_HEADSET_PLUG:
                    headsetPlugged = !headsetPlugged;
                    toastMessage = (headsetPlugged) ? "Headset Plugged!" : "Headset Unplugged";
                    break;
            }
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
        }
    }
}