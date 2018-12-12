package hu.bme.mit.inf.modes3dashboard;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import hu.bme.mit.inf.modes3dashboard.data.DatabaseConnector;
import hu.bme.mit.inf.modes3dashboard.data.ModesDatabase;
import hu.bme.mit.inf.modes3dashboard.data.Train;
import hu.bme.mit.inf.modes3dashboard.fragments.TrainListFragment;

public class NewElementActivity extends AppCompatActivity {
    private DatabaseConnector connector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseConnector.initialize(this);
        connector = DatabaseConnector.getInstance();
        setContentView(R.layout.activity_new_element);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewElementActivity.this, RedButtonActivity.class);
                startActivity(intent);
            }
        });

        Button saveTrainButton = findViewById(R.id.trainSaveButton);
        saveTrainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Train train = new Train();
                train.name = ((TextView)findViewById(R.id.trainName)).getText().toString();
                train.modesId = Long.getLong(((TextView)findViewById(R.id.trainId)).getText().toString());
                connector.createTrain(train);
            }
        });
    }
}
