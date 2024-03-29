package shayne.even.prisonerssandpit.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import shayne.even.prisonerssandpit.models.Prisoner;
import shayne.even.prisonerssandpit.models.PrisonerPerformanceScore;
import shayne.even.prisonerssandpit.models.PrisonerStatus;
import shayne.even.prisonerssandpit.models.QTable;
import shayne.even.prisonerssandpit.models.QTableRow;

import static shayne.even.prisonerssandpit.database.AppDatabase.DB_VERSION;

/**
 * Provides a database connection to the apps local SQLite database
 */

@Database(
        entities = {
                Prisoner.class,
                QTable.class,
                QTableRow.class,
                PrisonerPerformanceScore.class,
                PrisonerStatus.class
        },
        version = DB_VERSION
)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    static final int DB_VERSION = 1;
    public static final String DB_NAME = "PRISONER_DB";

    /** Provides the prisoner data access object */
    public abstract PrisonerDao prisonerDao();

    /** Provides the Q table data access object */
    public abstract QTableDao qTableDao();

    /** Provides the Q table with rows data access object */
    public abstract QTableWithRowsDao qTableWithRowsDao();

    /** Provides the prisoner performance score data access object */
    public abstract PrisonerPerformanceScoreDao prisonerPerformanceScoreDao();

    /** Provides the prisoner status data access object */
    public abstract PrisonerStatusDao prisonerStatusDao();


    /** Provides the prisoner Q table row data access object */
    public abstract QTableRowDao qTableRowDao();
}
