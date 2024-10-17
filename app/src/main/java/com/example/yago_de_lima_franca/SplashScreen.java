package com.example.yago_de_lima_franca;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashScreen extends AppCompatActivity {
    private int i;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        progressBar = findViewById(R.id.progressBar);
        executarProgressBar();
    }

    private void executarProgressBar() {
        Handler handler = new Handler(Looper.myLooper());
        progressBar.setVisibility(View.VISIBLE);
        i = progressBar.getProgress();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (i < 100) {
                    i += 10;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(i);
                            if (i >= 100) {
                                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }//while
            }//tarefa
        }).start();
    }
}
