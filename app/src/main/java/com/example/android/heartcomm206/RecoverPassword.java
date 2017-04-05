package com.example.android.heartcomm206;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.heartcomm207.R;

public class RecoverPassword extends AppCompatActivity {

    Button b1;
    EditText e1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_password);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (e1.getText().toString().equals("207")) {
                    Toast.makeText(getApplicationContext(), "Password is johnsmith", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), LogInActivity.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong answer, please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
