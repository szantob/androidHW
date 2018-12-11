package hu.bme.mit.inf.modes3dashboard.controllableElements.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import hu.bme.mit.inf.modes3dashboard.controllableElements.Turnout;

public class ElementsOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "modes3ElementsDatabase.db";

    private static final String TRAIN_TABLE_NAME = "trains";
    private static final String SECTION_TABLE_NAME = "sections";
    private static final String TURNOUT_TABLE_NAME = "turnouts";


    public ElementsOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        TrainTable.onCreate(db);
        SectionTable.onCreate(db);
        TurnoutTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        TrainTable.onUpgrade(db, oldVersion, newVersion);
        SectionTable.onUpgrade(db, oldVersion, newVersion);
        TurnoutTable.onUpgrade(db, oldVersion, newVersion);
    }
}
