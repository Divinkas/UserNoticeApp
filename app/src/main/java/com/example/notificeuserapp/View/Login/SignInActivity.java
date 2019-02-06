package com.example.notificeuserapp.View.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.notificeuserapp.Base.BaseActivity;
import com.example.notificeuserapp.Presenter.SignInPresenter;
import com.google.android.gms.common.SignInButton;

public class SignInActivity extends BaseActivity implements SignInView {

    private SignInPresenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new SignInPresenter(this);
        presenter.onAttach(this);

        initView();
    }

    public void initView() {
        setContentView(R.layout.activity_main);
        SignInButton signInButton = findViewById(R.id.btnSingInGoogle);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onSignInClicked();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void trySignIn() {
        //                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                    .requestIdToken(getString(R.string.default_web_client_id))
//                    .requestEmail()
//                    .build();
//                GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(context, gso);
//                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//                startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onDestroyActivity() {
        presenter.onDetach();
    }
}
