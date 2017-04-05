package com.example.android.heartcomm206;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import com.example.android.heartcomm207.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.IOException;
import java.util.Random;

public class HeartScannerActivity extends AppCompatActivity {

    Button startRecord, stopRecord;
    String audioSavePath = null;
    MediaRecorder mediaRecorder;
    String RandomAudioFileName = "ABCDEFGHIJKLMNOP";

    Random random;

    public static final int RequestPermissionCode = 1;

    LineGraphSeries<DataPoint> series;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_scanner);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        startRecord = (Button) findViewById(R.id.micButton);
        stopRecord = (Button) findViewById(R.id.micStop);
        stopRecord.setEnabled(false);
        random = new Random();
        /**
         * When user clicks on the record button, the application checks if permissions to record and
         * write to storage are allowed.
         * Starts recording based on permissions
         */
        startRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkPermission()) {
                    audioSavePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" +
                            CreateRandomAudioFileName(5) + "AudioRecording.3gp";  //storage

                    MediaRecorderReady(); //calls class MediaRecorderReady

                    try {

                        mediaRecorder.start();
                    } catch (IllegalStateException e) {   //Checks state of application, i.e. can't record if already recording
                        Log.d("Error", "IllegalStateException");
                        e.printStackTrace();
                    }
                    startRecord.setEnabled(false); //Disable record button once record starts
                    stopRecord.setEnabled(true); //Enables use of stop record button

                    Toast.makeText(HeartScannerActivity.this, "Recording",
                            Toast.LENGTH_LONG).show();
                } else {
                    requestPermission();
                }
            }
        });
        stopRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mediaRecorder.stop(); //stops media recorder on click
                    mediaRecorder.reset(); // restarts to idle state
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
                stopRecord.setEnabled(false);
                startRecord.setEnabled(true);

                Toast.makeText(HeartScannerActivity.this, "Recording Completed",
                        Toast.LENGTH_LONG).show();
            }
        });
        /**
         * Creates a graph with static data points.
         */
        GraphView graph = (GraphView) findViewById(R.id.heartGraph);
        series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(12, 60),
                new DataPoint(13, 64),
                new DataPoint(14, 62),
                new DataPoint(15, 70),
                new DataPoint(16, 68),
                new DataPoint(17, 74),
                new DataPoint(18, 76)

        });
        graph.addSeries(series);
    }

    public void MediaRecorderReady(){
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC); //set audio source, uses the built in microphone
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP); //set data format
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB); // encodes the recording
        mediaRecorder.setOutputFile(audioSavePath); //set the output file to audioSavePath
    }

    /**
     * Create an audio file to store the encoded audio
     *
     */

    public String CreateRandomAudioFileName(int string){
        StringBuilder stringBuilder = new StringBuilder( string );
        int i = 0 ;
        while(i < string ) {
            stringBuilder.append(RandomAudioFileName.
                    charAt(random.nextInt(RandomAudioFileName.length()))); //create a random audio file name

            i++ ;
        }
        return stringBuilder.toString();
    }
    /**
     * checks if permissions are allowed.
     * @return
     */
    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),
                RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Requests permission to write to external storage and record audio
     */
    private void requestPermission() {
        ActivityCompat.requestPermissions(HeartScannerActivity.this, new
                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
    }

    /**
     * Checks permission results.
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length > 0) {
                    boolean StoragePermission = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean RecordPermission = grantResults[1] ==
                            PackageManager.PERMISSION_GRANTED;

                    if (StoragePermission && RecordPermission) {
                        Toast.makeText(HeartScannerActivity.this, "Permission Granted",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(HeartScannerActivity.this, "Permission Denied", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }


        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;

        }

    /**
     * allows navigation of the application with the use of the app bar
     * @param item
     * @return
     */
    @Override
        public boolean onOptionsItemSelected (MenuItem item){
            switch (item.getItemId()) {
                case R.id.Visual_stat_activity:
                    Intent visualStatIntent = new Intent(this, VisualStatActivity.class);
                    startActivity(visualStatIntent);
                    return true;
                case R.id.About_activity:
                    Intent aboutIntent = new Intent(this, AboutActivity.class);
                    startActivity(aboutIntent);
                    return true;
                case R.id.Profile_activity:
                    Intent profileIntent = new Intent(this, ProfileActivity.class);
                    startActivity(profileIntent);
                    return true;
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





