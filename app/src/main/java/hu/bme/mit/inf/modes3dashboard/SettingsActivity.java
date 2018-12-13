package hu.bme.mit.inf.modes3dashboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import hu.bme.mit.inf.modes3dashboard.service.MQTTIntentService;

public class SettingsActivity extends AppCompatActivity {

    EditText serverEditText;
    EditText portEditText;
    Button saveAndConnectButton;
    EditText subTopicEditText;
    EditText pubTopicEditText;
    EditText pubNameEditText;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, RedButtonActivity.class);
                startActivity(intent);
            }
        });

        serverEditText = findViewById(R.id.editTextServer);
        portEditText = findViewById(R.id.editTextPort);
        saveAndConnectButton = findViewById(R.id.connectButton);
        subTopicEditText = findViewById(R.id.editTextSubTopic);
        pubTopicEditText = findViewById(R.id.editTextPubTopic) ;
        pubNameEditText = findViewById(R.id.editTextPubName) ;
        saveButton = findViewById(R.id.saveButton);

        saveAndConnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mqttSaveAndConnect(view);
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsSave(view);
            }
        });
        initializeSettings();
    }

    private void initializeSettings(){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String serverDefaultValue = getResources().getString(R.string.server_input_placeholder);
        String portDefaultValue = getResources().getString(R.string.port_input_placeholder);
        String subTopicDefaultValue = getResources().getString(R.string.topic_name);
        String pubTopicDefaultValue = getResources().getString(R.string.topic_name);
        String pubNameDefaultValue = getResources().getString(R.string.name);

        serverEditText.setText(sharedPref.getString(getString(R.string.MQTTServer), serverDefaultValue));
        portEditText.setText(sharedPref.getString(getString(R.string.MQTTPort), portDefaultValue));
        subTopicEditText.setText(sharedPref.getString(getString(R.string.MQTTSubTpoic), subTopicDefaultValue));
        pubTopicEditText.setText(sharedPref.getString(getString(R.string.MQTTPubTpoic), pubTopicDefaultValue));
        pubNameEditText.setText(sharedPref.getString(getString(R.string.MQTTPubName), pubNameDefaultValue));
    }

    private void mqttSaveAndConnect(View view){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String server = serverEditText.getText().toString();
        String port =  portEditText.getText().toString();
        editor.putString(getString(R.string.MQTTServer), server);
        editor.putString(getString(R.string.MQTTPort), port);
        editor.apply();
        Snackbar.make(view, R.string.snackbar_SaveServerSettings, Snackbar.LENGTH_LONG)
                .setAction(R.string.action, null).show();

        MQTTIntentService.startActionSetServerAndConnect(this,server,port);
    }
    private void settingsSave(View view){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.MQTTServer), serverEditText.getText().toString());
        editor.putString(getString(R.string.MQTTPort), portEditText.getText().toString());
        String subTopic = subTopicEditText.getText().toString();
        String pubTopic = pubTopicEditText.getText().toString();
        String pubName = pubNameEditText.getText().toString();
        editor.putString(getString(R.string.MQTTSubTpoic), subTopic);
        editor.putString(getString(R.string.MQTTPubTpoic), pubTopic);
        editor.putString(getString(R.string.MQTTPubName), pubName);
        editor.apply();
        Snackbar.make(view, R.string.snackbar_SaveSettings, Snackbar.LENGTH_LONG)
                .setAction(R.string.action, null).show();

        MQTTIntentService.startActionSetTopicData(this,subTopic,pubTopic,pubName);
    }

}
