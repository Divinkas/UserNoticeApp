package com.example.notificeuserapp.model.room.notice;

import android.content.Context;

import com.example.notificeuserapp.model.data.Notice;
import com.example.notificeuserapp.model.room.base.RoomDB;
import com.example.notificeuserapp.view.application.MyApplication;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class RoomNoticeModel implements IRoomNoticeModel {
    private RoomDB database;

    public RoomNoticeModel(Context context) {
        database = MyApplication.getRoomInstance(context);
    }

    @Override
    public Single<List<Notice>> getUserNotice(String userId){
        return database.noticeDao().getAllNoticesUser(userId);
    }

    @Override
    public void sendQuery(Callable<Integer> callable){
        Single.fromCallable(callable)
                .subscribeOn(Schedulers.io())
                .subscribe();
    }


    @Override
    public void addUserNotice(Notice notice){
        sendQuery(() -> {
            database.noticeDao().insertNotice(notice);
            return 0;
        });
    }

    @Override
    public void updateUserNotice(Notice notice){
        sendQuery( () -> {
            database.noticeDao().updateNotice(notice);
            return 0;
        });
    }

    @Override
    public void deleteUserNotice(Notice notice){
        sendQuery(() -> {
            database.noticeDao().deleteNotice(notice);
            return 0;
        });
    }

}
