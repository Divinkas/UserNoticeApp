package com.example.notificeuserapp.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.notificeuserapp.SignInActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public abstract class AuthentificationActivity extends BaseNetworkActivity {
    protected FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isNetworkConnection()){
            FirebaseApp.initializeApp(this);
            mAuth = FirebaseAuth.getInstance();
        }
    }

    public FirebaseUser getCurrentUser(){
        return mAuth.getCurrentUser();
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
