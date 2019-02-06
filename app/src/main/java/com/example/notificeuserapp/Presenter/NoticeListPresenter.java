package com.example.notificeuserapp.Presenter;

import android.content.Context;

import com.example.notificeuserapp.Base.BasePresenter;
import com.example.notificeuserapp.Data.Notice;
import com.example.notificeuserapp.Model.DatabaseModel;
import com.example.notificeuserapp.View.Notices.ListNoticeView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NoticeListPresenter extends BasePresenter<ListNoticeView> {

    private DatabaseModel databaseModel;
    private Context context;


    public NoticeListPresenter(Context context) {
        this.context = context;
        databaseModel = new DatabaseModel(this.context);
    }


    public void loadMyNotices(String userId) {
        getMvpView().showLoading();

        Disposable disposable = databaseModel
                .getUserNotice(userId)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(notices -> {
                    getMvpView().showListNotice(notices);
                    getMvpView().hideLoading();
                }, throwable -> getMvpView().onError("Error loading!"));

        getCompositeDisposable().add(disposable);
    }

    public void deleteNotice(Notice notice) {
        databaseModel.deleteUserNotice(notice);
    }
}
