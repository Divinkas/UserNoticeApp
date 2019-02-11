package com.example.notificeuserapp.presenter.base;

import com.example.notificeuserapp.presenter.base.interfaces.IPresenter;

public abstract class BasePresenter<T> implements IPresenter {
    private T view;

    public T getView(){
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }
}
