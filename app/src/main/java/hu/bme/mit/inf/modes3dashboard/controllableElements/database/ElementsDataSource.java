package hu.bme.mit.inf.modes3dashboard.controllableElements.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hu.bme.mit.inf.modes3dashboard.controllableElements.Train;

public class ElementsDataSource {
        private SQLiteDatabase database;
        private ElementsOpenHelper dbHelper;
        private String[] allTrainColumns = { TrainTable.COLUMN_ID,
            TrainTable.COLUMN_UID, TrainTable.COLUMN_NAME};
        /*private String[] trainColumns = { TrainTable.COLUMN_ID,
            TrainTable.COLUMN_UID, TrainTable.COLUMN_NAME};
        private String[] trainColumns = { TrainTable.COLUMN_ID,
            TrainTable.COLUMN_UID, TrainTable.COLUMN_NAME};*/

    public ElementsDataSource(Context context) {
        dbHelper = new ElementsOpenHelper(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
    }
    public Train createTrain(String aUID, String aName) {
        ContentValues values = new ContentValues();
        values.put(TrainTable.COLUMN_UID, aUID);
        values.put(TrainTable.COLUMN_NAME, aName);
        long insertId = database.insert(TrainTable.TABLE_NAME, null,
                values);
        Cursor cursor = database.query(TrainTable.TABLE_NAME,
                allTrainColumns, TrainTable.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Train newTrain = cursorToTrain(cursor);
        cursor.close();
        return newTrain;
    }
    private Train cursorToTrain(Cursor cursor) {
        Train train = new Train();
        train.setId(cursor.getLong(0));
        train.setUid(cursor.getLong(1));
        train.setName(cursor.getString(2));
        return train;
    }
    public void deleteTrain(Train train) {
        long id = train.getId();
        database.delete(TrainTable.TABLE_NAME, TrainTable.COLUMN_ID
                + " = " + id, null);
    }
    public List<Train> getAllTrains() {
        List<Train> trains = new ArrayList<Train>();
        Cursor cursor = database.query(TrainTable.TABLE_NAME,
                allTrainColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Train train = cursorToTrain(cursor);
            trains.add(train);
            cursor.moveToNext();
        }
        cursor.close();
        return trains;
    }
}
