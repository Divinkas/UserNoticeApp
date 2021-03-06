package com.example.notificeuserapp.view.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.widget.Button;
import android.widget.Toast;

import com.example.notificeuserapp.R;
import com.example.notificeuserapp.model.data.Notice;
import com.example.notificeuserapp.presenter.notice.NoticePresenter;
import com.example.notificeuserapp.presenter.notice.interfaces.IInsertNoticePresenter;
import com.example.notificeuserapp.utils.Constants;
import com.example.notificeuserapp.view.activity.base.AuthentificationActivity;
import com.example.notificeuserapp.view.application.MyApplication;

import java.util.Objects;

public class NewNoticeActivity extends AuthentificationActivity {
    private IInsertNoticePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isNetworkConnection()){
            if (MyApplication.getFirebaseAuth() != null) {
                if(MyApplication.getFirebaseAuth().getCurrentUser() == null)
                    openLoginActivity();
                else {
                    initView();
                }
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
                assert MyApplication.getFirebaseAuth() != null;
                presenter.insertNotice(
                        new Notice(
                                Constants.DEFAULT_NOTICE_ID,
                                textNotice.getText().toString(),
                                Objects.requireNonNull(MyApplication.getFirebaseAuth().getCurrentUser()).getUid()
                        )
                );
                Toast.makeText(getApplicationContext(), R.string.notice_added, Toast.LENGTH_SHORT).show();
                textNotice.setText("");
            }
        });
    }

}
