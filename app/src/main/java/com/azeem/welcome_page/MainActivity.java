package com.azeem.welcome_page;

import android.os.Bundle; // Imports the Bundle class, which is used to pass data between Android components.
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}