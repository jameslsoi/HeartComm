package com.example.android.heartcomm206;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.heartcomm206.AboutActivity;
import com.example.android.heartcomm206.HeartScannerActivity;
import com.example.android.heartcomm207.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class VisualStatActivity extends AppCompatActivity {

    LineGraphSeries<DataPoint> series2;

    private Button savenotebutton1;
    private SharedPreferences savednotes;
    private EditText editText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_stat);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        savenotebutton1 = (Button) findViewById(R.id.savenotes);
        editText1 = (EditText) findViewById(R.id.addnotes);
        savednotes = getSharedPreferences("notes", MODE_PRIVATE);

        savenotebutton1.setOnClickListener(saveButtonListener);

        editText1.setText(savednotes.getString("tag", null)); //add this line


        GraphView graph2 = (GraphView) findViewById(R.id.monthly_graph);
        series2 = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(1, 77),
                new DataPoint(2, 76),
                new DataPoint(3, 73),
                new DataPoint(4, 70),
                new DataPoint(5, 72),
                new DataPoint(6, 75),
                new DataPoint(7, 76),
                new DataPoint(8, 80)

        });
        graph2.addSeries(series2);

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
            case R.id.Profile_activity:
                Intent profileIntent = new Intent(this, ProfileActivity.class);
                startActivity(profileIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void makeTag(String tag){
        String save = savednotes.getString("tag", null);
        SharedPreferences.Editor preferencesEditor = savednotes.edit();
        preferencesEditor.putString("tag",tag);
        preferencesEditor.apply();

    }

    public View.OnClickListener saveButtonListener = new View.OnClickListener() {
        /**
         * Saves the input using shared preferences.
         * @param v
         */
        @Override
        public void onClick(View v) {
            if (editText1.getText().length() > 0) {
                makeTag(editText1.getText().toString());

                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(editText1.getWindowToken(), 0);
            }
        }
    };

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




