package hu.bme.mit.inf.modes3dashboard.controllableElements;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(
        entities = {Train.class/*,Section.class,Turnout.class*/},
        version = 1
)
//@TypeConverters(value = {Train.Category.class})
public abstract class ModesDatabase extends RoomDatabase {
    public abstract TrainDAO trainDao();
}
