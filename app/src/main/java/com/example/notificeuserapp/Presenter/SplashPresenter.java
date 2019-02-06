package com.example.notificeuserapp.Presenter;

import android.content.Context;
import android.content.Intent;

import com.example.notificeuserapp.Base.BasePresenter;
import com.example.notificeuserapp.View.Splash.SplashView;
import com.google.firebase.auth.FirebaseAuth;

public class SplashPresenter extends BasePresenter<SplashView> {

    protected FirebaseAuth mAuth;
    private Context context;

    public SplashPresenter(Context context) {
        this.context = context;
        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()==null){
            getMvpView().openSignInScreen();
        }
        else{
            getMvpView().openNoticesScreen();
        }
    }
}
