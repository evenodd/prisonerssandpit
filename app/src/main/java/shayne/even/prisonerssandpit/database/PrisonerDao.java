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
 * Created by Shayne Even on 30/04/2018.
 */
@Dao
public interface PrisonerDao {
    @Query("SELECT * FROM prisoner")
    List<Prisoner> getAll();

    @Query("SELECT * FROM prisoner WHERE uid IN (:ids)")
    List<Prisoner> loadAllByIds(int [] ids);

    @Query("SELECT * FROM prisoner WHERE uid = :id")
    Prisoner getPrisoner(long id);

    @Query("SELECT * FROM prisoner WHERE name LIKE :name LIMIT 1")
    Prisoner findByName(String name);

    @Insert
    long insertPrisoner(Prisoner prisoner);
}



