package com.example.notificeuserapp.Presenter;

import com.example.notificeuserapp.Presenter.interfaces.ISignInPresenter;
import com.example.notificeuserapp.View.ISignInView;

public class SignInPresenter implements ISignInPresenter {
    private ISignInView view;

    public SignInPresenter(ISignInView iSignInView){
        this.view = iSignInView;
    }
    @Override
    public void signInClicked() {
        view.tryingView();
    }
}
