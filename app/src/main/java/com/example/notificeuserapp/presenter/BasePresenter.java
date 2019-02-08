package com.example.notificeuserapp.presenter;

import com.example.notificeuserapp.presenter.interfaces.IPresenter;

abstract class BasePresenter<T> implements IPresenter {
    private T view;

    public T getView(){
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }
}
