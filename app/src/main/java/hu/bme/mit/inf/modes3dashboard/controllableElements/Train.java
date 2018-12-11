package hu.bme.mit.inf.modes3dashboard.controllableElements;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "trains")
public class Train{

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "uid")
    private long uid;
    @ColumnInfo(name = "name")
    private String name;

    public Train(){

    }
    public Train(long id, long uid, String name){
        this.id=id;
        this.uid=uid;
        this.name=name;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
