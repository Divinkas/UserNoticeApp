package com.example.notificeuserapp.presenter;


import android.content.Context;

import com.example.notificeuserapp.model.FirebaseModel;
import com.example.notificeuserapp.presenter.interfaces.ISignInPresenter;
import com.example.notificeuserapp.view.interfaces.ISignInView;
import com.google.firebase.auth.FirebaseUser;

public class SignInPresenter implements ISignInPresenter {

    private ISignInView view;
    private FirebaseModel firebaseModel;

    public SignInPresenter(ISignInView view, Context context) {
        this.view = view;
        firebaseModel = new FirebaseModel(context);
    }

    public FirebaseUser getCurrentUser(){
        return firebaseModel.getCurrentUser();
    }
    @Override
    public void signInClicked() {
        view.tryingView(firebaseModel.getSignInIntent());
    }
}
