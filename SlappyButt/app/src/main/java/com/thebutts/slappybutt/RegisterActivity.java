package com.thebutts.slappybutt;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class RegisterActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setupUiEvents();
    }

    void setupUiEvents() {
        Button firstButton = (Button) findViewById(R.id.btn_register);
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.onClickStartRegister((Button) v);
            }
        });
    }

    private void onClickStartRegister(Button b) {
        String username = ((EditText)findViewById(R.id.editText4)).getText().toString();
        String email = ((EditText)findViewById(R.id.editText)).getText().toString();
        String password = ((EditText)findViewById(R.id.editText2)).getText().toString();
        String confirmedPass = ((EditText)findViewById(R.id.editText3)).getText().toString();

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://localhost:4380/api/Account/Register");

        if(username.length() >= 1 && email.length() >= 1 && password.length() >= 1 && confirmedPass.length() >= 1
                && password.equals(confirmedPass)) {

            List<NameValuePair> nameValuePairs = new ArrayList<>(4);
            nameValuePairs.add(new BasicNameValuePair("Username", username));
            nameValuePairs.add(new BasicNameValuePair("Email", email));
            nameValuePairs.add(new BasicNameValuePair("Password", password));
            nameValuePairs.add(new BasicNameValuePair("ConfirmPassword", confirmedPass));
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            try {
                HttpResponse response = httpClient.execute(httpPost);
            } catch (IOException e) {
                e.printStackTrace();
            }


            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        }
        else if (username.length() < 1 && email.length() < 1 && password.length() < 1 && confirmedPass.length() < 1){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("All fields must be filled!")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //do things
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
        else if (!password.endsWith(confirmedPass)){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Passwords does not match!")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //do things
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }
}
