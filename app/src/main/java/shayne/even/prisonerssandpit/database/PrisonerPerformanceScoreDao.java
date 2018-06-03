package shayne.even.prisonerssandpit.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import shayne.even.prisonerssandpit.models.PrisonerPerformanceScore;

/**
 * The Data Access Object used to interact with Prisoner Performance Score Models
 */

@Dao
public interface PrisonerPerformanceScoreDao {
    /**
     * Queries for the performance score of the specified prisoner
     * @param prisonerId the id of the prisoner the score is for
     * @return the Performance Score model
     */
    @Query("SELECT * " +
            "FROM prisoner_performance_score " +
            "WHERE prisoner = :prisonerId " +
            "ORDER BY created_at DESC " +
            "LIMIT 1;")
    PrisonerPerformanceScore getPrisonersScore(long prisonerId);

    /**
     * Inserts the specified model into the prisoner_performance_score table
     * @param prisonerPerformanceScore the model to insert
     * @return the new id of the model
     */
    @Insert
    long insertPrisonerPerformanceScore(PrisonerPerformanceScore prisonerPerformanceScore);

    /**
     * Updates the specified model from the database
     * @param prisonerPerformanceScore the model to update
     */
    @Update
    void updatePrisonerScore(PrisonerPerformanceScore prisonerPerformanceScore);

}
