package hu.bme.mit.inf.modes3dashboard.controllableElements;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface TrainDAO {
    @Query("SELECT * FROM trains")
    List<Train> getAll();

    @Insert
    long insert(Train shoppingItems);

    @Update
    void update(Train shoppingItem);

    @Delete
    void deleteItem(Train shoppingItem);
}
