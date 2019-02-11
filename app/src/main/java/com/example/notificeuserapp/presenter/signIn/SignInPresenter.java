package com.example.notificeuserapp.presenter.signIn;


import android.content.Context;

import com.example.notificeuserapp.model.firebase.FirebaseModel;
import com.example.notificeuserapp.presenter.base.BasePresenter;
import com.example.notificeuserapp.presenter.signIn.interfaces.ISignInPresenter;
import com.example.notificeuserapp.view.interfaces.ISignInView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInPresenter extends BasePresenter<ISignInView> implements ISignInPresenter {

    private FirebaseModel firebaseModel;

    public SignInPresenter(Context context) {
        firebaseModel = new FirebaseModel(context);
    }

    public FirebaseAuth getFirebaseAuth(){
        return firebaseModel.getFirebaseAuth();
    }

    public FirebaseUser getCurrentUser(){
        return firebaseModel.getCurrentUser();
    }

    @Override
    public void signInClicked() {
        getView().tryingView(firebaseModel.getSignInIntent());
    }
}
