package com.example.notificeuserapp.View;

public interface ISignInView extends IView {
    void signInClicked(boolean isAuthorize);
    boolean isAuthorize();
}
