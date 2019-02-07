package com.example.notificeuserapp.model;

import android.content.Context;
import android.content.Intent;

import com.example.notificeuserapp.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class FirebaseModel {
    private Context context;
    private FirebaseAuth firebaseAuth;

    public FirebaseModel(Context context){
        this.context = context;
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public FirebaseUser getCurrentUser(){
        return firebaseAuth.getCurrentUser();
    }

    public Intent getSignInIntent(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.client_id))
                .requestEmail()
                .build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(context.getApplicationContext(), gso);
        return mGoogleSignInClient.getSignInIntent();
    }
}
