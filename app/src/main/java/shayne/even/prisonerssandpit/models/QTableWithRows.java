package shayne.even.prisonerssandpit.models;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Relation;
import android.util.SparseArray;

import java.util.HashMap;
import java.util.List;

/**
 * Data Model that holds a Q table. Model also contains a list of all QTableRows belonging to the
 * table
 */

public class QTableWithRows {
    @Embedded
    public QTable qTable;

    @Relation(parentColumn = QTable.UID, entityColumn = QTableRow.Q_TABLE)
    public List<QTableRow> rows;

    @Ignore
    private SparseArray<QTableRow> mRowsMappedByState;

    /**
     * Returns the rows as a HashMap indexed by their states.
     * @return
     */
    SparseArray<QTableRow> getRowsIndexedByState() {
        if (mRowsMappedByState == null) {
            mRowsMappedByState = new SparseArray<>();
            for (QTableRow row : rows) {
                mRowsMappedByState.append(row.getState(), row);
            }
        }
        return mRowsMappedByState;
    }
}
