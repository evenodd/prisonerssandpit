package shayne.even.prisonerssandpit.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import shayne.even.prisonerssandpit.models.Prisoner;
import shayne.even.prisonerssandpit.models.PrisonerPerformanceScore;
import shayne.even.prisonerssandpit.models.QTable;
import shayne.even.prisonerssandpit.models.QTableRow;

/**
 * The Data Access Object used to interact with Prisoner Models
 */
@Dao
public interface PrisonerDao {
    /**
     * Queries all prisoner models
     * @return list of all prisoners
     */
    @Query("SELECT * FROM prisoner")
    List<Prisoner> getAll();

    /**
     * Queries for prisoners by the specified ids
     * @param ids the ids of the prisoners to get
     * @return list of prisoners
     */
    @Query("SELECT * FROM prisoner WHERE uid IN (:ids)")
    List<Prisoner> loadAllByIds(int [] ids);

    /**
     * Queries for the prisoner with the specified id
     * @param id the id of the prisoner to get
     * @return the prisoner model
     */
    @Query("SELECT * FROM prisoner WHERE uid = :id")
    Prisoner getPrisoner(long id);

    /**
     * Queries for a list of all prisoners excluding the specified prisoner
     * @param excludedPrisoner the id of the prisoner to exclude from the list
     * @return list of all other prisoner models
     */
    @Query("SELECT * FROM prisoner WHERE uid != :excludedPrisoner")
    List<Prisoner> getWherePrisonerIsNot(long excludedPrisoner);

    /**
     * Saves the specified prisoner model into the prisoner table
     * @param prisoner the prisoner to insert
     * @return the id of the new table row
     */
    @Insert
    long insertPrisoner(Prisoner prisoner);
}



