package com.thebutts.slappybutt;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    private ProgressBar progress;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;
        progress = (ProgressBar) findViewById(R.id.progress_barr_main);


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
    }

    private void onClickStartFreeSlapping(Button b) {
        progress.setVisibility(View.VISIBLE);


        new Thread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, FreeSlappingActivityWithFragment.class);
                startActivity(intent);
            }
        }).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        progress.setVisibility(View.INVISIBLE);

       // Toast.makeText(this, "huc huc", Toast.LENGTH_SHORT).show();
    }
}
