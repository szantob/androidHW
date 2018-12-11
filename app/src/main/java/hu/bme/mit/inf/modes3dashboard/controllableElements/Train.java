package hu.bme.mit.inf.modes3dashboard.controllableElements;


public class Train{
    private long id;
    private long uid;
    private String name;

    public Train(){

    }
    public Train(long id, long uid, String name){
        this.id=id;
        this.uid=uid;
        this.name=name;
    }

    public void setId(long aLong) {
        id=aLong;
    }

    public long getId() {
        return id;
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
