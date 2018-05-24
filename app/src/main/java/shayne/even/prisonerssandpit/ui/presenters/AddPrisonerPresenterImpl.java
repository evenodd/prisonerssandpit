package shayne.even.prisonerssandpit.ui.presenters;

import android.content.Context;
import android.text.TextUtils;

import java.lang.ref.WeakReference;

import shayne.even.prisonerssandpit.tasks.prisoner.AddPrisonerAsyncTask;
import shayne.even.prisonerssandpit.tasks.prisoner.OnAddPrisonerFinishedListener;
import shayne.even.prisonerssandpit.ui.views.AddPrisonerView;

/**
 * Created by Shayne Even on 13/05/2018.
 */

public class AddPrisonerPresenterImpl implements AddPrisonerPresenter,
        OnAddPrisonerFinishedListener {

     private AddPrisonerView mView;

     public AddPrisonerPresenterImpl(AddPrisonerView view) {
          mView = view;
     }

     @Override
     public void addPrisoner(String name, String alpha, String gamma, Context context) {
         if (mView != null) mView.showProgress();

         if (TextUtils.isEmpty(name)) {
             onNameError();
             return;
         }

         if (!isValidVariable(alpha)) {
             onAlphaError();
             return;
         }

         if (!isValidVariable(gamma)) {
             onGammaError();
             return;
         }

         new AddPrisonerAsyncTask(
                 name,
                 Double.parseDouble(alpha),
                 Double.parseDouble(gamma),
                 this,
                 new WeakReference<>(context)
         ).execute();
      }

    private boolean isValidVariable(String val) {
        double parsedVal;
        try {
            parsedVal = Double.parseDouble(val);
        } catch (NumberFormatException e) {
            return false;
        }
        return !(parsedVal < 0 || parsedVal > 1);
    }

    @Override
    public void onNameError() {
        if (mView == null) return;
        mView.setNameErrorMessage();
        mView.hideProgress();
    }

    @Override
    public void onAlphaError() {
        if (mView == null) return;
         mView.setAlphaErrorMessage();
         mView.hideProgress();
    }

    @Override
    public void onGammaError() {
        if (mView == null) return;
         mView.setGammaErrorMessage();
         mView.hideProgress();
    }

    @Override
    public void onSuccess(long id) {
        if (mView == null) return;
        mView.returnResults(id);
    }
}
