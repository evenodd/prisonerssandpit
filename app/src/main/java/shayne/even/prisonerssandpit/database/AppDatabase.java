package shayne.even.prisonerssandpit.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import shayne.even.prisonerssandpit.models.Prisoner;
import shayne.even.prisonerssandpit.models.PrisonerPerformanceScore;
import shayne.even.prisonerssandpit.models.QTable;
import shayne.even.prisonerssandpit.models.QTableRow;

import static shayne.even.prisonerssandpit.database.AppDatabase.DB_VERSION;

/**
 * Created by Shayne Even on 30/04/2018.
 */

@Database(
        entities = {
                Prisoner.class,
                QTable.class,
                QTableRow.class,
                PrisonerPerformanceScore.class,
        },
        version = DB_VERSION
)
public abstract class AppDatabase extends RoomDatabase {
    static final int DB_VERSION = 1;
    public static final String DB_NAME = "PRISONER_DB";

    public abstract PrisonerDao prisonerDao();
    public abstract QTableDao qTableDao();
    public abstract QTableWithRowsDao qTableWithRowsDao();
    public abstract PrisonerPerformanceScoreDao prisonerPerformanceScoreDao();
}
