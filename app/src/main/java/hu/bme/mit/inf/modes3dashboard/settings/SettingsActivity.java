package hu.bme.mit.inf.modes3dashboard.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import hu.bme.mit.inf.modes3dashboard.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSave();
            }
        });

        onLoad();
    }
    private void onSave(){
        EditText serverUriEditText = (EditText) findViewById(R.id.serverUriEditText);
        String serverURI = serverUriEditText.getText().toString();

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("ServerURI", serverURI);
        editor.commit();
    }
    private void onLoad(){
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String defaultValue = getResources().getString(R.string.settings_mqttURI_spaceholder);
        String serverURI = sharedPref.getString("ServerURI", defaultValue);

        EditText serverUriEditText = (EditText) findViewById(R.id.serverUriEditText);
        serverUriEditText.setText(serverURI);

    }

}
