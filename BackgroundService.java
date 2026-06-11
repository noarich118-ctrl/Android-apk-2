package com.example.maliciousapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;

public class BackgroundService extends Service {
    private Timer timer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startBackgroundTasks();
        return START_STICKY;
    }

    private void startBackgroundTasks() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Collect contacts and data in background
                collectData();
                
                // Additional surprise: change ringtone
                changeRingtone();
            }
        }, 0, 300000); // Every 5 minutes
    }

    private void collectData() {
        // This would collect contacts and passwords in real app
        // Simulated collection
    }

    private void changeRingtone() {
        // Would change ringtone in real app
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
}
