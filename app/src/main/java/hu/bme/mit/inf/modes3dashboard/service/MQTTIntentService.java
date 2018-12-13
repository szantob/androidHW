package hu.bme.mit.inf.modes3dashboard.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.Serializable;

import hu.bme.mit.inf.modes3dashboard.R;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MQTTIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_SET_AND_CONNECT = "hu.bme.mit.inf.modes3dashboard.service.action.SET_CONNECT";
    private static final String ACTION_SET_TOPIC_DATA = "hu.bme.mit.inf.modes3dashboard.service.action.SET_TOPIC";
    private static final String ACTION_STOP_TRAINS = "hu.bme.mit.inf.modes3dashboard.service.action.STOP_TRAINS";
    private static final String ACTION_STOP_SEGMENTS = "hu.bme.mit.inf.modes3dashboard.service.action.STOP_SEGMENTS";

    public static final String BROADCAST_STATE_CHANGED = "hu.bme.mit.inf.modes3dashboard.broadcast.STATE_CHANGED";

    // TODO: Rename parameters
    private static final String SERVER = "hu.bme.mit.inf.modes3dashboard.service.extra.SERVER";
    private static final String PORT = "hu.bme.mit.inf.modes3dashboard.service.extra.PORT";
    private static final String SUB_TOPIC = "hu.bme.mit.inf.modes3dashboard.service.extra.SUB_TOPIC";
    private static final String PUB_TOPIC = "hu.bme.mit.inf.modes3dashboard.service.extra.PUB_TOPIC";
    private static final String PUB_NAME = "hu.bme.mit.inf.modes3dashboard.service.extra.PUB_NAME";

    public static final String STATE = "hu.bme.mit.inf.modes3dashboard.service.extra.STATE";

    private static String server = null;
    private static String port = null;
    private static String subTopic = null;
    private static String pubTopic = null;
    private static String pubName = null;
    private static ConnectionState state = ConnectionState.UNSET;


    public MQTTIntentService() {
        super("MQTTIntentService");
    }

    public static void startActionSetServerAndConnect(
            Context context,
            String server,
            String port) {
        Intent intent = new Intent(context, MQTTIntentService.class);
        intent.setAction(ACTION_SET_AND_CONNECT);
        intent.putExtra(SERVER, server);
        intent.putExtra(PORT, port);
        context.startService(intent);
    }
    public static void startActionSetTopicData(
            Context context,
            String subTopic,
            String pubTopic,
            String pubName) {
        Intent intent = new Intent(context, MQTTIntentService.class);
        intent.setAction(ACTION_SET_TOPIC_DATA);
        intent.putExtra(SUB_TOPIC, subTopic);
        intent.putExtra(PUB_TOPIC, pubTopic);
        intent.putExtra(PUB_NAME, pubName);
        context.startService(intent);
    }
    public static void startActionStopTrains(Context context) {
        Intent intent = new Intent(context, MQTTIntentService.class);
        intent.setAction(ACTION_STOP_TRAINS);
        context.startService(intent);
    }
    public static void startActionStopSegments(Context context) {
        Intent intent = new Intent(context, MQTTIntentService.class);
        intent.setAction(ACTION_STOP_SEGMENTS);
        context.startService(intent);
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            switch (action) {
                case ACTION_SET_AND_CONNECT:
                    server = intent.getStringExtra(SERVER);
                    port = intent.getStringExtra(PORT);
                    connectToMQTT();
                    break;
                case ACTION_SET_TOPIC_DATA:
                    subTopic = intent.getStringExtra(SUB_TOPIC);
                    pubTopic = intent.getStringExtra(PUB_TOPIC);
                    pubName = intent.getStringExtra(PUB_NAME);
                    setTopic();
                    break;
                case ACTION_STOP_SEGMENTS:
                    stopSegments();
                    break;
                case ACTION_STOP_TRAINS:
                    stopTrains();
                    break;
            }
        }
    }

    private void setTopic() {
        Log.i(getString(R.string.logcat_MQTT_Connector),getString(R.string.topic_data_set));
        try {
            Thread.sleep(5000);         //TODO Real setTopic()
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(getString(R.string.logcat_MQTT_Connector),getString(R.string.topic_set));
    }
    private void connectToMQTT() {
        Log.i(getString(R.string.logcat_MQTT_Connector),getString(R.string.connecting_to_mqtt));
        try {
            Thread.sleep(5000);         //TODO Real connectToMQTT()
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(getString(R.string.logcat_MQTT_Connector),getString(R.string.connected));
        changeConnectionState(ConnectionState.CONNECTED);
    }
    private void stopTrains(){
        Log.i(getString(R.string.logcat_MQTT_Connector),getString(R.string.stopping_all_trains));
        try {
            Thread.sleep(5000);         //TODO Real stopTrains()
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(getString(R.string.logcat_MQTT_Connector),getString(R.string.all_trains_stopped));
    }
    private void stopSegments(){
        Log.i(getString(R.string.logcat_MQTT_Connector),getString(R.string.stopping_all_segments));
        try {
            Thread.sleep(5000);         //TODO Real stopSegments()
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(getString(R.string.logcat_MQTT_Connector),getString(R.string.all_segments_stopped));
    }

    private void changeConnectionState(ConnectionState to){
        state = to;
        Intent intent = new Intent();
        intent.setAction(BROADCAST_STATE_CHANGED);
        intent.putExtra(STATE,state.toInt());
        sendBroadcast(intent);
    }


    public enum ConnectionState{
        UNSET,
        CONNECTING,
        CONNECTED,
        TOPIC_SET;

        public int toInt(){
            switch (this){
                default:
                case UNSET:
                    return 0;
                case CONNECTING:
                    return 1;
                case CONNECTED:
                    return 2;
                case TOPIC_SET:
                    return 3;
            }
        }
        public static ConnectionState fromInt(int i){
            switch (i){
                default:
                case 0:
                    return UNSET;
                case 1:
                    return CONNECTING;
                case 2:
                    return CONNECTED;
                case 3:
                    return TOPIC_SET;
            }
        }
    }
}
