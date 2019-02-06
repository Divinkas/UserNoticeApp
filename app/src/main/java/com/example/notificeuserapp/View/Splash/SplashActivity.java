package com.example.notificeuserapp.View.Splash;

import android.os.Bundle;

import com.example.notificeuserapp.Base.BaseActivity;
import com.example.notificeuserapp.Presenter.SplashPresenter;
import com.example.notificeuserapp.View.Login.SignInActivity;
import com.example.notificeuserapp.View.Notices.NoticesActivity;

public class SplashActivity extends BaseActivity implements SplashView {

    private SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        presenter = new SplashPresenter(this);
        presenter.onAttach(this);
    }


    @Override
    public void openNoticesScreen() {
        BaseActivity.startActivity(this, NoticesActivity.class);
        finish();
    }

    @Override
    public void openSignInScreen() {
        BaseActivity.startActivity(this, SignInActivity.class);
        finish();
    }

    @Override
    protected void onDestroyActivity() {
        presenter.onDetach();
    }
}
