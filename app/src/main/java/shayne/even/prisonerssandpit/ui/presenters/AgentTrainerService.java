package shayne.even.prisonerssandpit.ui.presenters;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class AgentTrainerService extends IntentService {

    private static final String PRISONER_ID_EXTRA = "prisoner_id_extra";

    public AgentTrainerService() {
        super("AgentTrainerService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        long id = intent.getLongExtra(PRISONER_ID_EXTRA, -1);
        Log.i("seven", "Prisoner id in service is: " + id);
    }
}
