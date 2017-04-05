package com.example.android.heartcomm206;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.heartcomm207.R;

public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Heart_scanner_activity:
                Intent heartScannerIntent = new Intent(this, HeartScannerActivity.class);
                startActivity(heartScannerIntent);
                return true;
            case R.id.Visual_stat_activity:
                Intent visualStatIntent = new Intent(this, VisualStatActivity.class);
                startActivity(visualStatIntent);
                return true;
            case R.id.Profile_activity:
                Intent profileIntent = new Intent(this, ProfileActivity.class);
                startActivity(profileIntent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}





