package com.example.notificeuserapp.Presenter;

import android.content.Context;

import com.example.notificeuserapp.Data.Notice;
import com.example.notificeuserapp.Model.DatabaseModel;
import com.example.notificeuserapp.View.Contract.NoticesContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NoticePresenter {
    private DatabaseModel databaseModel;
    private Context context;
    private NoticesContract contract;

    private Disposable disposable;

    public NoticePresenter(Context context){
        this.context = context;
        databaseModel = new DatabaseModel(this.context);
    }

    public NoticePresenter(Context context, NoticesContract contract) {
        this.context = context;
        this.contract = contract;
        databaseModel = new DatabaseModel(this.context);
    }

    public void loadMyNotices(String userId) {
        contract.showLoading();
        if(contract!= null) {
            disposable = databaseModel
                    .getUserNotice(userId)
                    .observeOn(Schedulers.io())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(notices -> {
                        contract.loadListNotice(notices);
                        contract.hideLoading();
                    }, throwable -> contract.showErrorLoading());
        }
    }

    public void updateNotice(Notice notice){
        databaseModel.updateUserNotice(notice);
    }

    public void insertNotice(Notice notice){
        databaseModel.addUserNotice(notice);
    }

    public void deleteNotice(Notice notice){
        databaseModel.deleteUserNotice(notice);
    }

    public void unSubscribe(){
        if(disposable != null){
            disposable.dispose();
        }
    }
}
