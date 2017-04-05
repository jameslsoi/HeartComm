package com.example.android.heartcomm206;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.heartcomm206.HeartScannerActivity;
import com.example.android.heartcomm207.R;

public class LogInActivity extends AppCompatActivity {


    Button b1, b2;
    EditText e1, e2;

    int counter = 3; //used as a failed login counter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        b1 = (Button) findViewById(R.id.loginbutton);
        b2 = (Button) findViewById(R.id.recoverpassword);
        e1 = (EditText) findViewById(R.id.userEntry);
        e2 = (EditText) findViewById(R.id.passEntry);

        b1.setOnClickListener(new View.OnClickListener() {
            /**
             * checks user login detail, logs in user or denies access based on
             * credentials
             *
             * @param v
             */
            @Override
            public void onClick(View v) {
                if (e1.getText().toString().equals("johnsmith") &&
                        e2.getText().toString().equals("johnsmith")) {
                    startActivity(new Intent(getApplicationContext(), HeartScannerActivity.class)); //if user details are correct start the next activity
                    finish(); //Can not return to this page using the back button
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong credentials", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "Tries left: " + counter, Toast.LENGTH_SHORT).show();
                    counter--; //counter reduces everytime credentials are wrong

                    if (counter == 0) {
                        b1.setEnabled(false); //if user credentials are wrong, disable the ability to log in
                    }
                }
            }
        });
    }
}

