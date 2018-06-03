package shayne.even.prisonerssandpit.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.sql.Date;

import static shayne.even.prisonerssandpit.models.PrisonerPerformanceScore.PRISONER;

/**
 * Data model that holds the performance scores of a Prisoner Agent
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

    @ColumnInfo(name = "created_at")
    private Date mCreatedAt;

    /**
     * Creates a new PrisonerPerformanceScore model
     * @param prisoner the id of the prisoner the scores are for
     * @param coopScore the score the prisoner got against a cooperative prisoner agent
     * @param betrayScore the score the prisoner got against a betraying prisoner agent
     * @param titForTatScore the score the prisoner got against an agent using a tit for tat
     *                       strategy
     */
    public PrisonerPerformanceScore(long prisoner, int coopScore, int betrayScore,
                                    int titForTatScore) {
        mPrisoner = prisoner;
        mCoopScore = coopScore;
        mBetrayScore = betrayScore;
        mTitForTatScore = titForTatScore;
        mCreatedAt = new Date(new java.util.Date().getTime());
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

    public Date getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        mCreatedAt = createdAt;
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
