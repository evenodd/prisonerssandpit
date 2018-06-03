package shayne.even.prisonerssandpit.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import java.util.List;

import shayne.even.prisonerssandpit.models.QTable;
import shayne.even.prisonerssandpit.models.QTableRow;

/**
 * Data Access Object used to interact with Q Table Models
 */

@Dao
public interface QTableDao {
    /**
     * Inserts a new Model into the q_table table
     * @param qTable the model to insert
     * @return the new id of the Q Table
     */
    @Insert
    long insertQTable(QTable qTable);
}
