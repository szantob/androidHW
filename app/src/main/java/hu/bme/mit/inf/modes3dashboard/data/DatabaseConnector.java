package hu.bme.mit.inf.modes3dashboard.data;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import hu.bme.mit.inf.modes3dashboard.fragments.TrainListFragment;

public class DatabaseConnector {
    private static boolean initialized = false;
    private ModesDatabase database;
    private static DatabaseConnector instance;
    private DatabaseConnector(Context context){
        database = Room.databaseBuilder(context,ModesDatabase.class,"modesDatabase").build();

    }

    public static void initialize(Context context){
        if(initialized) return;
        instance = new DatabaseConnector(context);
        initialized = true;
    }
    public static DatabaseConnector getInstance(){
        return instance;
    }

    public void createTrain(final Train newTrain) {
        new AsyncTask<Void, Void, Train>() {

            @Override
            protected Train doInBackground(Void... voids) {
                newTrain.id = database.TrainDao().insert(newTrain);
                return newTrain;
            }

            @Override
            protected void onPostExecute(Train train){}
        }.execute();
    }
    public void loadTrains(TrainDataReader reader) {
        new AsyncTask<TrainDataReader, Void, List<Train>>() {
            TrainDataReader reader;

            @Override
            protected List<Train> doInBackground(TrainDataReader... trainDataReaders) {
                reader = trainDataReaders[0];
                return database.TrainDao().getAll();
            }

            @Override
            protected void onPostExecute(List<Train> trains) {
                reader.getTrainData(trains);
            }
        }.execute(reader);
    }
    public interface TrainDataReader{
        public void getTrainData(List<Train> trains);
    }

}
