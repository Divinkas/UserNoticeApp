package com.example.notificeuserapp.Presenter;

import android.content.Context;
import android.content.Intent;

import com.example.notificeuserapp.Base.BasePresenter;
import com.example.notificeuserapp.View.Login.SignInView;
import com.google.firebase.auth.FirebaseAuth;

public class SignInPresenter extends BasePresenter<SignInView> {

    protected FirebaseAuth mAuth;
    private Context context;

    public SignInPresenter(Context context) {
        this.context = context;
        mAuth = FirebaseAuth.getInstance();
    }

    public void onSignInClicked() {
        getMvpView().trySignIn();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
