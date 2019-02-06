package com.example.notificeuserapp.Presenter;

import android.content.Context;

import com.example.notificeuserapp.Base.BasePresenter;
import com.example.notificeuserapp.Data.Notice;
import com.example.notificeuserapp.Model.DatabaseModel;
import com.example.notificeuserapp.View.Contract.NoticesView;
import com.example.notificeuserapp.View.Notices.EditNoticeView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class EditNoticePresenter extends BasePresenter<EditNoticeView> {
    private DatabaseModel databaseModel;
    private Context context;


    public EditNoticePresenter(Context context) {
        this.context = context;
        databaseModel = new DatabaseModel(this.context);
    }

    public void updateNotice(Notice notice) {
        databaseModel.updateUserNotice(notice);
    }

    public void deleteNotice(Notice notice) {
        databaseModel.deleteUserNotice(notice);
    }
}
