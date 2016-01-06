package com.thebutts.slappybutt;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

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
    }

    private void onClickStartFreeSlapping(Button b) {
        Intent intent = new Intent(this, FreeSlappingActivity.class);
        startActivity(intent);
    }
}
