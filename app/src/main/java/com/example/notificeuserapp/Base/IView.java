package com.example.notificeuserapp.Base;

import android.support.annotation.StringRes;

public interface IView {
    void showLoading();

    void hideLoading();

    void onError(@StringRes int resId);

    void onError(String message);

    void showMessage(@StringRes int resId);

    void showMessage(String message);

    boolean isNetworkConnected();

    void hideKeyboard();

    void openActivityOnLogout();

}

