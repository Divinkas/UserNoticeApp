package com.example.notificeuserapp.Presenter;

import android.content.Context;

import com.example.notificeuserapp.Data.Notice;
import com.example.notificeuserapp.Model.DatabaseModel;
import com.example.notificeuserapp.Presenter.interfaces.IInsertNoticePresenter;
import com.example.notificeuserapp.Presenter.interfaces.ILoadNoticePresenter;
import com.example.notificeuserapp.Presenter.interfaces.IUpdateNoticePresenter;
import com.example.notificeuserapp.View.Contract.NoticesContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.internal.observers.ConsumerSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class NoticePresenter extends BasePresenter
        implements ILoadNoticePresenter, IUpdateNoticePresenter, IInsertNoticePresenter {
    private Context context;
    private NoticesContract contract;

    public NoticePresenter(Context context){
        this.context = context;
        databaseModel = new DatabaseModel(this.context);
    }

    public NoticePresenter(Context context, NoticesContract contract) {
        this.context = context;
        this.contract = contract;
        databaseModel = new DatabaseModel(this.context);
    }

    @Override
    public void loadMyNotices(String userId) {
        contract.showLoading();
        if(contract!= null) {
            databaseModel
                    .getUserNotice(userId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new ConsumerSingleObserver<>(notices -> {
                        contract.hideLoading();
                        contract.loadListNotice(notices);
                    }, throwable -> contract.showErrorLoading()));
        }
    }

    @Override
    public void updateNotice(Notice notice){
        databaseModel.updateUserNotice(notice);
    }

    @Override
    public void insertNotice(Notice notice){
        databaseModel.addUserNotice(notice);
    }

    @Override
    public void deleteNotice(Notice notice){
        databaseModel.deleteUserNotice(notice);
    }

}
