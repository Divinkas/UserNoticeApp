package com.example.notificeuserapp.Base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<T extends IView> implements IPresenter<T> {


    private final CompositeDisposable compositeDisposable;
    private T mvpView;

    public BasePresenter() {
        this.compositeDisposable = new CompositeDisposable();
    }

    public T getMvpView() {
        return mvpView;
    }


    @Override
    public void onAttach(T view) {
        this.mvpView = view;
    }

    @Override
    public void onRestoreView(@NonNull Bundle savedInstanceState) {

    }

    @Override
    public void onSaveStateView(@NonNull Bundle savedInstanceState) {

    }

    @Override
    public void onDetach() {
        this.mvpView = null;
        compositeDisposable.clear();
    }

    @Override
    public void setUserAsLoggedOut() {
        //TODO logout an go to login activity
    }

    public boolean isViewAttached() {
        return mvpView != null;
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    ;
}
