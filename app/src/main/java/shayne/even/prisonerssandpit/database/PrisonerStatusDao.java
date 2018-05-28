package shayne.even.prisonerssandpit.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import shayne.even.prisonerssandpit.models.PrisonerStatus;

/**
 * Created by Shayne Even on 28/05/2018.
 */

@Dao
public interface PrisonerStatusDao {
    @Insert
    long insert(PrisonerStatus prisonerStatus);

    @Query("SELECT * FROM prisoner_status WHERE prisoner = :prisoner")
    PrisonerStatus getPrisonerStatus(long prisoner);
}
