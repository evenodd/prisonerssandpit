package shayne.even.prisonerssandpit.models;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

/**
 * Created by Shayne Even on 1/05/2018.
 */

public class QTableWithRows {
    @Embedded
    public QTable qTable;

    @Relation(parentColumn = QTable.UID, entityColumn = QTableRow.Q_TABLE)
    public List<QTableRow> rows;
}
