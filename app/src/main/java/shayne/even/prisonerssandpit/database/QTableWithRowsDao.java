package shayne.even.prisonerssandpit.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import shayne.even.prisonerssandpit.models.QTableWithRows;

/**
 * Created by Shayne Even on 7/05/2018.
 */
@Dao
public interface QTableWithRowsDao {
    @Query("SELECT * FROM q_table where uid = :id")
    @Transaction
    QTableWithRows getQTable(long id);
}

