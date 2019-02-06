package com.example.notificeuserapp.Base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

public abstract class BaseFragment extends Fragment implements IView {

    private BaseActivity baseActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof BaseActivity) {
            baseActivity = (BaseActivity) context;
        }

        setRetainInstance(true);
    }


    @Override
    public void onDetach() {
        super.onDetach();
        baseActivity = null;
    }


    @Override
    public void showLoading() {
        if (activityIsNotNull()) {
            getBaseActivity().showLoading();
        }
    }

    @Override
    public void hideLoading() {
        if (activityIsNotNull()) {
            getBaseActivity().hideLoading();
        }
    }

    @Override
    public void onError(int resId) {
        if (activityIsNotNull()) {
            getBaseActivity().onError(resId);
        }
    }

    private boolean activityIsNotNull() {
        return baseActivity != null;
    }

    @Override
    public void onError(String message) {
        if (activityIsNotNull()) {
            getBaseActivity().onError(message);
        }
    }

    @Override
    public void showMessage(int resId) {
        if (activityIsNotNull()) {
            getBaseActivity().showMessage(resId);
        }
    }

    @Override
    public void showMessage(String message) {
        if (activityIsNotNull()) {
            getBaseActivity().onError(message);
        }
    }

    @Override
    public boolean isNetworkConnected() {
        if (activityIsNotNull()) {
            return getBaseActivity().isNetworkConnected();
        }
        return false;
    }

    @Override
    public void hideKeyboard() {
        if (activityIsNotNull()) {
            getBaseActivity().hideKeyboard();
        }
    }

    @Override
    public void openActivityOnLogout() {
        if (activityIsNotNull()) {
            getBaseActivity().openActivityOnLogout();
        }
    }


    protected void setToolbarTitle(String title) {
        if (getBaseActivity() != null) {
            getBaseActivity().setToolbarTitle(title);
        }
    }

    protected void setToolbarTitle(@StringRes int titleRes) {
        setToolbarTitle(getString(titleRes));
    }


    @Nullable
    public BaseActivity getBaseActivity() {
        return baseActivity;
    }


    public boolean onBackPressed() {
        return false;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        onDestroyFragment();
    }

    protected abstract void onDestroyFragment();
}
