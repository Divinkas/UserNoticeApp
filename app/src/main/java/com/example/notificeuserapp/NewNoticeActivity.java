package com.example.notificeuserapp;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.widget.Button;
import android.widget.Toast;

import com.example.notificeuserapp.Data.Notice;
import com.example.notificeuserapp.Presenter.NoticePresenter;
import com.example.notificeuserapp.Utils.Constants;
import com.example.notificeuserapp.View.AuthentificationActivity;

import java.util.Objects;

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

        AppCompatEditText textNotice = findViewById(R.id.apcNoticeText);
        Button btnAddNotice = findViewById(R.id.btnAddNewNotice);

        btnAddNotice.setOnClickListener(view -> {
            if(!Objects.requireNonNull(textNotice.getText()).toString().isEmpty()){
                presenter.insertNotice(
                        new Notice(
                                Constants.DEFAULT_NOTICE_ID,
                                textNotice.getText().toString(),
                                getCurrentUser().getUid()
                        )
                );
                Toast.makeText(getApplicationContext(), "Заметка добавлена", Toast.LENGTH_SHORT).show();
                textNotice.setText("");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unSubscribe();
    }
}
