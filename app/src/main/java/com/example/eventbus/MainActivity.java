package com.example.eventbus;

/* EventBus is an open-source Android library that simplifies communication between Activities, Fragments,
 * Threads, and Services, with less code and better quality.
 * The main reason we should use EventBus is loose coupling. Sometimes, you want to process specific events
 * that are interested in multiple parts of your application, like the presentation layer, business layer,
 * and data layer, so EventBus provides an easy solution for this.
 * */

import android.app.IntentService;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.text_info)
    TextView textInfo;

    @BindView(R.id.event_bus_text)
    TextView displayTextView;

   // private static final String TAG = MainActivity.class.getSimpleName();
   // private TextView displayTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

//        displayTextView = (TextView)findViewById(R.id.event_bus_text);
//        Button serviceButton = (Button)findViewById(R.id.service_button);
//        serviceButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent serviceIntent = new Intent(MainActivity.this, MyService.class);
//
//                Log.e("Button", "Inside Button click");
//                startService(serviceIntent);
//
//                Log.e("Button", "Below startService");
//            }
//        });
    }

    @OnClick(R.id.service_button)
    public void onButtonClick(View view) {
        Intent serviceIntent = new Intent(MainActivity.this, MyService.class);
        startService(serviceIntent);
    }

    /* IntentService runs on a different thread. So to update a UI view from another thread we will use
     * the EventBus to achieve this.
     *
     * A messageEventFromService() method which is annotate with @Subscribe is a subscriber to the event publisher.
     * */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventFromService(EventMessage event) {
        displayTextView.setText(event.getNotification());
    }

    @Override
    protected void onStart() {
        super.onStart();
//        Log.e("Print","On Start");
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
