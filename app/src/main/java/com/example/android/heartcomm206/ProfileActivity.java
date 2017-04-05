package com.example.android.heartcomm206;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.android.heartcomm207.R;

public class ProfileActivity extends AppCompatActivity {

    private static final int REQUEST_PHONE_PERMISSION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_actiivty);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        findViewById(R.id.emergencyCall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestCall();
            }
        });


    }

    /**
     * asks for call permissions
     */
    private void requestCall() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    REQUEST_PHONE_PERMISSION);
        } else {
            callems();
        }
    }

    /**
     * Calls ems
     */
    private void callems() {
        Intent in = new Intent(Intent.ACTION_CALL, Uri.parse("tel:07206")); // uri.parse is used to tell the application what to reference
        try {
            startActivity(in);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getApplicationContext(),
                    "Could not find an activity to place the call.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Check and returns permission results for use in request call class
     * @param requestCode
     * @param permissions
     * @param grantResults
     */

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PHONE_PERMISSION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callems();
                }
                break;
            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Heart_scanner_activity:
                Intent heartScannerIntent = new Intent(this, HeartScannerActivity.class);
                startActivity(heartScannerIntent);
                return true;
            case R.id.About_activity:
                Intent aboutIntent = new Intent(this, AboutActivity.class);
                startActivity(aboutIntent);
                return true;
            case R.id.Visual_stat_activity:
                Intent visualIntent = new Intent(this, VisualStatActivity.class);
                startActivity(visualIntent);
                return true;
            /*case R.id.log_out:
                Intent logoutIntent = new Intent(this, LogInActivity.class);
                startActivity(logoutIntent);
                return true;*/
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





