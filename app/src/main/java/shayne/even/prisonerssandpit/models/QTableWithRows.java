package shayne.even.prisonerssandpit.models;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Relation;
import android.util.SparseArray;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Shayne Even on 1/05/2018.
 */

public class QTableWithRows {
    @Embedded
    public QTable qTable;

    @Relation(parentColumn = QTable.UID, entityColumn = QTableRow.Q_TABLE)
    public List<QTableRow> rows;

    @Ignore
    private SparseArray<QTableRow> mRowsMappedByState;

    public SparseArray<QTableRow> getRowsIndexedByState() {
        if (mRowsMappedByState == null) {
            mRowsMappedByState = new SparseArray<>();
            for (QTableRow row : rows) {
                mRowsMappedByState.append(row.getState(), row);
            }
        }
        return mRowsMappedByState;
    }
}
