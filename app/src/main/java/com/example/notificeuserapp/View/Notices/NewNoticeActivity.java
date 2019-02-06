package com.example.notificeuserapp.View.Notices;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.notificeuserapp.Base.BaseActivity;
import com.example.notificeuserapp.Data.Notice;
import com.example.notificeuserapp.Presenter.NewNoticePresenter;
import com.example.notificeuserapp.View.Contract.NoticesView;

import java.util.List;


public class NewNoticeActivity extends BaseActivity implements NewNoticeView {

    private NewNoticePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_notice);

        presenter = new NewNoticePresenter(this);
        presenter.onAttach(this);
    }


    @Override
    protected void onDestroyActivity() {
        presenter.onDetach();
    }

}
