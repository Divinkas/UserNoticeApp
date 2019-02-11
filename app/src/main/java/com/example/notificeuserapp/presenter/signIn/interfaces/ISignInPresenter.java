package com.example.notificeuserapp.presenter.signIn.interfaces;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public interface ISignInPresenter {

    void signInClicked();
    FirebaseUser getCurrentUser();
    FirebaseAuth getFirebaseAuth();
}
