package hu.bme.mit.inf.modes3dashboard.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface TrainDao {
    @Query("SELECT * FROM trains")
    List<Train> getAll();

    @Insert
    long insert(Train train);

    @Update
    void update(Train train);

    @Delete
    void deleteItem(Train train);
}
