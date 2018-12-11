package hu.bme.mit.inf.modes3dashboard.controllableElements;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "turnout")
public class Turnout{

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "uid")
    private long uid;
    @ColumnInfo(name = "name")
    private String name;


    public Turnout(){

    }
    public Turnout(long id, String name){
        this.id=id;
        this.name=name;
    }
}
