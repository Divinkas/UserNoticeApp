package com.example.notificeuserapp.presenter;

import android.content.Context;

import com.example.notificeuserapp.model.data.Notice;
import com.example.notificeuserapp.model.room.IRoomNoticeModel;
import com.example.notificeuserapp.model.room.RoomNoticeModel;
import com.example.notificeuserapp.model.room.base.RoomDB;
import com.example.notificeuserapp.presenter.interfaces.IInsertNoticePresenter;
import com.example.notificeuserapp.presenter.interfaces.ILoadNoticePresenter;
import com.example.notificeuserapp.presenter.interfaces.IUpdateNoticePresenter;
import com.example.notificeuserapp.view.MyApplication;
import com.example.notificeuserapp.view.interfaces.INoticeView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.internal.observers.ConsumerSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class NoticePresenter extends BasePresenter<INoticeView>
        implements ILoadNoticePresenter, IUpdateNoticePresenter, IInsertNoticePresenter {

    private IRoomNoticeModel roomNoticeModel;
    private RoomDB database;

    public NoticePresenter(Context context){
        database = ((MyApplication)context.getApplicationContext()).getRoomInstance();
        roomNoticeModel = new RoomNoticeModel(database);
    }

    @Override
    public void loadMyNotices(String userId) {
        getView().showLoading();
        if(getView()!= null) {
            roomNoticeModel
                    .getUserNotice(userId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new ConsumerSingleObserver<>(notices -> {
                        getView().hideLoading();
                        getView().loadListNotice(notices);
                    }, throwable -> getView().showErrorLoading()));
        }
    }

    @Override
    public void updateNotice(Notice notice){
        roomNoticeModel.updateUserNotice(notice);
    }

    @Override
    public void insertNotice(Notice notice){
        roomNoticeModel.addUserNotice(notice);
    }

    @Override
    public void deleteNotice(Notice notice){
        roomNoticeModel.deleteUserNotice(notice);
    }


}
