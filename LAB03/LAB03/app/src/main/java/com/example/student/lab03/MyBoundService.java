package com.example.student.lab03;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class MyBoundService extends Service {
    private int operationCounter;
    private final IBinder binder = new MyBinder();
    private Toast toast;
    private Timer timer;
    private TimerTask timerTask;

    @Override
    public void onCreate() {
        super.onCreate();
        timer = new Timer();
        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        operationCounter = 0;
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        clearTimerSchedule();
        initTask();
        timer.scheduleAtFixedRate(timerTask, 6 * 1000, 6 * 1000);
        showToast("Your service has been started");
        return super.onStartCommand(intent, flags, startId);
    }

    private class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    showToast("Your bound service is still working.");
                    operationCounter++;
                }
            });


        }
    }

    public void showOperationCounter() {
        showToast("Operation counter: " + operationCounter);
    }

    private void showToast(String text) {
        toast.setText(text);
        toast.show();
    }
    private void clearTimerSchedule() {
        if(timerTask != null) {
            timerTask.cancel();
            timer.purge();
        }
    }

    private void initTask() {
        timerTask = new MyTimerTask();
    }

    @Override
    public void onDestroy() {
        clearTimerSchedule();
        showToast("Your service has been stopped");
        super.onDestroy();
    }

    public class MyBinder extends Binder {
        MyBoundService getMyService() {
            return MyBoundService.this;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
