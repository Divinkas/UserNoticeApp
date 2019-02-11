package com.example.notificeuserapp.view.interfaces;

import android.content.Intent;

import com.example.notificeuserapp.view.interfaces.base.IView;

public interface ISignInView extends IView {
    void tryingView(Intent signInIntent);
    void openNextActivity();
}
