package com.example.notificeuserapp;

import android.os.Bundle;

import com.example.notificeuserapp.Presenter.NoticePresenter;
import com.example.notificeuserapp.View.AuthentificationActivity;

public class NewNoticeActivity extends AuthentificationActivity {
    private NoticePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isNetworkConnection()){
            if(getCurrentUser()== null)
                openLoginActivity();
            else {
                initView();
            }
        }
        else{ errorConnection(); }
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_new_notice);
        presenter = new NoticePresenter(this);

        //presenter.insertNotice();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unSubscribe();
    }
}
