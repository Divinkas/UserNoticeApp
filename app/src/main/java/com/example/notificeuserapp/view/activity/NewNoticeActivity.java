package com.example.notificeuserapp.view.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.widget.Button;
import android.widget.Toast;

import com.example.notificeuserapp.R;
import com.example.notificeuserapp.model.data.Notice;
import com.example.notificeuserapp.presenter.NoticePresenter;
import com.example.notificeuserapp.presenter.interfaces.IInsertNoticePresenter;
import com.example.notificeuserapp.utils.Constants;

import java.util.Objects;

public class NewNoticeActivity extends AuthentificationActivity {
    private IInsertNoticePresenter presenter;

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
        setTitle(R.string.insert_title);
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
                Toast.makeText(getApplicationContext(), R.string.notice_added, Toast.LENGTH_SHORT).show();
                textNotice.setText("");
            }
        });
    }

}