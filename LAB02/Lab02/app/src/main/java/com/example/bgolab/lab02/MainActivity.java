package com.example.bgolab.lab02;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver broadcastReceiver = new MyReceiver();

    IntentFilter intentFilter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.registerReceiver(broadcastReceiver,intentFilter);

        // Get Fragment belonged Activity
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Fragment myFragment = new BlankFragment();
                transaction.add(R.id.frameLayout, myFragment);
                transaction.commit();
            }
        });
        Button btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Fragment myFragment = new BlankFragment2();
                transaction.add(R.id.frameLayout, myFragment);
                transaction.commit();
            }
        });




    }

    @Override
    protected void onDestroy() {
        this.unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }
}
