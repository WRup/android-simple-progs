package com.example.student.lab03;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MyBoundService myBoundService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
        Intent serviceIntent = new Intent(this, MyBoundService.class);
        startService(serviceIntent);
        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent serviceIntent = new Intent(MainActivity.this, MyBoundService.class);
                unbindService(serviceConnection);
                stopService(serviceIntent);
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        Button btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showServiceOperationCounter();
            }
        });
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyBoundService.MyBinder binder = (MyBoundService.MyBinder) iBinder;
            myBoundService = binder.getMyService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            myBoundService = null;
        }
    };

    private void showServiceOperationCounter() {
        if(myBoundService != null) {
            myBoundService.showOperationCounter();
        }
    }
}
