package com.example.eventbus;

/* This class will extends IntentService class and override the onHandleIntent(); This is where the intent
 * the component that started it is obtained.*/

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

public class MyService extends IntentService {

    public MyService() {
        super("MY SERVICE");
        Log.e("Print", "My SERVICE CONSTRUCTOR");
    }

    @Override
    protected void onHandleIntent(Intent serviceIntent) {

        Log.e("Service", "Intent in service");
        EventBus.getDefault().post(new EventMessage(getString(R.string.event_message_from_service)));
    }
}

