package shayne.even.prisonerssandpit.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Data model for the rows in a Q table
 */
@Entity(tableName = "q_table_row")
public class QTableRow {
    public static final String Q_TABLE = "q_table";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    private long mUid;

    @ColumnInfo(name = Q_TABLE)
    private long mQTable;

    @ColumnInfo(name = "state")
    private final int mState;

    @ColumnInfo(name = "betray_q_value")
    private double mBetrayQValue;

    @ColumnInfo(name = "stay_q_value")
    private double mStayQValue;

    /**
     * Creates a new Q table row.
     * @param qTable the id of the Q table the row belongs to
     * @param state the state the row is for
     * @param betrayQValue the q value for performing a betray action for the given state
     * @param stayQValue the q value for performing a stay action for the given state
     */
    public QTableRow(long qTable, int state, double betrayQValue, double stayQValue) {
        mQTable = qTable;
        mState = state;
        mBetrayQValue = betrayQValue;
        mStayQValue = stayQValue;
    }

    public long getUid() {
        return mUid;
    }

    public void setUid(long uid) {
        mUid = uid;
    }

    public double getBetrayQValue() {
        return mBetrayQValue;
    }

    public void setBetrayQValue(double betrayQValue) {
        mBetrayQValue = betrayQValue;
    }

    public double getStayQValue() {
        return mStayQValue;
    }

    public void setStayQValue(double stayQValue) {
        mStayQValue = stayQValue;
    }

    public int getState() {
        return mState;
    }

    public long getQTable() {
        return mQTable;
    }

    @Override
    public String toString() {
        return "QTableRow{" +
                "mUid=" + mUid +
                ", mQTable=" + mQTable +
                ", mState=" + mState +
                ", mBetrayQValue=" + mBetrayQValue +
                ", mStayQValue=" + mStayQValue +
                '}';
    }
}
