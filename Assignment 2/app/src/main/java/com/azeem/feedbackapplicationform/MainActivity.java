package com.azeem.feedbackapplicationform;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    String tag = "EVH_Demo: ";

    long currentTime = System.currentTimeMillis();  // Store the last event timestamp

    Button generateButton;
    TextView generateNo;
    String currentValue = null;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateNo = findViewById(R.id.generateNo);
        generateButton = findViewById(R.id.generateButton);

        // Restore the saved state (if any)
        if (savedInstanceState != null) {
            currentValue = savedInstanceState.getString("currentValue");
            if (currentValue != null) {
                generateNo.setText(currentValue);  // Restore the random number
            }
        }

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                int val = random.nextInt(100);  // Generate a random number
                currentValue = Integer.toString(val);  // Update the current value
                generateNo.setText(currentValue);  // Set the current value to the TextView
            }
        });



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

    // Save the current value before the activity is destroyed
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("currentValue", currentValue);  // Save the current random number
    }


    // Restore the saved state when the activity is recreated
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currentValue = savedInstanceState.getString("currentValue");  // Retrieve the saved number
        if (currentValue != null) {
            generateNo.setText(currentValue);  // Display the saved random number
        }
    }

    // Helper method to calculate elapsed time
    private void getElapsedTime(String phase) {
        long ElapsedTime = System.currentTimeMillis()- currentTime;
        Log.d(tag, tag + phase+"- Elapsed time: " + ElapsedTime + " ms");
        currentTime = System.currentTimeMillis();
    }
}
