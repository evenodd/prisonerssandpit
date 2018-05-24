package shayne.even.prisonerssandpit.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Shayne Even on 1/05/2018.
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
}