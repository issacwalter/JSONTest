package com.example.shit.jsontest;

import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "TTEST";
    private EventBus mainEventBus;
    private FragmentReader fragmentReader;

    protected Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentReader = new FragmentReader();
        FragmentManager fManager = getSupportFragmentManager();
        FragmentTransaction transaction = fManager.beginTransaction();
        transaction.replace(android.R.id.content, fragmentReader);
        transaction.commit();

        EventBus.getDefault().register(this);
        mainEventBus = new EventBus();

    }

    @Subscribe
    public void onEvent(MyEvent event) {

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
