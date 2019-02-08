package com.example.notificeuserapp.presenter.interfaces;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public interface ISignInPresenter {

    void signInClicked();
    FirebaseUser getCurrentUser();
    FirebaseAuth getFirebaseAuth();
}
