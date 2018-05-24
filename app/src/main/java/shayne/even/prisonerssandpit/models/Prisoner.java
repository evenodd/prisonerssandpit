package shayne.even.prisonerssandpit.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.content.Context;

import java.util.Locale;
import java.util.Random;

import shayne.even.prisonerssandpit.PrisonersSandpitApp;
import shayne.even.prisonerssandpit.di.component.DaggerModelComponent;
import shayne.even.prisonerssandpit.di.component.ModelComponent;

import static shayne.even.prisonerssandpit.models.Prisoner.Q_TABLE;
import static shayne.even.prisonerssandpit.rl.episodes.PrisonersDilemma.BETRAY;
import static shayne.even.prisonerssandpit.rl.episodes.PrisonersDilemma.STAY;

/**
 * Created by Shayne Even on 23/04/2018.
 */

@Entity(
        tableName = "prisoner",
        foreignKeys = @ForeignKey(
                entity = QTable.class,
                parentColumns = QTable.UID,
                childColumns = Q_TABLE
        ),
        indices = {@Index(Q_TABLE)}
)
public class Prisoner {
    public static final String UID = "uid";
    public static final String Q_TABLE = "q_table";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = UID)
    private long mUid;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = Q_TABLE)
    private long mQTable;

    @ColumnInfo(name = "alpha")
    private double mAlpha;

    @ColumnInfo(name = "gamma")
    private double mGamma;

    @Ignore
    private QTableHolder mQTableHolder;

    @Ignore
    private ModelComponent mModelComponent;

    public Prisoner(String name, long qTable, double alpha, double gamma) {
        mAlpha = alpha;
        mGamma = gamma;
        mName = name;
        mQTable = qTable;
        mQTableHolder = new QTableHolder();
    }

    public String getName() {
        return mName;
    }

    public long getUid() {
        return mUid;
    }

    public void setUid(long uid) {
        this.mUid = uid;
    }

    public long getQTable() {
        return mQTable;
    }

    public void setQTable(long QTable) {
        mQTable = QTable;
    }

    public double getAlpha() {
        return mAlpha;
    }

    public void setAlpha(double alpha) {
        mAlpha = alpha;
    }

    public double getGamma() {
        return mGamma;
    }

    public void setGamma(double gamma) {
        mGamma = gamma;
    }

    private ModelComponent getComponent(Context context) {
        if (mModelComponent == null) {
            mModelComponent = DaggerModelComponent.builder()
                    .applicationComponent(PrisonersSandpitApp.get(context).getComponent())
                    .build();
        }
        return  mModelComponent;
    }

    public int getAction(Context context, int state) {
        QTableRow row = mQTableHolder.getRow(state, context);
        if (row.getStayQValue() == row.getBetrayQValue()) return new Random().nextInt(2);
        return row.getStayQValue() > row.getBetrayQValue() ? STAY : BETRAY;
    }

    private class QTableHolder {
        private QTableWithRows mQTableWithRows;

        QTableWithRows getQTable(Context context) {
            if (mQTableWithRows == null) {
                mQTableWithRows = getComponent(context)
                        .getAppDatabase()
                        .qTableWithRowsDao()
                        .getQTable(mQTable);
            }
            return mQTableWithRows;
        }

        QTableRow getRow(int state, Context context) {
            for (QTableRow row : mQTableHolder.getQTable(context).rows) {
                if (row.getState() == state) return row;
            }
            throw new IllegalArgumentException(String.format(
                    Locale.ENGLISH,
                    "Invalid state %d",
                    state)
            );
        }

        void updateQTable(Context context) {
            getComponent(context)
                    .getAppDatabase()
                    .qTableDao()
                    .updateQTableRows(mQTableWithRows.rows);
        }
    }

    public double getQValue(int state, int action, Context context) {
        QTableRow row = mQTableHolder.getRow(state, context);
        if (action == BETRAY) return row.getBetrayQValue();
        return row.getStayQValue();
    }

    public double getMaxQ(int state, Context context) {
        QTableRow row = mQTableHolder.getRow(state, context);
        if (row.getStayQValue() > row.getBetrayQValue()) return row.getStayQValue();
        return row.getBetrayQValue();
    }

    public void learn(int startState, int state, int reward, int action, Context context) {
        double q = getQValue(startState, action, context);
        double value = q + mAlpha * (reward + mGamma * getMaxQ(state, context) - q);

        if (action == BETRAY) {
            mQTableHolder.getRow(startState, context).setBetrayQValue(value);
        }
        else {
            mQTableHolder.getRow(startState, context).setStayQValue(value);
        }
    }

    public void saveQTable(Context context) {
        mQTableHolder.updateQTable(context);
    }
}
