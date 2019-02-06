package com.example.notificeuserapp.Presenter;

import android.content.Context;

import com.example.notificeuserapp.Base.BasePresenter;
import com.example.notificeuserapp.Data.Notice;
import com.example.notificeuserapp.Model.DatabaseModel;
import com.example.notificeuserapp.View.Contract.NoticesView;
import com.example.notificeuserapp.View.Notices.NewNoticeView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewNoticePresenter extends BasePresenter<NewNoticeView> {

    private DatabaseModel databaseModel;
    private Context context;


    public NewNoticePresenter(Context context) {
        this.context = context;
        databaseModel = new DatabaseModel(this.context);
    }

    public void insertNotice(Notice notice) {
        databaseModel.addUserNotice(notice);
    }

}
