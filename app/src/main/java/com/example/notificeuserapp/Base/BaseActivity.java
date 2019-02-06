package com.example.notificeuserapp.Base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


public abstract class BaseActivity extends AppCompatActivity implements IView {


    public static <T extends Activity> void startActivity(Context context, Class<T> tClass, Bundle bundle) {
        Intent intent = new Intent(context, tClass);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static <T extends Activity> void startActivity(Context context, Class<T> tClass) {
        Intent intent = new Intent(context, tClass);
        context.startActivity(intent);
    }

    @Override
    public void onError(int resId) {
        onError(getString(resId));
    }

    @Override
    public void onError(String message) {
        if (message != null) {
            showSnackbar(message);
        }
    }

    private void showSnackbar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(int resId) {
        showMessage(getString(resId));
    }

    @Override
    public void showMessage(String message) {
        if (message != null) {
            showSnackbar(message);
        }
    }

    @Override
    public boolean isNetworkConnected() {
        return isNetworkConnection();
    }

    protected boolean isNetworkConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    @Override
    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void openActivityOnLogout() {
    }


    public void setToolbarTitle(String title) {
        changeToolbarTitle(title);
    }

    private void changeToolbarTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    public void setToolbarTitle(@StringRes int titleResId) {
        String title = getString(titleResId);
        changeToolbarTitle(title);
    }


    @Override
    public void onBackPressed() {

        boolean handled = false;

        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if (fragment instanceof BaseFragment) {
                handled = ((BaseFragment) fragment).onBackPressed();

                if (handled) {
                    break;
                }
            }
        }

        if (!handled) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onDestroyActivity();
    }

    protected abstract void onDestroyActivity();
}
