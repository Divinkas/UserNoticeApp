package com.example.notificeuserapp.view.activity.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.notificeuserapp.view.application.MyApplication;
import com.example.notificeuserapp.view.activity.SignInActivity;
import com.google.firebase.auth.FirebaseAuth;

public abstract class AuthentificationActivity extends BaseNetworkActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.initFirebase();
    }

    protected void openLoginActivity(){
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

    protected void logOut(){
        FirebaseAuth.getInstance().signOut();
        openLoginActivity();
    }
}
