package com.example.notificeuserapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.notificeuserapp.Presenter.SignInPresenter;
import com.example.notificeuserapp.Presenter.interfaces.ISignInPresenter;
import com.example.notificeuserapp.View.AuthentificationActivity;
import com.example.notificeuserapp.View.ISignInView;
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

public class SignInActivity extends AuthentificationActivity implements ISignInView {

    private ISignInPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SignInPresenter(this);
        if(isNetworkConnection()){
            if(getCurrentUser()== null) initView();
            else { openNextActivity(); }
        }
        else{ errorConnection(); }
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        setTitle(R.string.login_title);
        SignInButton signInButton = findViewById(R.id.btnSingInGoogle);
        signInButton.setOnClickListener(view -> presenter.signInClicked());
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
                                openNextActivity();
                            }
                        });
            } catch (ApiException ignored) {
                Toast.makeText(this, "Авторизация неуспешна!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void tryingView() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.client_id))
                .requestEmail()
                .build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(getApplicationContext(), gso);
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void openNextActivity() {
        Intent intent = new Intent(this, NoticesActivity.class);
        startActivity(intent);
        finish();
    }
}
