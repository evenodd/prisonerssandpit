package shayne.even.prisonerssandpit.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import shayne.even.prisonerssandpit.models.QTableWithRows;

/**
 * The Data Access Object used for interacting with the QTableWithRows Models
 */
@Dao
public interface QTableWithRowsDao {
    /**
     * Queries for a Q Table and returns it as a QTableWithRowsModel
     * @param id the id of the QTable in the QTableWithRows Model
     * @return the QTableWithRows model
     */
    @Query("SELECT * FROM q_table where uid = :id")
    @Transaction
    QTableWithRows getQTable(long id);
}

