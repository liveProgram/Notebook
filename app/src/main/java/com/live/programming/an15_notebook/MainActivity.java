package com.live.programming.an15_notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        Intent in = new Intent(MainActivity.this, DisplayActivity.class);
                        startActivity(in);
                        MainActivity.this.finish(); // destroy this activity forever during this execution
                    }
                },
                3500
        );

    }
}