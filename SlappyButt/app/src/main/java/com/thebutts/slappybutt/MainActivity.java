package com.thebutts.slappybutt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        SharedPreferences prefs = getSharedPreferences("MyPrefsFile", 0);
        String restoredText = prefs.getString("token", null);
        if (restoredText != null) {
            View b = findViewById(R.id.btn_register);
            b.setVisibility(View.INVISIBLE);
            View k = findViewById(R.id.logout);
            k.setVisibility(View.VISIBLE);
        }
        else if(restoredText==null){
            View b = findViewById(R.id.logout);
            b.setVisibility(View.INVISIBLE);
            View k = findViewById(R.id.btn_register);
            k.setVisibility(View.VISIBLE);
        }
        setupUiEvents();
    }

    void setupUiEvents() {
        Button firstButton = (Button) findViewById(R.id.btn_start_free_slapping);
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.onClickStart((Button) v);
            }
        });

        Button secondButton = (Button) findViewById(R.id.btn_register);
        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.onClickStartRegister((Button) v);
            }
        });

        Button thirdButton = (Button) findViewById(R.id.logout);
        thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.onClickLogout((Button) v);
            }
        });

        Button thButton = (Button) findViewById(R.id.highScoreButton);
        thButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.onClickScores((Button) v);
            }
        });

    }

    private void onClickStartRegister(Button b) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void onClickStart(Button b) {
        SharedPreferences prefs = getSharedPreferences("MyPrefsFile", 0);
        String restoredText = prefs.getString("token", null);
        if (restoredText != null) {
            Intent intent = new Intent(this, FreeSlappingActivity.class);
            startActivity(intent);

        }
        else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    private void onClickLogout(Button b) {
        SharedPreferences prefs = getSharedPreferences("MyPrefsFile", 0);
        prefs.edit().remove("token").commit();
        View v = findViewById(R.id.logout);
        v.setVisibility(View.INVISIBLE);
        View k = findViewById(R.id.btn_register);
        k.setVisibility(View.VISIBLE);
    }

    private void onClickScores(Button b) {
        Intent intent = new Intent(this, ScoresActivity.class);
        startActivity(intent);
    }
}
