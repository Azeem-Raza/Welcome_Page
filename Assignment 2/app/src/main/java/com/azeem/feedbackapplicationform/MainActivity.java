package com.azeem.feedbackapplicationform;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // UI Elements
    private EditText firstNameEditText, lastNameEditText, phoneEditText, emailEditText;
    private TextView randomNumberTextView, lastRandomNumberTextView;

    // Random number handling
    private Handler handler = new Handler();
    private Random random = new Random();
    private int lastRandomNumber;

    // State variables
    private String orientationChangeTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        startUpdatingRandomNumber();

    //Controlling Screen Orientations
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // Restore state if activity is recreated (e.g., after orientation change)
        if (savedInstanceState != null) {
            restoreInstanceState(savedInstanceState);
        }
    }

    // Initialize UI elements
    private void initializeViews() {
        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.SurNameEditText);
        phoneEditText = findViewById(R.id.PhoneEditText);
        emailEditText = findViewById(R.id.EmailEditText);
        randomNumberTextView = findViewById(R.id.randomNumberTextView);
        lastRandomNumberTextView = findViewById(R.id.lastRandomNumberTextView);
    }

    // Start updating the random number every second
    private void startUpdatingRandomNumber() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateRandomNumber();
                handler.postDelayed(this, 1000);  // Repeat every second
            }
        }, 1000);
    }

    // Update and display a new random number
    private void updateRandomNumber() {
        lastRandomNumber = random.nextInt(100);  // Random number between 0-99
        randomNumberTextView.setText("Random Number: " + lastRandomNumber);
    }

    // Restore state (form data and random number) if activity is recreated
    private void restoreInstanceState(Bundle savedInstanceState) {
        firstNameEditText.setText(savedInstanceState.getString("firstName"));
        lastNameEditText.setText(savedInstanceState.getString("lastName"));
        phoneEditText.setText(savedInstanceState.getString("phoneNumber"));
        emailEditText.setText(savedInstanceState.getString("email"));

        // Restore last random number and orientation change time
        lastRandomNumber = savedInstanceState.getInt("lastRandomNumber");
        lastRandomNumberTextView.setText("Last Random Number before Orientation: " + lastRandomNumber);

        orientationChangeTime = savedInstanceState.getString("orientationChangeTime");
        Toast.makeText(this, "Orientation changed at: " + orientationChangeTime, Toast.LENGTH_LONG).show();
    }

    // Save form data and random number on orientation change or activity recreation
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save form data
        outState.putString("firstName", firstNameEditText.getText().toString());
        outState.putString("lastName", lastNameEditText.getText().toString());
        outState.putString("phoneNumber", phoneEditText.getText().toString());
        outState.putString("email", emailEditText.getText().toString());

        // Save random number and orientation change time
        outState.putInt("lastRandomNumber", lastRandomNumber);
        outState.putString("orientationChangeTime", getCurrentTimeFormatted());
    }

    // Utility method to get the current time as a formatted string
    private String getCurrentTimeFormatted() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }

    // Stop updating the random number when the activity is destroyed
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);  // Clean up the handler
    }
}

