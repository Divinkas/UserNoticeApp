package com.example.notificeuserapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.notificeuserapp.View.AuthentificationActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import static com.example.notificeuserapp.Utils.Constants.RC_SIGN_IN;

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
        signInButton.setOnClickListener(view -> {
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.client_id))
                .requestEmail()
                .build();
            GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(getApplicationContext(), gso);
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                assert account != null;
                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth
                        .signInWithCredential(credential)
                        .addOnCompleteListener(SignInActivity.this, task2 -> {
                            setContentView(R.layout.activity_main);
                            if (task2.isSuccessful()) {
                                openNoticeActivity();
                            }
                        });
            } catch (ApiException ignored) {
                Toast.makeText(this, "Авторизация неуспешна!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openNoticeActivity(){
        Intent intent = new Intent(this, NoticesActivity.class);
        startActivity(intent);
        finish();
    }

}
