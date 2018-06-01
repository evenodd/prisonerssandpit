package shayne.even.prisonerssandpit.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import shayne.even.prisonerssandpit.models.PrisonerStatus;

/**
 * Created by Shayne Even on 28/05/2018.
 */

@Dao
public interface PrisonerStatusDao {
    @Insert
    long insert(PrisonerStatus prisonerStatus);

    @Query("SELECT * FROM prisoner_status WHERE prisoner = :prisoner")
    LiveData<PrisonerStatus> getPrisonerStatus(long prisoner);

    @Query("UPDATE prisoner_status " +
            "SET status = :status " +
            "WHERE prisoner = :prisonerId")
    void updateStatus(long prisonerId, String status);
}
