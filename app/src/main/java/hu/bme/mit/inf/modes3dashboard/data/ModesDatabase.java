package hu.bme.mit.inf.modes3dashboard.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(
        entities = {Train.class},
        version = 1
)
public abstract class ModesDatabase extends RoomDatabase {
    public abstract TrainDao TrainDao();
}