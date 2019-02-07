package com.example.notificeuserapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.notificeuserapp.R;
import com.example.notificeuserapp.presenter.SignInPresenter;
import com.example.notificeuserapp.presenter.interfaces.ISignInPresenter;
import com.example.notificeuserapp.view.interfaces.ISignInView;
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

import static com.example.notificeuserapp.utils.Constants.RC_SIGN_IN;

public class SignInActivity extends AuthentificationActivity implements ISignInView {

    private ISignInPresenter presenter;
    private ProgressBar progressBar;
    private SignInButton signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SignInPresenter(this, this);
        if(isNetworkConnection()){
            if(presenter.getCurrentUser()== null) initView();
            else { openNextActivity(); }
        }
        else{ errorConnection(); }
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_sign_in);
        setTitle(R.string.login_title);
        progressBar = findViewById(R.id.progressBarUserLoading);
        signInButton = findViewById(R.id.btnSingInGoogle);
        signInButton.setOnClickListener(view -> {
            showLoading();
            presenter.signInClicked();
        });
        hideLoading();
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
                            setContentView(R.layout.activity_sign_in);
                            if (task2.isSuccessful()) {
                                hideLoading();
                                openNextActivity();
                            }
                        });
            } catch (ApiException ignored) {
                Toast.makeText(this, R.string.failed_autorize, Toast.LENGTH_SHORT).show();
                hideLoading();
            }
        }
    }

    @Override
    public void tryingView(Intent signInIntent) {
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void openNextActivity() {
        Intent intent = new Intent(this, NoticesActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        signInButton.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        signInButton.setVisibility(View.VISIBLE);
    }
}
