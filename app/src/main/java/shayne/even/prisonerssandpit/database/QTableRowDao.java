package shayne.even.prisonerssandpit.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import shayne.even.prisonerssandpit.models.QTableRow;

/**
 * Created by Shayne Even on 28/05/2018.
 */

@Dao
public interface QTableRowDao {
    @Query("SELECT q_table_row.* " +
            "FROM q_table_row " +
            "JOIN prisoner ON q_table_row.q_table = prisoner.q_table " +
            "WHERE state = :state " +
            "AND prisoner.uid = :prisoner;")
    QTableRow getRow(long prisoner, int state);


}