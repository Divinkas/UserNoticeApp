package com.example.notificeuserapp.view.interfaces;

import android.content.Intent;

import com.google.firebase.auth.FirebaseUser;

public interface ISignInView extends IView {
    void tryingView(Intent signInIntent);
    void openNextActivity();
}
