package shayne.even.prisonerssandpit.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static shayne.even.prisonerssandpit.models.PrisonerPerformanceScore.PRISONER;

/**
 * Created by Shayne Even on 7/05/2018.
 */

@Entity(tableName = "prisoner_performance_score",
    foreignKeys = @ForeignKey(
            entity = Prisoner.class,
            parentColumns = Prisoner.UID,
            childColumns = PRISONER
    ),
    indices = {@Index(PRISONER)}
)
public class PrisonerPerformanceScore {

    public static final String PRISONER = "prisoner";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    private long mUid;

    @ColumnInfo(name = PRISONER)
    private long mPrisoner;

    @ColumnInfo(name = "coop_score")
    private int mCoopScore;

    @ColumnInfo(name = "betray_score")
    private int mBetrayScore;

    @ColumnInfo(name = "tit_fot_tat_score")
    private int mTitForTatScore;

    public PrisonerPerformanceScore(long prisoner, int coopScore, int betrayScore,
                                    int titForTatScore) {
        mPrisoner = prisoner;
        mCoopScore = coopScore;
        mBetrayScore = betrayScore;
        mTitForTatScore = titForTatScore;
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

    public int getCoopScore() {
        return mCoopScore;
    }

    public void setCoopScore(int coopScore) {
        mCoopScore = coopScore;
    }

    public int getBetrayScore() {
        return mBetrayScore;
    }

    public void setBetrayScore(int betrayScore) {
        mBetrayScore = betrayScore;
    }

    public int getTitForTatScore() {
        return mTitForTatScore;
    }

    public void setTitForTatScore(int titForTatScore) {
        mTitForTatScore = titForTatScore;
    }

    @Override
    public String toString() {
        return "PrisonerPerformanceScore{" +
                "mUid=" + mUid +
                ", mPrisoner=" + mPrisoner +
                ", mCoopScore=" + mCoopScore +
                ", mBetrayScore=" + mBetrayScore +
                ", mTitForTatScore=" + mTitForTatScore +
                '}';
    }
}
