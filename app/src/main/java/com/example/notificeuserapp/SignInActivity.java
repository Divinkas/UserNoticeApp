package com.example.notificeuserapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.notificeuserapp.View.AuthentificationActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;

public class SignInActivity extends AuthentificationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isNetworkConnection()){
            if(getCurrentUser()== null)
                initView();
            else {
                Intent intent = new Intent(this, NoticesActivity.class);
                startActivity(intent);
                finish();
            }
        }
        else{ errorConnection(); }
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        SignInButton signInButton = findViewById(R.id.btnSingInGoogle);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                    .requestIdToken(getString(R.string.default_web_client_id))
//                    .requestEmail()
//                    .build();
//                GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
    }

}
