package com.example.notificeuserapp.presenter;

abstract class BasePresenter<T>{
    private T view;

    public T getView(){
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }
}
