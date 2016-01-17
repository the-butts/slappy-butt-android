package com.thebutts.slappybutt;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

    public final static String apiURL = "http://localhost:4380/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUiEvents();
    }

    void setupUiEvents() {
        Button firstButton = (Button) findViewById(R.id.btn_start_free_slapping);
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.onClickStartFreeSlapping((Button) v);
            }
        });

        Button secondButton = (Button) findViewById(R.id.register);
        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.onClickStartRegister((Button) v);
            }
        });
    }

    private void onClickStartFreeSlapping(Button b) {
        Intent intent = new Intent(this, FreeSlappingActivity.class);
        startActivity(intent);
    }

    private void onClickStartRegister(Button b) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
