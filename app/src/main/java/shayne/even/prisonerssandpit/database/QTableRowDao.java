package shayne.even.prisonerssandpit.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import shayne.even.prisonerssandpit.models.QTableRow;

/**
 * Data Access Object for interacting with Q Table Row Models.
 */

@Dao
public interface QTableRowDao {
    /**
     * Queries for the q table row the specified prisoner with the specified state
     * @param prisoner the prisoner model the q table belongs to
     * @param state the state the should query for
     * @return the resulting QTableRow model
     */
    @Query("SELECT q_table_row.* " +
            "FROM q_table_row " +
            "JOIN prisoner ON q_table_row.q_table = prisoner.q_table " +
            "WHERE state = :state " +
            "AND prisoner.uid = :prisoner;")
    QTableRow getRow(long prisoner, int state);

    /**
     * Inserts multiple Q Table row models into the q_table_row table
     * @param qTableRows the models to insert
     * @return the ids of the newly inserted rows
     */
    @Insert
    long[] insertQTableRows(List<QTableRow> qTableRows);

    /**
     * Updates multiple Q Table row models from the database
     * @param qTableRows the models to update
     */
    @Update
    void updateQTableRows(List<QTableRow> qTableRows);
}