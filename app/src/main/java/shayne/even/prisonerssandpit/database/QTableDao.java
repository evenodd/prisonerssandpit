package shayne.even.prisonerssandpit.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import java.util.List;

import shayne.even.prisonerssandpit.models.QTable;
import shayne.even.prisonerssandpit.models.QTableRow;

/**
 * Created by Shayne Even on 2/05/2018.
 */

@Dao
public interface QTableDao {
    @Insert
    long insertQTable(QTable qTable);

    @Insert
    long[] insertQTableRows(List<QTableRow> qTableRows);

    @Update
    void updateQTableRows(List<QTableRow> qTableRows);
}
