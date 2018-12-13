package hu.bme.mit.inf.modes3dashboard;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import hu.bme.mit.inf.modes3dashboard.service.MQTTIntentService;

public class RedButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_button);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalShutdown();
                Snackbar.make(view, R.string.emergency_shutdown, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Button stopTrainButton = findViewById(R.id.buttonStopTrains);
        Button stopSegmentButton = findViewById(R.id.buttonStopSegments);
        stopTrainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trainsShutdown();
                Snackbar.make(view, R.string.emergency_train_stop, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        stopSegmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                segmentsShutdown();
                Snackbar.make(view, R.string.emergency_segment_stop, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    private void totalShutdown(){
        trainsShutdown();
        segmentsShutdown();
    }
    private void trainsShutdown(){
        MQTTIntentService.startActionStopTrains(this);
    }
    private void segmentsShutdown(){
        MQTTIntentService.startActionStopSegments(this);
    }

}
