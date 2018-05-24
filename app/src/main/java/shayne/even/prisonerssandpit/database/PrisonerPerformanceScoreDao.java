package shayne.even.prisonerssandpit.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import shayne.even.prisonerssandpit.models.PrisonerPerformanceScore;

/**
 * Created by Shayne Even on 7/05/2018.
 */

@Dao
public interface PrisonerPerformanceScoreDao {

    @Query("SELECT * FROM prisoner_performance_score WHERE prisoner = :prisonerId")
    PrisonerPerformanceScore getPrisonersScore(long prisonerId);

    @Insert
    long insertPrisonerPerformanceScore(PrisonerPerformanceScore prisonerPerformanceScore);

    @Update
    void updatePrisonerScore(PrisonerPerformanceScore prisonerPerformanceScore);

}
