package shayne.even.prisonerssandpit.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import shayne.even.prisonerssandpit.models.PrisonerStatus;

/**
 * The Data Access Object used to interact with the Prisoner Status models
 */

@Dao
public interface PrisonerStatusDao {
    /**
     * Inserts a new Prisoner Status into the table
     * @param prisonerStatus the model to insert
     * @return the id of the new Status Model
     */
    @Insert
    long insert(PrisonerStatus prisonerStatus);

    /**
     * Queries for the specified prisoner's status
     * @param prisoner the id of the prisoner
     * @return a LiveData object that hold the status model
     */
    @Query("SELECT * FROM prisoner_status WHERE prisoner = :prisoner")
    LiveData<PrisonerStatus> getPrisonerStatus(long prisoner);

    /**
     * Updates the status of the specified prisoner
     * @param prisonerId the id of the prisoner to update
     * @param status the new status value for the Status model
     */
    @Query("UPDATE prisoner_status " +
            "SET status = :status " +
            "WHERE prisoner = :prisonerId")
    void updateStatus(long prisonerId, String status);
}
