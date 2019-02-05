package com.example.notificeuserapp;

import android.os.Bundle;

import com.example.notificeuserapp.View.BaseNetworkActivity;

public class SignInActivity extends BaseNetworkActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isNetworkConnection()){ initView(); }
        else{ errorConnection(); }
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);

    }
}
