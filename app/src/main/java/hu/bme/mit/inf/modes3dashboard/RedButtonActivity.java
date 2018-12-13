package hu.bme.mit.inf.modes3dashboard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import hu.bme.mit.inf.modes3dashboard.service.MQTTIntentService;

import static hu.bme.mit.inf.modes3dashboard.service.MQTTIntentService.ConnectionState.UNSET;

public class RedButtonActivity extends AppCompatActivity {
    private View layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout = findViewById(R.id.layoutRedButton);

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

    private class activityBroadcastReciver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action){
                default:
                    return;
                case MQTTIntentService.BROADCAST_STATE_CHANGED:
                    onMQTTStateChanged(intent.getIntExtra(MQTTIntentService.STATE,0));
                    break;
            }
        }

        private void onMQTTStateChanged(int i){
            MQTTIntentService.ConnectionState state = MQTTIntentService.ConnectionState.fromInt(i);
            switch (state){
                case UNSET:
                    break;
                case CONNECTING:
                    break;
                case CONNECTED:
                    break;
                case TOPIC_SET:
                    break;
            }
        }
    }
    private void onMQTTConnected(){
        Snackbar.make(layout,"MQTTConnected", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

}
