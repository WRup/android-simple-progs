package com.example.student.lab03;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {

    private Toast toast;
    private Timer timer;
    private TimerTask timerTask;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        timer = new Timer();
        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
    }

    private class MyTimerTtask extends TimerTask {
        @Override
        public void run() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    showToast("Your service is still working");
                }
            });

        }
    }
    private void showToast(String text) {
        toast.setText(text);
        toast.show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        clearTimerSchedule();
        initTask();
        timer.scheduleAtFixedRate(timerTask, 4 * 1000, 4 * 1000);
        showToast("Your service has been started");
        return super.onStartCommand(intent, flags, startId);
    }
    private void clearTimerSchedule() {
        if(timerTask != null) {
            timerTask.cancel();
            timer.purge();
        }
    }
    private void initTask() {
        timerTask = new MyTimerTtask();
    }
}
