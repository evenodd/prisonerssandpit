package shayne.even.prisonerssandpit.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.content.Context;

import java.util.Random;

import shayne.even.prisonerssandpit.PrisonersSandpitApp;
import shayne.even.prisonerssandpit.di.component.DaggerModelComponent;
import shayne.even.prisonerssandpit.di.component.ModelComponent;

import static shayne.even.prisonerssandpit.models.Prisoner.Q_TABLE;
import static shayne.even.prisonerssandpit.rl.environments.PrisonersDilemma.BETRAY;
import static shayne.even.prisonerssandpit.rl.environments.PrisonersDilemma.STAY;

/**
 * Data model for Q Learning Agents that can interact in Prisoner Dilemmas.
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

    /**
     * Creates a new Prisoner Model. After initiated the model should be inserted into the database
     * to generate its uid.
     * @param name the name of the prisoner
     * @param qTable the id of its Q Table
     * @param alpha the alpha constant to use in its leaning algorithm
     * @param gamma the gamma constant to use in its learning algorithm
     */
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

    /**
     * Queries the database for the Q table row with the specified state and returns the action with
     * the largest Q value. If the actions have the same value the actio if chosen randomly.
     * @param context the app context used to get the AppDatabaseInstance
     * @param state the PrisonerDilemma state to get an action for
     * @return PrisonersDilemma.STAY(0) or PrisonersDilemma.BETRAY(1)
     */
    public int getAction(Context context, int state) {

        QTableRow row = getComponent(context)
                .getAppDatabase()
                .qTableRowDao()
                .getRow(mUid, state);

        if (row.getStayQValue() == row.getBetrayQValue()) return new Random().nextInt(2);
        return row.getStayQValue() > row.getBetrayQValue() ? STAY : BETRAY;
    }

    /**
     * Holds a prisoner model's respective QTableWithRowsModel. The Model is lazy loaded the first
     * time it is requested.
     */
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
            return getQTable(context).getRowsIndexedByState().get(state);
        }

        void updateQTable(Context context) {
            if (mQTableWithRows != null) {
                getComponent(context)
                        .getAppDatabase()
                        .qTableRowDao()
                        .updateQTableRows(mQTableWithRows.rows);
            }
        }
    }

    private double getQValue(int state, int action, Context context) {
        QTableRow row = mQTableHolder.getRow(state, context);
        if (action == BETRAY) return row.getBetrayQValue();
        return row.getStayQValue();
    }

    private double getMaxQ(int state, Context context) {
        QTableRow row = mQTableHolder.getRow(state, context);
        if (row.getStayQValue() > row.getBetrayQValue()) return row.getStayQValue();
        return row.getBetrayQValue();
    }


    /**
     * Calculates the "quality" of the specified action given the specified state and stores the
     * value in the agent's Q Table. The q table will be lazy loaded from the database the first
     * time this method is called.
     * @param state the state the agent performed the action in
     * @param nextState the next state the agent will be in after performing the action
     * @param reward the reward the agent received for the given action
     * @param action the action the agent performed
     * @param context the context of the application to connect to the database with
     * @param finalState a flag that signifies if the agent is in a final state and wont
     *                   transition into a new state
     */
    public void learn(int state, int nextState, int reward, int action, Context context,
                      boolean finalState) {
        double q = getQValue(state, action, context);
        double maxQ = finalState ? 0 : getMaxQ(nextState, context);
        double value = q + mAlpha * (reward + mGamma * maxQ - q);

        if (action == BETRAY) {
            mQTableHolder.getRow(state, context).setBetrayQValue(value);
        }
        else {
            mQTableHolder.getRow(state, context).setStayQValue(value);
        }
    }

    /**
     * Saves the new "learned" q values into the database. Will do nothing if the 'learn' method
     * hasn't been called.
     * @param context the application context to connect to the database with
     */
    public void saveQTable(Context context) {
        mQTableHolder.updateQTable(context);
    }
}
