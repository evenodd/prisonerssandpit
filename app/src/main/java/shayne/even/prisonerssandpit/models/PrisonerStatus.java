package shayne.even.prisonerssandpit.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Data model that holds the status of a prisoner agent*/

@Entity(
        tableName = "prisoner_status",
        foreignKeys = @ForeignKey(
                entity = Prisoner.class,
                parentColumns = Prisoner.UID,
                childColumns = PrisonerStatus.PRISONER
        ),
        indices = {@Index(PrisonerStatus.PRISONER)}
)
public class PrisonerStatus {

    public static final String PRISONER = "prisoner";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    private long mUid;

    @ColumnInfo(name = PRISONER)
    private long mPrisoner;

    @ColumnInfo(name = "status")
    private String mStatus;

    /**
     * Creates a PrisonerStatus data model.
     * @param prisoner the id of the prisoner the status is for
     * @param status the status of the prisoner
     */
    public PrisonerStatus(long prisoner, String status) {
        mPrisoner = prisoner;
        mStatus = status;
    }

    public long getUid() {
        return mUid;
    }

    public void setUid(long uid) {
        mUid = uid;
    }

    public long getPrisoner() {
        return mPrisoner;
    }

    public void setPrisoner(long prisoner) {
        mPrisoner = prisoner;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }
}
