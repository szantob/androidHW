package hu.bme.mit.inf.modes3dashboard.controllableElements;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "section")
public class Section{

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "uid")
    private long uid;
    @ColumnInfo(name = "name")
    private String name;

    public Section(){

    }
    public Section(long id, String name){
        this.id=id;
        this.name=name;
    }
}
