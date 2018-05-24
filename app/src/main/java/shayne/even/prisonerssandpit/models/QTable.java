package shayne.even.prisonerssandpit.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;

import shayne.even.prisonerssandpit.database.AppDatabase;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Shayne Even on 1/05/2018.
 */
@Entity(tableName = "q_table")
public class QTable {
    public static final String UID = "uid";
    private static final int ITERATIONS = 10;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = UID)
    private long mUid;

    public long getUid() {
        return mUid;
    }

    public void setUid(long uid) {
        mUid = uid;
    }

    /**
     * Inserts a new qTable model into the database and inserts all the rows for the qtable into
     * the qTableRow relation
     * @param db an instance of the app's database
     * @return the uid of the qTable
     */
    public static long createQTableWithRows(AppDatabase db) {
        long id = db.qTableDao().insertQTable(new QTable());

        int nbOfStates = calculateNumberOfStates();

        ArrayList<QTableRow> qTableRows = new ArrayList<>();

        for(int i = 0; i < calculateNumberOfStates(); i++) {
            qTableRows.add(new QTableRow(id, i, 0, 0));
        }
        db.qTableDao().insertQTableRows(qTableRows);
        return id;
    }

    private static int calculateNumberOfStates() {
        int stateCount = 0;

        for (int i = 0; i < ITERATIONS ; i++) {
            stateCount += Math.pow(4, i);
        }
        return stateCount;
    }

}
