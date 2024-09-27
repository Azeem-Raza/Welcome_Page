package com.azeem.feedbackapplicationform;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    String tag = "EVH_Demo: ";

    long currentTime = System.currentTimeMillis();  // Store the last event timestamp

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getElapsedTime("onCreate");// Update the last event timestamp
    }

    @Override
    protected void onStart() {
        super.onStart();

        getElapsedTime("onStart");  // Update the last event timestamp
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        getElapsedTime("onRestart");
          // Update the last event timestamp
    }

    @Override
    protected void onResume() {
        super.onResume();

        getElapsedTime("onResume"); // Update the last event timestamp
    }

    @Override
    protected void onPause() {
        super.onPause();

        getElapsedTime("onPause");  // Update the last event timestamp
    }

    @Override
    protected void onStop() {
        super.onStop();

        getElapsedTime("onStop");  // Update the last event timestamp
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        getElapsedTime("onDestroy");  // Update the last event timestamp
    }

    // Helper method to calculate elapsed time
    private void getElapsedTime(String phase) {
        long ElapsedTime = System.currentTimeMillis()- currentTime;
        Log.d(tag, tag + phase+"- Elapsed time: " + ElapsedTime + " ms");
        currentTime = System.currentTimeMillis();
    }
}
